/*
 *  Board.java
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
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA
 *  or visit the site <http://www.gnu.org/licenses/>.
 */

package rcrr.reversi;

import java.util.List;

/**
 * A board is an instance of a board game position. It is the state that a board
 * has regardless of the player that has to move or the time spent or remaining to each player.
 * <p>
 * A {@code Board} object holds the information of the state of each board's square.
 * <p>
 * Two boards are equal when they represent the same position. It is up to the implementation
 * if leverage the immutability property and to cache the existing boards instead of creating new ones.
 * 
 * @see Square
 * @see SquareState
 * @see Player
 * @see BoardFactory
 */
public interface Board {

    /**
     * Returns the disk difference between the player and her opponent.
     * <p>
     * Parameter {@code player} must be not {@code null}.
     *
     * @param player the player
     * @return       the disk count difference
     * @throws NullPointerException if parameter {@code player} is {@code null}
     */
    int countDifference(Player player);

    /**
     * Returns the disk count for the color.
     * <p>
     * Parameter {@code color} must be not {@code null}.
     *
     * @param color the color for which the disk count is computed
     * @return      the disk count
     * @throws NullPointerException if parameter {@code color} is {@code null}
     */
    int countPieces(SquareState color);

    /**
     * Returns the {@code SquareState} value for the given board's square.
     * <p>
     * When {@code square} is {@code null} the method has to return {@code SquareState.OUTER} value.
     *
     * @param  square the board square to retrieve the state value
     * @return        the square state
     */
    SquareState get(Square square);

    /**
     * Returns if the player has any legal move given the board state.
     * <p>
     * Parameter {@code player} must be not {@code null}.
     *
     * @param player the player
     * @return       {@code true} if the player has any legal move, otherwise {@code false}
     * @throws NullPointerException if parameter {@code player} is null
     */
    boolean hasAnyLegalMove(Player player);

    /**
     * Returns true if either black or white player has any legal move.
     *
     * @return {@code true} if either player has a legal move
     */
    boolean hasAnyPlayerAnyLegalMove();

    /**
     * Returns the boolean value telling if the move, done by the specified player, is legal.
     * <p>
     * Parameter {@code move} must be not {@code null}.
     * Parameter {@code player} must be not {@code null}.
     *
     * @param move   the square where to put the new disk
     * @param player the player moving
     * @return       true if the move is legal, otherwise false
     * @throws NullPointerException if parameter {@code move} or {@code player} is null
     */
    boolean isLegal(Square move, Player player);

    /**
     * Returns a list holding the legal moves that the {@code player} can
     * do at the board position. When no moves are available to the player
     * the method returns an empty list.
     * <p>
     * Parameter {@code player} must be not {@code null}.
     *
     * @param player the player
     * @return       the moves available to the player
     * @throws NullPointerException if parameter {@code player} is null
     */
    List<Square> legalMoves(Player player);

    /**
     * Returns a new updated board to reflect move by player. This static factory executes a game move
     * to the board and returns a new one, reflecting the move. The original board is not modified.
     * <p>
     * A null value for player is not allowed, a {@code NullPointerException} is thrown in such a case.
     * <p>
     * A null value for move is allowed, and moreover is the only valid value
     * acceptable by the method, when the player has not any legal move.
     * Otherwise a null move is forbidden, and a {@code NullPointerException} is risen.
     * <p>
     * The method does check if the move is legal. It throws an {@code IllegalArgumentException} in case it is not.
     *
     * @param  move   the board square where to put the disk
     * @param  player the disk color to put on the board
     * @return        a new {@code Board} reflecting the move made
     * @throws NullPointerException     if parameter {@code move} or {@code player} is null
     * @throws IllegalArgumentException if the {@code move} by {@code player} is illegal
     */
    Board makeMove(Square move, Player player);

    /**
     * Returns a formatted string showing a 2d graphical represention of the board.
     *
     * @return a string being a 2d representation of the board
     */
    String printBoard();

    /**
     * Returns a formatted string showing a 2d graphical composed view
     * of the board and the disk count.
     * <p>
     * The method joins the printBoard() and the printCount() output,
     * setting the second on the right of the first board's row.
     *
     * @return a string being a 2d representation of the board with the disk count
     */
    String printBoardWithCount();

    /**
     * Returns a formatted string, giving the two player disk count and their difference.
     *
     * @return a string showing the two player's count
     */
    String printCount();

}
