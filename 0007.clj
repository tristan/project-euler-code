(use '[clojure.contrib.lazy-seqs :only (primes)])

; clojure contrib making this one a piece of cake!
(println (first (drop 10000 primes))) ; 104743