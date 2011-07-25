/*
 *  IagoMethodsTest.java
 *
 *  Copyright (c) 2010, 2011 Roberto Corradini. All rights reserved.
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
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.is;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/**
 * Test Suite for the {@code Iago} class.
 *
 * @see Iago
 */
public class IagoMethodsTest {

    /** Class constructor. */
    public IagoMethodsTest() { }

    /**
     * Tests the {@code init()} method in class {@code Iago.EdgeTable}.
     *
     * @see Iago.EdgeTable#init()
     */
    @Test
    public final void testEdgeTableInit() {
	// to be written.
        assertTrue(true);
    }

    /**
     * Tests the {@code load(String)} method in class {@code Iago.EdgeTable}.
     *
     * @see Iago.EdgeTable#load(String)
     */
    @Test
    public final void testEdgeTableLoad() {

	/** Load the static edge table from the file data/edge-table-st.dat. */
	Iago.EdgeTable table = Iago.EdgeTable.load("rcrr/reversi/data/edge-table-st_CL_REFERENCE.dat");
	assertTrue(true);

    }

    /**
     * Tests the {@code computeStatic()} method in class {@code Iago.EdgeTable}.
     *
     * @see Iago.EdgeTable#computeStatic()
     */
    @Test
    public final void testEdgeTableComputeStatic() throws NoSuchMethodException,
							  IllegalAccessException,
							  InvocationTargetException {

	Iago.EdgeTable computed = computeStaticProxy();
	Iago.EdgeTable expected = Iago.EdgeTable.load("rcrr/reversi/data/edge-table-st_CL_REFERENCE.dat");

	for (int index = 0; index < Iago.EdgeTable.SIZE; index++) {
	    assertThat("Values computed and values loaded from the reference copy must be equal."
		       + " At index=" + index + " values differ.",
		       computed.get(index),
		       is(expected.get(index)));
	}

    }

    /**
     * Tests the {@code refine()} method in class {@code Iago.EdgeTable}.
     *
     * @see Iago.EdgeTable#refine()
     */
    @Test
    public final void testEdgeTableRefine() {

	final Iago.EdgeTable computed = Iago.EdgeTable.load("rcrr/reversi/data/edge-table-st_CL_REFERENCE.dat");
	computed.refine();

	final Iago.EdgeTable expected = Iago.EdgeTable.load("rcrr/reversi/data/edge-table-00_Java.dat");

	for (int index = 0; index < Iago.EdgeTable.SIZE; index++) {
	    assertThat("Values computed and values loaded from the reference copy must be equal."
		       + " At index=" + index + " values differ.",
		       computed.get(index),
		       is(expected.get(index)));
	}
    }

    /**
     * Tests the {@code mobility(GamePosition)} method.
     *
     * @see Iago#mobility(GamePosition)
     */
    @Test
    public final void testMobility() {
        assertThat("new Iago().mobility(GamePositionFixtures.INITIAL).current() is 4.",
                   new Iago().mobility(GamePositionFixtures.INITIAL).current(),
                   is(4));
        assertThat("new Iago().mobility(GamePositionFixtures.INITIAL).potential() is 6.",
                   new Iago().mobility(GamePositionFixtures.INITIAL).potential(),
                   is(6));
        assertThat("new Iago().mobility(GamePositionFixtures.BLACK_HAS_TO_PASS).current() is 0.",
                   new Iago().mobility(GamePositionFixtures.BLACK_HAS_TO_PASS).current(),
                   is(0));
        assertThat("new Iago().mobility(GamePositionFixtures.BLACK_HAS_TO_PASS).potential() is 5.",
                   new Iago().mobility(GamePositionFixtures.BLACK_HAS_TO_PASS).potential(),
                   is(5));
    }

