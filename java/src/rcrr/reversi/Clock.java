/*
 *  Clock.java
 *
 *  Copyright (c) 2010 Roberto Corradini. All rights reserved.
 *
 *  This file is part of the reversi program
 *  http://github.com/rcrr/reversi
 *
 *  This program is free software; you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the
 *  Free Software Foundation; either version 3, or (at your option) any
 *  later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA
 *  or visit the site <http://www.gnu.org/licenses/>.
 */

package rcrr.reversi;

import java.util.Map;
import java.util.EnumMap;
import java.util.Collections;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;

/**
 * The {@code Clock} class defines the different clocks used in the game.
 * It has two values, the Black's remaining time, and the White's one.
 * The remaining times are received and returned by clock's methods by
 * means of duration objects, as defined by the Joda-Time {@code Duration} class.
 * <p>
 * {@code Clock} is immutable.
 * <p>
 * In order to create a new {@code Clock} are available three static factories.
 * The first is:
 * <pre>
 * {@code
 * Clock c = Clock.valueOf(blackDuration, whiteDuration);
 * }
 * </pre>
 * where the parameters {@code blackDuration} and {@code whiteDuration} are of type {@code Duration}.
 * The second factory is:
 * <pre>
 * {@code
 * Clock c = Clock.valueOf(durationMap);
 * }
 * </pre>
 * where the {@code durationMap} parameter is defined having objects of the {@code Player} class as keys
 * and objects of the {@code Duration} class as values.
 * And the third factory is:
 * <pre>
 * {@code
 * Clock c = Clock.initialClock(gameDuration);
 * }
 * </pre>
 * where the {@code gameDuration} parameter is assigned equally to both players.
 * <p>
 * Another way to get a new {@code Clock} is to call the {@code set}
 * method. It returns a new clock subtracting the provided delta time
 * to the specified player. For instance let say that we have already a {@code Clock} and we want to
 * subtract one full second from the Black player:
 * <pre>
 * {@code
 * Clock c;
 * ...
 * Clock updated = c.set(Player.BLACK, Seconds.ONE.toStandardDuration());
 * }
 * </pre>
 * where {@code Seconds.ONE.toStandardDuration()} parameter is
 * a one second duration object belonging to the {@code Duration} class.
 */
public final class Clock {

    /** It is really needed? */
    private static final NumberFormat TIME_FORMATTER = new DecimalFormat("##00");

    /** Prime number 17. */
    private static final int PRIME_NUMBER_17 = 17;

    /** Prime number 37. */
    private static final int PRIME_NUMBER_37 = 37;

    /**
     * Returns a String representing the clock.
     * The format is mm:ss corresponding to the given time in milliseconds, where:
     *  - mm is the amount of minutes
     *  - ss is the amount of seconds
     *
     * @param  duration time in milliseconds
     * @return          a formatted {@code String} with minutes and seconds
     */
    private static String timeString(final Duration duration) {
        long time = duration.getMillis();
        long rTime = time / DateTimeConstants.MILLIS_PER_SECOND;
        long minutes = (long) Math.floor(rTime / DateTimeConstants.SECONDS_PER_MINUTE);
        long seconds = rTime - (minutes * DateTimeConstants.SECONDS_PER_MINUTE);
        return TIME_FORMATTER.format(minutes) + ":" + TIME_FORMATTER.format(seconds);
    }

    /**
     * Class static factory that returns a new {@code Clock} object constructed using the given map.
     *
     * @param  durationMap the game's time assigned to the players
     * @return             a new {@code Clock} having Black's and White's time set to the
     *                     given parameters
     * @throws NullPointerException     when blackDuration or whiteDuration is null
     * @throws IllegalArgumentException when blackDuration or whiteDuration is negative
     */
    public static Clock valueOf(final Map<Player, Duration> durationMap) {
        if (durationMap == null) {
            throw new NullPointerException("Parameter durationMap cannot be null.");
        }
        if (durationMap.size() != Player.values().length) {
            throw new IllegalArgumentException("Parameter durationMap size is not consistent."
                                               + " durationMap.size()=" + durationMap.size()
                                               + " expected value: " + Player.values().length);
        }
        if (durationMap.containsKey(null)) {
            throw new NullPointerException("Parameter durationMap cannot have null keys.");
        }
        return new Clock(durationMap);
    }

    /**
     * Class static factory that returns a new {@code Clock} object with the given initial values
     * assigned to each individual player.
     *
     * @param  blackDuration the game's time assigned to the Black player
     * @param  whiteDuration the game's time assigned to the White player
     * @return               a new {@code Clock} having Black's and White's time set to the
     *                       given parameters
     * @throws NullPointerException     when blackDuration or whiteDuration is null
     * @throws IllegalArgumentException when blackDuration or whiteDuration is negative
     */
    public static Clock valueOf(final Duration blackDuration, final Duration whiteDuration) {
        if (blackDuration == null) { throw new NullPointerException("Parameter blackDuration cannot be null."); }
        if (whiteDuration == null) { throw new NullPointerException("Parameter blackDuration cannot be null."); }
        if (blackDuration.isShorterThan(Duration.ZERO)) {
            throw new IllegalArgumentException("Parameter blackDuration cannot be negative. blackDuration="
                                               + blackDuration);
        }
        if (whiteDuration.isShorterThan(Duration.ZERO)) {
            throw new IllegalArgumentException("Parameter whiteDuration cannot be negative. whiteDuration="
                                               + whiteDuration);
        }
        final Map<Player, Duration> mutableDurationMap = new EnumMap<Player, Duration>(Player.class);
        mutableDurationMap.put(Player.BLACK, blackDuration);
        mutableDurationMap.put(Player.WHITE, whiteDuration);
        return valueOf(mutableDurationMap);
    }

