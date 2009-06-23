(load-file "primes.clj")
(load-file "list-lib.clj")
(load-file "pandigital.clj")

; TODO: this is slow, optimise
(defn problem-forty-three []
  (let [sieve (primes/sieve 100) limit (math/! 10)]
    (loop [n 0 vals '()]
      (if (< limit n)
	(math/sum vals)
	(let [p (get-permutation n 0 9)]
	  (if (loop [rp (drop 1 p) pr sieve]
		(if (zero? (rem (list-to-number (take 3 rp)) (first pr)))
		  (if (= 17 (first pr))
		    true
		    (recur (drop 1 rp) (drop 1 pr)))
		  false))
	    (do
	      (println p)
	      (recur (inc n) (cons (list-to-number p) vals)))
	    (recur (inc n) vals)))))))
		    
	