    @Test
    public final void testStaticEdgeStability()  throws NoSuchMethodException,
							IllegalAccessException,
							InvocationTargetException {
	assertThat("Iago.staticEdgeStability(Player.BLACK, BoardFixtures.BLACK_HAS_TO_PASS) must be -2725.",
		   staticEdgeStabilityProxy(Player.BLACK, BoardFixtures.BLACK_HAS_TO_PASS),
		   is(-2725));

	assertThat("Holding an edge has a value of 700.",
		   staticEdgeStabilityProxy(Player.BLACK,
					    new Board.Builder()
					    .withSquaresLiteral(1, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0)
					    .build()),
		   is(700));

	assertThat("Configuration (0 0 1 1 1 2 1 1 0 0) is valued -50.",
		   staticEdgeStabilityProxy(Player.BLACK,
					    new Board.Builder()
					    .withSquaresLiteral(0, 1, 1, 1, 2, 1, 1, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0)			    
					    .build()),
		   is(-50));

	assertThat("Configuration (0 0 1 1 1 0 1 1 0 0) is valued 1000.",
		   staticEdgeStabilityProxy(Player.BLACK,
					    new Board.Builder()
					    .withSquaresLiteral(0, 1, 1, 1, 0, 1, 1, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0,
								0, 0, 0, 0, 0, 0, 0, 0)
					    .build()),
		   is(1000));

    }

    /**
     * Tests the {@code pieceStability(Board, Square)} method.
     *
     * @see Iago.EdgeTable#pieceStability(Board, Square)
     */
    @Test
    public final void testPieceStability() {
	assertThat("Edge (0, 1, 1, 1, 0, 0, 2, 1, 2, 1), must return (1, 0, 0, 0, 0, 0, 2, 1, 0, 1).",
		   edgePieceStability(Arrays.asList(0, 1, 1, 1, 0, 0, 2, 1, 2, 1)),
		   is(Arrays.asList(null, 0, 0, 0, null, null, 2, 1, 0, 1)));
	assertThat("Edge (1, 2, 1, 0, 1, 0, 2, 0, 0, 1), must return (1, 0, 2, 1, 1, 0, 1, 0, 0, 2).",
		   edgePieceStability(Arrays.asList(1, 2, 1, 0, 1, 0, 2, 0, 0, 1)),
		   is(Arrays.asList(1, 0, 2, null, 1, null, 1, null, null, 2)));
    }

    /**
     * Tests the {@code Iago.EdgeTable.index(Player, Board, Iago.Edge)} method.
     *
     * @see Iago.EdgeTable#index(Player, Board, Iago.Edge)
     */
    @Test
    public final void testEdgeTableIndex() throws NoSuchMethodException,
						  IllegalAccessException,
						  InvocationTargetException {
        assertThat("Iago.EdgeTable.index(Player.BLACK, BoardFixtures.INITIAL, Iago.Edge.TOP) is 0.",
                   indexProxy(Player.BLACK, BoardFixtures.INITIAL, Iago.Edge.TOP),
                   is(0));
        assertThat("Iago.EdgeTable.index(Player.WHITE, BoardFixtures.INITIAL, Iago.Edge.TOP) is 0.",
                   indexProxy(Player.WHITE, BoardFixtures.INITIAL, Iago.Edge.TOP),
                   is(0));

        final Board fullEdge = new Board.Builder().withSquaresLiteral(1, 1, 1, 1, 1, 1, 1, 1,
                                                                      0, 1, 0, 0, 0, 0, 1, 0,
                                                                      0, 0, 0, 0, 0, 0, 0, 0,
                                                                      0, 0, 0, 0, 0, 0, 0, 0,
                                                                      0, 0, 0, 0, 0, 0, 0, 0,
                                                                      0, 0, 0, 0, 0, 0, 0, 0,
                                                                      0, 0, 0, 0, 0, 0, 0, 0,
                                                                      0, 0, 0, 0, 0, 0, 0, 0).build();

        assertThat("When all the squares in the edge are occupied by the PLAYER the value must be 29524.",
                   indexProxy(Player.BLACK, fullEdge, Iago.Edge.TOP),
                   is(29524));
        assertThat("When all the squares in the edge are occupied by the OPPONENT the value must be 59048.",
                   indexProxy(Player.WHITE, fullEdge, Iago.Edge.TOP),
                   is(59048));

        assertThat("Iago.EdgeTable.index(Player.BLACK, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.TOP) is 35290.",
                   indexProxy(Player.BLACK, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.TOP),
                   is(35290));
        assertThat("Iago.EdgeTable.index(Player.WHITE, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.TOP) is 50816.",
                   indexProxy(Player.WHITE, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.TOP),
                   is(50816));
        assertThat("Iago.EdgeTable.index(Player.BLACK, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.BOTTOM) is 39355.",
                   indexProxy(Player.BLACK, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.BOTTOM),
                   is(39355));
        assertThat("Iago.EdgeTable.index(Player.WHITE, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.BOTTOM) is 49217.",
                   indexProxy(Player.WHITE, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.BOTTOM),
                   is(49217));
        assertThat("Iago.EdgeTable.index(Player.BLACK, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.LEFT) is 34999.",
                   indexProxy(Player.BLACK, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.LEFT),
                   is(34999));
        assertThat("Iago.EdgeTable.index(Player.WHITE, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.LEFT) is 50306.",
                   indexProxy(Player.WHITE, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.LEFT),
                   is(50306));
        assertThat("Iago.EdgeTable.index(Player.BLACK, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.RIGHT) is 26224.",
                   indexProxy(Player.BLACK, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.RIGHT),
                   is(26224));
        assertThat("Iago.EdgeTable.index(Player.WHITE, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.RIGHT) is 42638.",
                   indexProxy(Player.WHITE, BoardFixtures.BLACK_HAS_TO_PASS, Iago.Edge.RIGHT),
                   is(42638));
    }

