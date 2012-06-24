/*
 *  FileStateTest.java
 *
 *  Copyright (c) 2010, 2011, 2012 Roberto Corradini. All rights reserved.
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

package rcrr.reversi.board;

import org.junit.Test;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * Test Suite for {@code FileState} enum.
 */
public class FileStateTest {

    /** Class constructor. */
    public FileStateTest() { }

    @Test(expected = IndexOutOfBoundsException.class)
    public final void testValueOf_when_parameter_index_isInvalid_caseA() {
        FileState.valueOf(8, 6561);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public final void testValueOf_when_parameter_index_isInvalid_caseB() {
        FileState.valueOf(7, 6560);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public final void testValueOf_when_parameter_index_isInvalid_caseC() {
        FileState.valueOf(8, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public final void testValueOf_when_parameter_order_isInvalid_caseA() {
        FileState.valueOf(-1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public final void testValueOf_when_parameter_order_isInvalid_caseB() {
        FileState.valueOf(9, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public final void testValueOf_when_parameter_order_isInvalid_caseC() {
        FileState.valueOf(2, 0);
    }

    @Test
    public final void testValueOf() {
        assertThat("FileState.valueOf(3, 10) has to be an instance of FileState class.",
                   FileState.valueOf(3, 10),
                   instanceOf(FileState.class));
        assertThat("FileState.valueOf(8, 6560) has to be an instance of FileState class.",
                   FileState.valueOf(8, 6560),
                   instanceOf(FileState.class));
        assertThat("FileState.valueOf(5, 0) has to be an instance of FileState class.",
                   FileState.valueOf(5, 0),
                   instanceOf(FileState.class));
    }

    @Test
    public final void testToString() {
        assertThat("FileState.valueOf(3, 10).toString() has to return the appropriate string.",
                   FileState.valueOf(3, 10).toString(),
                   is("[(order=3, index=10) [ @ . @ ]]"));
    }


}
