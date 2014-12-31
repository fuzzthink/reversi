/**
 * @file
 *
 * @brief Sort Utils module definitions.
 *
 * @details This module provides sorting functions.
 *
 * @par sort_utils.h
 * <tt>
 * This file is part of the reversi program
 * http://github.com/rcrr/reversi
 * </tt>
 * @author Roberto Corradini mailto:rob_corradini@yahoo.it
 * @copyright 2014 Roberto Corradini. All rights reserved.
 *
 * @par License
 * <tt>
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 3, or (at your option) any
 * later version.
 * \n
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * \n
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA
 * or visit the site <http://www.gnu.org/licenses/>.
 * </tt>
 */

#ifndef SORT_UTILS_H
#define SORT_UTILS_H

/**
 * @brief Function pointer type for comparing values.
 *
 * @details Within the forthcoming explanation `a`, and the same applies to `b`, is a shortcut
 *          for the more precise but also more verbose sentence: "the value referenced by pointer `a`".
 *
 *          Compare functions bring the complete information to be self-sufficient, and so the predicates
 *          appear to be redundant, but most sort functions are written expecting a proper predicate because
 *          the empirical evidence shows that sorting using a predicate is faster. The reason is rooted in the
 *          fact that the compare makes two comparisons, an a third one is done by the sort function itself.
 *          The predicates make just one compare, and the sorting function can use the return value as a Boolean.
 *          So for primitive values sorting using a compare function consumes three comparisons versus a single one
 *          executed when the sorting algorithm is designed on a predicate.
 *
 *          Anyhow the signature for predicates and compares is the same, and so
 *          function implementation can have different forms:
 *          - Compare: has to ensure that when `a` is equal `b`,
 *            zero is returned, when `a` is greater than `b` a positive integer,
 *            and otherwise a negative one.
 *          - EQ, equal to: has to ensure that when `a` is equal `b`,
 *            `TRUE` is returned, and `FALSE` otherwise.
 *          - LT, less than: has to ensure that when `a` is less than `b`,
 *            `TRUE` is returned, and `FALSE` otherwise.
 *          - LE, less than or equal to: has to ensure that when `a` is less than or equal `b`,
 *            `TRUE` is returned, and `FALSE` otherwise.
 *          - GT, greater than: has to ensure that when `a` is greater than `b`,
 *            `TRUE` is returned, and `FALSE` otherwise.
 *          - GE, greater than or equal to: has to ensure that when `a` is greater than or equal `b`,
 *            `TRUE` is returned, and `FALSE` otherwise.
 *
 * @param [in] a a pointer to the first value
 * @param [in] b a pointer to the second value
 * @return       result of comparing the values pointed by `a` and `b`
 */
typedef int
(*sort_utils_compare_function) (const void *const a,
                                const void *const b);

/**
 * @brief Function pointer type for sorting arrays.
 *
 * @details This prototype is equal to the qsort definition
 *          in the standard library.
 *
 * @param [in,out] a            the array to be sorted
 * @param [in]     count        the number of element in array
 * @param [in]     element_size the number of bytes used by one element
 * @param [in]     cmp          the compare function applied by the algorithm
 */
typedef void
(*sort_utils_sort_function) (void *const a,
                             const size_t count,
                             const size_t element_size,
                             const sort_utils_compare_function cmp);



/********************************/
/* Compare function signatures. */
/********************************/

/* double */

extern int
sort_utils_double_cmp (const void *const a,
                       const void *const b);

extern int
sort_utils_double_icmp (const void *const a,
                        const void *const b);



/* int */

extern int
sort_utils_int_eq (const void *const a,
                   const void *const b);

extern int
sort_utils_int_gt (const void *const a,
                   const void *const b);

extern int
sort_utils_int_ge (const void *const a,
                   const void *const b);

extern int
sort_utils_int_lt (const void *const a,
                   const void *const b);

extern int
sort_utils_int_le (const void *const a,
                   const void *const b);

extern int
sort_utils_int_cmp (const void *const a,
                    const void *const b);

extern int
sort_utils_int_icmp (const void *const a,
                     const void *const b);



/* uint64_t */

extern int
sort_utils_uint64_t_eq (const void *const a,
                        const void *const b);

extern int
sort_utils_uint64_t_gt (const void *const a,
                        const void *const b);

extern int
sort_utils_uint64_t_ge (const void *const a,
                        const void *const b);

extern int
sort_utils_uint64_t_lt (const void *const a,
                        const void *const b);

extern int
sort_utils_uint64_t_le (const void *const a,
                        const void *const b);

