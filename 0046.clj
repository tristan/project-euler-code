(load-file "primes.clj")

(defn problem-forty-six []
  (let [sieve (primes/sieve 10000)]
	(loop [restprimes (drop 4 sieve) n 9]
	      ;(print "testing:" n)
	      (if (= n (first restprimes))
		  (do
		      ;(println " ... prime!")
		      (recur (rest restprimes) (+ n 2)))
		(let [r (loop [p 1]
			      (let [t (- n (* 2 (* p p)))]
				   (if (< t 0)
				       n
				     (if (primes/prime? t sieve)
					 (do
					     ;(println " ..." n "=" t "+ 2 *" p "^2")
					     nil)
				       (recur (inc p))))))]
				       (if (nil? r)
					   (recur restprimes (+ n 2))
					 r))))))