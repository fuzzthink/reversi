#!/bin/bash

PROG=./build/test/bin/exact_solver_test
#ARGS="./build/bin/gpdb_verify -f db/gpdb-test-db.txt -q duplicate-entry"
ARGS="./build/bin/gpdb_verify -f db/gpdb-sample-games.txt -q empty"


G_SLICE=always-malloc G_DEBUG=gc-friendly \
valgrind -v --tool=memcheck \
            --leak-check=full \
            --num-callers=40 \
            --log-file=valgrind.log \
            --track-origins=yes \
            --leak-check=full \
            --show-reachable=yes \
            --gen-suppressions=all \
            --suppressions=minimal.supp \
              $(which $PROG) $ARGS


# how to generate the suppression file ....
# cat ./minimalraw.log | ./parse_valgrind_suppressions.sh > minimal.supp
# valgrind --leak-check=full --show-reachable=yes --error-limit=no --gen-suppressions=all --log-file=minimalraw.log ./minimal
