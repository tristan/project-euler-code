(load "primes")

(defn fn0 []
  (first (drop 10000 (wheel-primes))))

(defn fn1 []
  (first (drop 10000 (hashtable-primes))))

(def solutions (list fn0 fn1))