extern int
sort_utils_uint64_t_cmp (const void *const a,
                         const void *const b);

extern int
sort_utils_uint64_t_icmp (const void *const a,
                          const void *const b);



/* int64_t */

extern int
sort_utils_int64_t_eq (const void *const a,
                       const void *const b);

extern int
sort_utils_int64_t_gt (const void *const a,
                       const void *const b);

extern int
sort_utils_int64_t_ge (const void *const a,
                       const void *const b);

extern int
sort_utils_int64_t_lt (const void *const a,
                       const void *const b);

extern int
sort_utils_int64_t_le (const void *const a,
                       const void *const b);

extern int
sort_utils_int64_t_cmp (const void *const a,
                        const void *const b);

extern int
sort_utils_int64_t_icmp (const void *const a,
                         const void *const b);


/***************************************/
/* Insertion-sort function prototypes. */
/***************************************/

extern void
sort_utils_insertionsort (void *const a,
                          const size_t count,
                          const size_t element_size,
                          const sort_utils_compare_function cmp);

extern void
sort_utils_insertionsort_asc_d (double *const a,
                                const int n);

extern void
sort_utils_insertionsort_dsc_d (double *const a,
                                const int count);

extern void
sort_utils_insertionsort_asc_i (int *const a,
                                const int count);

extern void
sort_utils_insertionsort_dsc_i (int *const a,
                                const int count);



/**********************************/
/* Heap-sort function prototypes. */
/**********************************/

extern void
sort_utils_heapsort (void *const a,
                     const size_t count,
                     const size_t element_size,
                     const sort_utils_compare_function cmp);

extern void
sort_utils_heapsort_asc_d (double *const a,
                           const int count);

extern void
sort_utils_heapsort_dsc_d (double *const a,
                           const int count);

extern void
sort_utils_heapsort_dsc_i (int *const a,
                           const int count);

extern void
sort_utils_heapsort_asc_i (int *const a,
                           const int count);



/************************************/
/* Smooth-sort function prototypes. */
/************************************/

extern void
sort_utils_smoothsort (void *const a,
                       const size_t count,
                       const size_t element_size,
                       const sort_utils_compare_function cmp);

extern void
sort_utils_smoothsort_asc_d (double *const a,
                             const int count);

extern void
sort_utils_smoothsort_dsc_d (double *const a,
                             const int count);

extern void
sort_utils_smoothsort_asc_i (int *const a,
                             const int count);

extern void
sort_utils_smoothsort_dsc_i (int *const a,
                             const int count);



/***********************************/
/* Quick-sort function prototypes. */
/***********************************/

extern void
sort_utils_quicksort (void *const a,
                      const size_t count,
                      const size_t element_size,
                      const sort_utils_compare_function cmp);

extern void
sort_utils_quicksort_asc_d (double *const a,
                            const int count);

extern void
sort_utils_quicksort_dsc_d (double *const a,
                            const int count);

extern void
sort_utils_quicksort_asc_i (int *const a,
                            const int count);

extern void
sort_utils_quicksort_dsc_i (int *const a,
                            const int count);



/***********************************/
/* Shell-sort function prototypes. */
/***********************************/

extern void
sort_utils_shellsort (void *const a,
                      const size_t count,
                      const size_t element_size,
                      const sort_utils_compare_function cmp);

extern void
sort_utils_shellsort_asc_d (double *const a,
                            const int count);

extern void
sort_utils_shellsort_dsc_d (double *const a,
                            const int count);

extern void
sort_utils_shellsort_asc_i (int *const a,
                            const int count);

extern void
sort_utils_shellsort_dsc_i (int *const a,
                            const int count);



/***********************************/
/* Merge-sort function prototypes. */
/***********************************/

extern void
sort_utils_mergesort (void *const a,
                      const size_t count,
                      const size_t element_size,
                      const sort_utils_compare_function cmp);

extern void
sort_utils_mergesort_a (void *const a,
                        const size_t count,
                        const size_t es,
                        const sort_utils_compare_function cmp,
                        void *const aux);

extern void
sort_utils_mergesort_asc_d (double *const a,
                            const int count);

extern void
sort_utils_mergesort_dsc_d (double *const a,
                            const int count);

extern void
sort_utils_mergesort_asc_i (int *const a,
                            const int count);

extern void
sort_utils_mergesort_dsc_i (int *const a,
                            const int count);



/***********************************/
/* Merge-sort function prototypes. */
/***********************************/

extern void
sort_utils_timsort (void *const a,
                    const size_t count,
                    const size_t element_size,
                    const sort_utils_compare_function cmp);



#endif /* SORT_UTILS_H */