    @Test
    public final void testCountEdgeNeighbors() {

	assertThat("countEdgeNeighbors(BLACK, BLACK_HAS_TO_PASS, C1) is 2.",
		   Iago.EdgeTable.countEdgeNeighbors(Player.BLACK,
						     BoardFixtures.BLACK_HAS_TO_PASS,
						     Square.C1),
		   is(2));

	assertThat("countEdgeNeighbors(WHITE, BLACK_HAS_TO_PASS, C1) is 0.",
		   Iago.EdgeTable.countEdgeNeighbors(Player.WHITE,
						     BoardFixtures.BLACK_HAS_TO_PASS,
						     Square.C1),
		   is(0));

	assertThat("countEdgeNeighbors(BLACK, BLACK_HAS_TO_PASS, E1) is 1.",
		   Iago.EdgeTable.countEdgeNeighbors(Player.BLACK,
						     BoardFixtures.BLACK_HAS_TO_PASS,
						     Square.E1),
		   is(1));

	assertThat("countEdgeNeighbors(WHITE, BLACK_HAS_TO_PASS, E1) is 1.",
		   Iago.EdgeTable.countEdgeNeighbors(Player.WHITE,
						     BoardFixtures.BLACK_HAS_TO_PASS,
						     Square.E1),
		   is(1));

	assertThat("countEdgeNeighbors(BLACK, BLACK_HAS_TO_PASS, G1) is 0.",
		   Iago.EdgeTable.countEdgeNeighbors(Player.BLACK,
						     BoardFixtures.BLACK_HAS_TO_PASS,
						     Square.G1),
		   is(0));

	assertThat("countEdgeNeighbors(WHITE, BLACK_HAS_TO_PASS, G1) is 1.",
		   Iago.EdgeTable.countEdgeNeighbors(Player.WHITE,
						     BoardFixtures.BLACK_HAS_TO_PASS,
						     Square.G1),
		   is(1));

	assertThat("countEdgeNeighbors(BLACK, BLACK_HAS_TO_PASS, H1) is 0.",
		   Iago.EdgeTable.countEdgeNeighbors(Player.BLACK,
						     BoardFixtures.BLACK_HAS_TO_PASS,
						     Square.H1),
		   is(0));

	assertThat("countEdgeNeighbors(WHITE, BLACK_HAS_TO_PASS, H1) is 0.",
		   Iago.EdgeTable.countEdgeNeighbors(Player.WHITE,
						     BoardFixtures.BLACK_HAS_TO_PASS,
						     Square.H1),
		   is(0));

    }

