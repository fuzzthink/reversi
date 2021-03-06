/**
 * @file
 *
 * @brief Game tree logger module definitions.
 * @details This module defines the functions used to log the game tree.
 *
 * @par game_tree_logger.h
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

#ifndef GAME_TREE_LOGGER_H
#define GAME_TREE_LOGGER_H

#include <glib.h>

#include "board.h"



/**
 * @brief Environment in wich the logger operates.
 */
typedef struct {
  gboolean   log_is_on;        /**< @brief True when logging is turned on. */
  gchar     *file_name_prefix; /**< @brief The log file name prefix received by the caller. */
  gchar     *h_file_name;      /**< @brief The complete nmae for the head file. */
  gchar     *t_file_name;      /**< @brief The complete name for the tail file. */
  FILE      *h_file;           /**< @brief Head file. */
  FILE      *t_file;           /**< @brief Tail file. */
} LogEnv;

/**
 * @brief It is collecting the info logged into a record by the head write function.
 */
typedef struct {
  int        sub_run_id;  /**< @brief Sub run id field. */
  uint64_t   call_id;     /**< @brief Call id. */
  uint64_t   hash;        /**< @brief Game position hash. */
  uint64_t   parent_hash; /**< @brief Parent game position hash. */
  SquareSet  blacks;      /**< @brief Blacks field part of the game position. */
  SquareSet  whites;      /**< @brief Whites field part of the game position. */
  Player     player;      /**< @brief Player field part of the game position. */
  gchar     *json_doc;    /**< @brief Json field. */
} LogDataH;

/**
 * @brief It is collecting the info logged into a record by the tail write function.
 */
typedef struct {
  int        sub_run_id;  /**< @brief Sub run id field. */
  uint64_t   call_id;     /**< @brief Call id. */
  gchar     *json_doc;    /**< @brief Json field. */
} LogDataT;



/********************************************************/
/* Function implementations for the GameTreeLog entity. */ 
/********************************************************/

extern void
game_tree_log_open_h (LogEnv * const env);

extern void
game_tree_log_open_t (LogEnv * const env);

extern void
game_tree_log_write_h (const LogEnv   * const env,
                       const LogDataH * const data);

extern void
game_tree_log_write_t (const LogEnv   * const env,
                       const LogDataT * const data);

extern void
game_tree_log_close (LogEnv * const env);

extern LogEnv *
game_tree_log_init (const gchar * const file_name_prefix);

extern gchar *
game_tree_log_data_h_json_doc (const int                  call_level,
                               const GamePosition * const gp);

#endif /* GAME_TREE_LOGGER_H */
