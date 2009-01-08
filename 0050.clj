(load-file "primes.clj")

(defn problem-fifty [limit]
  (let [sieve (primes/sieve 1000000)]
    (loop [s 0 n 1 max_val 0 max_n 1 max_s 0]
      (let [v (math/sum (take n (drop s sieve)))]
	(if (< limit v)
	  (if (= n max_n)
	    (list "value:" max_val "consecutive" max_n "start" max_s)	      
	    (recur (inc s) max_n max_val max_n max_s))
	  (if (and (primes/prime? v sieve) (< max_n n))
	    (recur s (inc n) v n s)
	    (recur s (inc n) max_val max_n max_s)))))))