(load-file "primes.clj")

(let [sieve (primes/sieve 1000000)]
  (time
  (loop [n 3 it 5 primes 1 numbers 3]
    (let [is-prime? (primes/prime? it sieve)]
      (if (= it (* n n))
	(do
	  ;(println n it primes numbers (* 100.0 (/ primes numbers)))
	  (if (> 10.0 (* 100.0 (/ (if is-prime? (inc primes) primes) numbers)))
	    (println n it primes numbers)
	    (recur (+ n 2) 
		   (+ it (inc n)) 
		   (if is-prime? (inc primes) primes) 
		   (inc numbers))))
	(recur n 
	       (+ it (dec n)) 
	       (if is-prime? (inc primes) primes) 
	       (inc numbers))))))
    )