    @Test
    public final void testEdgeMoveProbability() {

	List<Double> probabilities = new ArrayList<Double>();
	for (Square sq : Iago.Edge.TOP.squares()) {
	    probabilities.add(Iago.EdgeTable.edgeMoveProbability(Player.WHITE,
								 BoardFixtures.BLACK_HAS_TO_PASS,
								 sq));
	}

	assertThat("Edge move probabilities for Player.WHITE on BoardFixtures.BLACK_HAS_TO_PASS are:"
		   + " (0.50, 0.90, 0.05, 1.00, 0.10, 1.00, 0.10, 1.00, 1.00, 0.50).",
		   probabilities,
		   is(Arrays.asList(0.50, 0.90, 0.05, 1.00, 0.10, 1.00, 0.10, 1.00, 1.00, 0.50)));

    }

    @Test
    public final void testSortPossibilities() {

	final Iago.ProbabilityValue one =       new Iago.ProbabilityValue(1.0, 1);
	final Iago.ProbabilityValue two =       new Iago.ProbabilityValue(2.0, 2);
	final Iago.ProbabilityValue three =     new Iago.ProbabilityValue(3.0, 3);
	final Iago.ProbabilityValue four =      new Iago.ProbabilityValue(4.0, 4);
	final Iago.ProbabilityValue fourAgain = new Iago.ProbabilityValue(4.4, 4);
	final Iago.ProbabilityValue five =      new Iago.ProbabilityValue(2.0, 5);

	Comparator<Iago.ProbabilityValue> gt = Iago.ProbabilityValue.GT;
	Comparator<Iago.ProbabilityValue> lt = Iago.ProbabilityValue.LT;

	List<Iago.ProbabilityValue> possibilities = Arrays.asList(two,
								  one,
								  five,
								  four,
								  fourAgain,
								  three);

	List<Iago.ProbabilityValue> sortedUp = Iago.ProbabilityValue.sortPossibilities(possibilities, gt);
	List<Iago.ProbabilityValue> sortedDown = Iago.ProbabilityValue.sortPossibilities(possibilities, lt);

	List<Iago.ProbabilityValue> expectedGt = Arrays.asList(one,
							       two,
							       three,
							       four,
							       fourAgain,
							       five);

	List<Iago.ProbabilityValue> expectedLt = Arrays.asList(five,
							       four,
							       fourAgain,
							       three,
							       two,
							       one);

	assertThat("Sorting from the most valued to the lesser one.",
		   sortedUp,
		   is(expectedGt));

	assertThat("Sorting from the lesser valued to the most one.",
		   sortedDown,
		   is(expectedLt));

    }

    @Test
    public final void testCombineEdgeMoves() {

	assertThat("[(1.0 5800) (0.5 5800)] BLACK must be 5800.",
		   Iago.EdgeTable.combineEdgeMoves(Arrays.asList(new Iago.ProbabilityValue(1.0, 5800),
								 new Iago.ProbabilityValue(0.5, 5800)),
						   Player.BLACK),
		   is(5800));

	assertThat("[(1.0 5800) (0.5 5800)] WHITE must be 5800.",
		   Iago.EdgeTable.combineEdgeMoves(Arrays.asList(new Iago.ProbabilityValue(1.0, 5800),
								 new Iago.ProbabilityValue(0.5, 5800)),
						   Player.WHITE),
		   is(5800));

	assertThat("[(1.0 2075) (0.005 4000)] BLACK must be 2085.",
		   Iago.EdgeTable.combineEdgeMoves(Arrays.asList(new Iago.ProbabilityValue(1.000, 2075),
								 new Iago.ProbabilityValue(0.005, 4000)),
						   Player.BLACK),
		   is(2085));

	assertThat("[(1.0 2075) (0.005 4000)] WHITE must be 2075.",
		   Iago.EdgeTable.combineEdgeMoves(Arrays.asList(new Iago.ProbabilityValue(1.000, 2075),
								 new Iago.ProbabilityValue(0.005, 4000)),
						   Player.WHITE),
		   is(2075));

	assertThat("[(1.0 5100) (0.001 7800)] BLACK must be 5103.",
		   Iago.EdgeTable.combineEdgeMoves(Arrays.asList(new Iago.ProbabilityValue(1.000, 5100),
								 new Iago.ProbabilityValue(0.001, 7800)),
						   Player.BLACK),
		   is(5103));

    }