    /**
     * Class static factory that returns a new {@code Clock} object with the given initial value
     * assigned equally to both players.
     *
     * @param  initialDuration the game's duration assigned to the two players
     * @return                 a new {@code Clock} having Black's and White's time duration
     *                         set to the same given value
     * @throws IllegalArgumentException if gameTimeInMinutes is lesser than 1, or greather than 60.
     */
    public static Clock initialClock(final Duration initialDuration) {
        if (initialDuration == null) { throw new NullPointerException("Parameter initialDuration cannot be null."); }
        if (initialDuration.isShorterThan(Duration.ZERO)) {
            throw new IllegalArgumentException("Parameter initialDuration cannot be negative. initialDuration="
                                               + initialDuration);
        }
        return valueOf(initialDuration, initialDuration);
    }

    /** Lazily initialized, cached hashCode. */
    private volatile int hashCode = 0;

    /**
     * The playersGameDuration field.
     * It stores each players' clock time as a Joda-Time Duration.
     * Internally Durations store the time in milliseconds.
     */
    private final Map<Player, Duration> playersGameDuration;

    /**
     * Class constructor.
     * <p>
     * This constructor creates a Clock object given the Black's time and the White's
     * one.
     *
     * @param durationMap a map having the remaining time duration assigned to each player.
     */
    private Clock(final Map<Player, Duration> durationMap) {
        assert (durationMap != null) : "Parameter durationMap cannot be null.";
        assert (durationMap.size() == Player.values().length)
            : "Parameter durationMap size is not consistent."
            + " durationMap.size()=" + durationMap.size()
            + ", expected value: " + Player.values().length;
        final EnumMap<Player, Duration> durationEnumMap = (durationMap instanceof EnumMap)
            ? (EnumMap<Player, Duration>) durationMap : new EnumMap<Player, Duration>(durationMap);
        this.playersGameDuration = Collections.unmodifiableMap(durationEnumMap);
    }

    /**
     * Returns true if the specified object is equal to this clock.
     * Two clockss are equal when they have the same remaining times.
     *
     * @param object the object to compare to
     * @return {@code true} when the {@code object} parameter is an instance of
     *         the {@code Clock} class and when the time remaining to both
     *         players is equal.
     */
    @Override
    public boolean equals(final Object object) {
        if (object == this) { return true; }
        if (!(object instanceof Clock)) { return false; }
        Clock clock = (Clock) object;
        for (Player player : Player.values()) {
            if (!get(player).equals(clock.get(player))) { return false; }
        }
        return true;
    }

    /**
     * Returns a {@code Duration} value that represents the player's remaining time
     * as registered by the {@code Clock} instance.
     *
     * @param  player the player for which the remaining time is queried
     * @return        the player remaining duration
     * @throws NullPointerException if the player parameter is null
     */
    public Duration get(final Player player) {
        if (player == null) {
            throw new NullPointerException("Parameter player connot be null.");
        }
        return playersGameDuration.get(player);
    }

    /**
     * Returns a hash code for this clock.
     *
     * @return a hash code for this clock
     */
    @Override
    public int hashCode() {
        if (hashCode == 0) {
            int result = PRIME_NUMBER_17;
            for (Duration duration : playersGameDuration.values()) {
                int k = duration.hashCode();
                result = PRIME_NUMBER_37 * result + k;
            }
            hashCode = result;
        }
        return hashCode;
    }

    /**
     * Returns a formatted string, showing the two player clocks.
     *
     * @return a string showing the two player's clocks
     */
    public String printClock() {
        return "[" + Player.BLACK.symbol() + "=" + timeString(get(Player.BLACK)) + ", "
            + Player.WHITE.symbol() + "=" + timeString(get(Player.WHITE)) + "]";
    }

    /**
     * Returns a {@code String} representing the {@code Clock} object.
     *
     * @return a {@code String} representing the clock
     */
    @Override public String toString() {
        return "[" + Player.BLACK + "=" + timeString(get(Player.BLACK)) + ", "
            + Player.WHITE + "=" + timeString(get(Player.WHITE)) + "]";
    }

    /**
     * Returns a new {@code Clock} object generated subtracting the delta value from
     * the specified player remaining clock time.
     * <p>
     * When the delta parameter is greather than the player's actual time
     * the updated value is set to zero.
     *
     * @param  player the player from which to take away the specified time
     * @param  delta  the amount of time in milliseconds to subtract from the
     *                player's clock time
     * @return        a new updated {@code Clock}
     * @throws NullPointerException     if the player or delta parameter is null
     * @throws IllegalArgumentException if the delta parameter is negative.
     */
    public Clock set(final Player player, final Duration delta) {
        if (player == null) { throw new NullPointerException("Parameter player connot be null"); }
        if (delta == null) { throw new NullPointerException("Parameter delta connot be null."); }
        if (delta.isShorterThan(Duration.ZERO)) {
            throw new IllegalArgumentException("Parameter delta cannot be negative. delta=" + delta);
        }
        final Duration actual = playersGameDuration.get(player);
        final Duration updated = (actual.isLongerThan(delta)) ? actual.minus(delta) : Duration.ZERO;

        final Map<Player, Duration> mutableDurationMap = new EnumMap<Player, Duration>(playersGameDuration);
        mutableDurationMap.put(player, updated);
        return valueOf(mutableDurationMap);
    }

}
