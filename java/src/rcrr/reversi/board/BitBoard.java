/*
 *  BitBoard.java
 *
 *  Copyright (c) 2012 Roberto Corradini. All rights reserved.
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
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MUST  02111-1307, USA
 *  or visit the site <http://www.gnu.org/licenses/>.
 */

package rcrr.reversi.board;

/**
 * The class provides an implementation for common methods used by the bit board concrete implementations.
 */
public abstract class BitBoard extends AbstractBoard {

    /** Integer value for the black player. It is equal to Player.BLACK.ordinal(). */
    static final int BLACK = 0;

    /** Integer value for the white player. It is equal to Player.WHITE.ordinal(). */
    static final int WHITE = 1;

    /** Used for masking a byte when using integer values. */
    static final int BYTE_MASK_FOR_INT = 0xFF;

    /** A bitboard being all set with the exception of column A. */
    private static final long ALL_SQUARES_EXCEPT_COLUMN_A = 0xFEFEFEFEFEFEFEFEL;

    /** A bitboard being all set with the exception of column H. */
    private static final long ALL_SQUARES_EXCEPT_COLUMN_H = 0x7F7F7F7F7F7F7F7FL;

    /**
     * Nine squares configurations arranged in an array. Position 0 is all set.
     * Position 1 has columns B, C, D, E, F, G, H set, column A is empty.
     * Position 2 has columns C, D, E, F, G, H set, column A and B are empty.
     * Positions 3 to 7 follow the same pattern, up to position 8 that has all empies.
     */
    private static final long[] ALL_SQUARES_EXCEPT_LEFT_COLUMNS = {
        0xFFFFFFFFFFFFFFFFL, 0xFEFEFEFEFEFEFEFEL, 0xFCFCFCFCFCFCFCFCL,
        0xF8F8F8F8F8F8F8F8L, 0xF0F0F0F0F0F0F0F0L, 0xE0E0E0E0E0E0E0E0L,
        0xC0C0C0C0C0C0C0C0L, 0x8080808080808080L, 0x0000000000000000L
    };

    /**
     * Nine squares configurations arranged in an array. Position 0 is all set.
     * Position 1 has columns A, B, C, D, E, F, G set, column H is empty.
     * Position 2 has columns A, B, C, D, E, F, set, column G and H are empty.
     * Positions 3 to 7 follow the same pattern, up to position 8 that has all empies.
     */
    private static final long[] ALL_SQUARES_EXCEPT_RIGTH_COLUMNS = {
        0xFFFFFFFFFFFFFFFFL, 0x7F7F7F7F7F7F7F7FL, 0x3F3F3F3F3F3F3F3FL,
        0x1F1F1F1F1F1F1F1FL, 0x0F0F0F0F0F0F0F0FL, 0x0707070707070707L,
        0x0303030303030303L, 0x0101010101010101L, 0x0000000000000000L
    };

    /** Shifts 1 position. */
    private static final int SHIFT_1 = 1;

    /** Shifts 9 positions, 8 - 1. */
    private static final int SHIFT_7 = 7;

    /** Shifts 8 positions. */
    private static final int SHIFT_8 = 8;

    /** Shifts 9 positions, 8 + 1. */
    private static final int SHIFT_9 = 9;

    /**
     * Returns the opponent of {@code player} parameter.
     *
     * @param player a player
     * @return       the opponent player
     */
    static final int opponent(final int player) { return player ^ WHITE; }

    /**
     * Returns a new long value by shifting the {@code squares} parameter by one position
     * on the board, following the direction given by the {@code dir} parameter.
     *
     * @param squares the squares set on the bitboard
     * @param dir     the direction along to make the shift
     * @return        the shifted squares
     */
    static final long shift(final long squares, final Direction dir) {
        switch (dir) {
        case NW: return (squares >>> SHIFT_9) & ALL_SQUARES_EXCEPT_COLUMN_H;
        case N:  return (squares >>> SHIFT_8);
        case NE: return (squares >>> SHIFT_7) & ALL_SQUARES_EXCEPT_COLUMN_A;
        case W:  return (squares >>> SHIFT_1) & ALL_SQUARES_EXCEPT_COLUMN_H;
        case E:  return (squares <<  SHIFT_1) & ALL_SQUARES_EXCEPT_COLUMN_A;
        case SW: return (squares <<  SHIFT_7) & ALL_SQUARES_EXCEPT_COLUMN_H;
        case S:  return (squares <<  SHIFT_8);
        case SE: return (squares <<  SHIFT_9) & ALL_SQUARES_EXCEPT_COLUMN_A;
        default: throw new IllegalArgumentException("Undefined value for direction. dir=" + dir);
        }
    }