    @Test
    public final void testPossibleEdgeMovesValue() {

        final List<Integer> edgeTable = new ArrayList<Integer>(Iago.EdgeTable.SIZE);
        for (int idx = 0; idx < Iago.EdgeTable.SIZE; idx++) {
            edgeTable.add(idx);
        }
	Iago.EdgeTable table = new Iago.EdgeTable(edgeTable);

	int value = table.possibleEdgeMovesValue(Player.WHITE,
						 BoardFixtures.BLACK_HAS_TO_PASS,
						 50816);

	assertThat("This has been tested comparing the common lisp return value.",
		   value,
		   is(-38935));

    }

    @Test
    public final void testPossibleEdgeMove() {

        final List<Integer> edgeTable = new ArrayList<Integer>(Iago.EdgeTable.SIZE);
        for (int idx = 0; idx < Iago.EdgeTable.SIZE; idx++) {
            edgeTable.add(idx);
        }
	Iago.EdgeTable table = new Iago.EdgeTable(edgeTable);

	Iago.ProbabilityValue pv = table.possibleEdgeMove(Player.WHITE,
							  BoardFixtures.BLACK_HAS_TO_PASS,
							  Square.C1);

	assertThat("Iago.EdgeTable.possibleEdgeMove(...) must have a return pv.value() of -38935.",
		   pv.value(),
		   is(-38935));

	assertThat("Iago.EdgeTable.possibleEdgeMove(...) must have a return pv.probability() of 1.0.",
		   pv.probability(),
		   is(1.0));

    }

    private final Board topEdgeLiteral(final List<Integer> edge) {
	if (edge.size() != 10) { throw new IllegalArgumentException("Parameter edge has a wrong size."); }
	Integer[] board = new Integer[64];
	for (int idx = 0; idx < 64; idx++) {
	    board[idx] = 0;
	}
	board[9] = edge.get(0);
	board[14] = edge.get(9);
	for (int idx = 1; idx < 9; idx++) {
	    board[idx - 1] = edge.get(idx);
	}
	return new Board.Builder().withSquaresLiteral(board).build();
    }

    private final List<Integer> edgePieceStability(final List<Integer> edge) {
	List<Integer> result = new ArrayList<Integer>();
	Board board = topEdgeLiteral(edge);
	for (Square sq : Iago.Edge.TOP.squares()) {
	    result.add(Iago.EdgeTable.pieceStability(board, sq));
	}
	return result;
    }

    private static int indexProxy(final Player player,
				  final Board board,
				  final Iago.Edge edge)
        throws NoSuchMethodException,
               IllegalAccessException,
               InvocationTargetException {

        final Method method = Iago.EdgeTable.class.getDeclaredMethod("index",
								     Player.class,
								     Board.class,
								     Iago.Edge.class);
        method.setAccessible(true);
	int result = (Integer) method.invoke(null, player, board, edge);
        return result;
    }

    private static int staticEdgeStabilityProxy(final Player player,
						final Board board)
	throws NoSuchMethodException,
	       IllegalAccessException,
	       InvocationTargetException {

        final Method method = Iago.EdgeTable.class.getDeclaredMethod("staticEdgeStability",
								     Player.class,
								     Board.class);
        method.setAccessible(true);
	int result = (Integer) method.invoke(null, player, board);
	return result;
    }

    private static Iago.EdgeTable computeStaticProxy() throws NoSuchMethodException,
							      IllegalAccessException,
							      InvocationTargetException {

        final Method method = Iago.EdgeTable.class.getDeclaredMethod("computeStatic");
        method.setAccessible(true);
        return (Iago.EdgeTable) method.invoke(null);
    }

}
