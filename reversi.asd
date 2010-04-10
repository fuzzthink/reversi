;;;
;;; Copyright (c) 1998-2002 Peter Norvig
;;; Copyright (c) 2009-2010 Roberto Corradini

;;; This file is part of the reversi program
;;; http://github.com/rcrr/reversi

;;; This pogram takes origin from the code
;;; described in the Peter Norvig's PAIP book (Paradigms of AI Programming).
;;; The internet location of the book is "http://norvig.com/paip.html"
;;; The page where to download the source code is "http://norvig.com/paip/README.html"
;;; The licence agreement page is located at "http://norvig.com/license.html"
;;; 
;;; Here the licence is reported:
;;; Copyright © 1998-2002 by Peter Norvig.

;;; Permission is granted to anyone to use this software, in source or object code form,
;;; on any computer system, and to modify, compile, decompile, run, and redistribute
;;; it to anyone else, subject to the following restrictions:
;;;   1. The author makes no warranty of any kind, either expressed or implied,
;;;      about the suitability of this software for any purpose.
;;;   2. The author accepts no liability of any kind for damages or other
;;;      consequences of the use of this software, even if they arise from
;;;      defects in the software.
;;;   3. The origin of this software must not be misrepresented,
;;;      either by explicit claim or by omission.
;;;   4. Altered versions must be plainly marked as such,
;;;      and must not be misrepresented as being the original software.
;;;      Altered versions may be distributed in packages
;;;      under other licenses (such as the GNU license). 
;;; If you find this software useful, it would be nice if you let me (peter@norvig.com)
;;; know about it, and nicer still if you send me modifications that you
;;; are willing to share. However, you are not required to do so.

;;; As stated in the original license, the modified version, this file, is
;;; distributed under the GNU GPL v3 license.

;;; This program is free software; you can redistribute it and/or modify it
;;; under the terms of the GNU General Public License as published by the
;;; Free Software Foundation; either version 3, or (at your option) any
;;; later version.

;;; This program is distributed in the hope that it will be useful,
;;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;;; GNU General Public License for more details.

;;; You should have received a copy of the GNU General Public License
;;; along with this program; if not, write to the Free Software
;;; Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA

(defpackage #:reversi (:use :cl :asdf))

(in-package :reversi)

(defsystem reversi
  :name "reversi"
  :version "0.0.0"
  :maintainer "Roberto Corradini <rob_corradini@yahoo.it>"
  :author "Roberto Corradini <rob_corradini@yahoo.it>"
  :licence "GNU Gpl v3"
  :description "Reversi Board Game"
  :long-description "Common Lisp implementation of the Reversi Board Game"
  :serial nil
  :depends-on ()
  :components ((:file "constants")
	       (:file "auxfns")
	       (:file "reversi"
		      :depends-on ("auxfns"
				   "constants"))
	       (:file "strategies"
		      :depends-on ("auxfns"
				   "constants"
				   "edge-table-utils"))
	       (:file "edge-table-utils"
		      :depends-on ("auxfns"
				   "constants"))))