    /**
     * Returns a new long value by shifting the {@code squares} parameter on the board, by a number of positions
     * as given by the {@code amount} parameter, following the direction given by the {@code dir} parameter.
     * <p>
     * Amount must be in the 0..8 range, meaning that 0 is equal to no shift, 1 is on position,
     * and 8 always return an empy squares.
     *
     * @param squares the squares set on the bitboard
     * @param dir     the direction along to make the shift
     * @param amount  the amount to shift
     * @return        the shifted squares
     */
    static final long shift(final long squares, final Direction dir, final int amount) {
        switch (dir) {
        case NW: return (squares >>> (SHIFT_9 * amount)) & ALL_SQUARES_EXCEPT_RIGTH_COLUMNS[amount];
        case N:  return (squares >>> (SHIFT_8 * amount));
        case NE: return (squares >>> (SHIFT_7 * amount)) & ALL_SQUARES_EXCEPT_LEFT_COLUMNS[amount];
        case W:  return (squares >>> (SHIFT_1 * amount)) & ALL_SQUARES_EXCEPT_RIGTH_COLUMNS[amount];
        case E:  return (squares <<  (SHIFT_1 * amount)) & ALL_SQUARES_EXCEPT_LEFT_COLUMNS[amount];
        case SW: return (squares <<  (SHIFT_7 * amount)) & ALL_SQUARES_EXCEPT_RIGTH_COLUMNS[amount];
        case S:  return (squares <<  (SHIFT_8 * amount));
        case SE: return (squares <<  (SHIFT_9 * amount)) & ALL_SQUARES_EXCEPT_LEFT_COLUMNS[amount];
        default: throw new IllegalArgumentException("Undefined value for direction. dir=" + dir);
        }
    }

    /**
     * The bitboard field.
     * Values can be modified only by the constructor.
     */
    private final transient long[] bitboard;

    /**
     * Class constructor.
     * <p>
     * {@code bitboard} must be not null, and must have a size equal to
     * two. Overlapping bit set to one are not allowed.
     * Invariants are not enforced.
     *
     * @param  bitboard the bitboard field
     */
    BitBoard(final long[] bitboard) {
        this.bitboard = bitboard.clone();
    }

    /**
     * Returns the disk count for the color.
     *
     * @param color the color for which the disk count is computed
     * @return the disk count
     * @throws NullPointerException if parameter {@code color} is null
     */
    @Override
    public int countPieces(final SquareState color) {
        if (color == null) {
            throw new NullPointerException("Parameter color must be not null.");
        }
        switch (color) {
        case BLACK: return Long.bitCount(bitboard[BLACK]);
        case WHITE: return Long.bitCount(bitboard[WHITE]);
        case EMPTY: return Long.bitCount(empties());
        case OUTER: return 0;
        default: throw new IllegalArgumentException("Undefined value for color parameter. color=" + color);
        }
    }

    /**
     * Returns the {@code SquareState} value for the given board's square.
     * <p>
     * When {@code square} is {@code null} the method returns {@code SquareState.OUTER} value.
     *
     * @param  square the board square to retrieve the state value
     * @return        the square state
     */
    public SquareState get(final Square square) {
        if (square == null) { return SquareState.OUTER; }
        final long bitsquare = 1L << square.ordinal();
        if ((bitsquare & bitboard[BLACK]) != 0) {
            return SquareState.BLACK;
        } else if ((bitsquare & bitboard[WHITE]) != 0) {
            return SquareState.WHITE;
        } else {
            return SquareState.EMPTY;
        }
    }

    /**
     * Returns the empty set of squares in the board.
     *
     * @return the empy set of squares
     */
    final long empties() { return ~(bitboard[BLACK] | bitboard[WHITE]); }

    /**
     * Acessor method for the bitboard field.
     * <p>
     * Be carefull! Do not change the array values.
     * It should return a copy of the field, but for
     * performance reasons a reference of the array is returned.
     *
     * @return the bitboard field
     */
    long[] bitboard() { return this.bitboard; }

}
