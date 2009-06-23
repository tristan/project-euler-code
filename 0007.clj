(load-file "sieveoferatosthenes.clj")

(defn find-the-nth-prime [n]
  (let [initial-primes (vector 2 3 5 7 11 13)]
    (loop [primes initial-primes]
      (if (> (count primes) n)
	(nth primes (dec n))
	(recur (expand-vector-of-primes primes (* n 12)))))))
;; todo: make function to specify limit to number of primes rather than limit to size
;; this is prevent the use of the by-trial value 12 used in the (* n 12)
;; until then this function cannot be considered correct

(defn problem-seven-example []
  (= (find-the-nth-prime 6) 13))

;(println (problem-seven-example))

(println (find-the-nth-prime 10001)) ; 104743