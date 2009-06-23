(load-file "math.clj")
(load-file "primes.clj")

(def prime-list (primes/sieve 1000000))

(defn prime? [nbr]
  (primes/prime? nbr prime-list))

(println (time
(apply #'+ 
(loop [primes (drop-while #(< % 10) prime-list) results '()]
  (if (nil? primes)
    (do
      (println "need more primes!")
      results)
    (if (= (count results) 11)
      (do
	(println "got all results!")
	results)
      (recur (rest primes)
	     (if (let [p (first primes)]
		   (loop [size-up 1]
		     (let [v (rem (first primes) (math/pow 10 size-up))]
		       ;(println p "." size-up v)
		       (if (= v p)
			 (loop [size-down (dec size-up)]
			   (let [u (/ (- p (rem p (math/pow 10 size-down))) (math/pow 10 size-down))]
			     ;(println p "." size-down u)
			     (if (< size-down 1)
			       true
			       (if (prime? u)
				 (recur (dec size-down))
				 false))))
			 (if (prime? v)
			   (recur (inc size-up))
			   false)))))
	       (cons (first primes) results)
	       results)))))
)))