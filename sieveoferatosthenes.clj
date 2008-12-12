(defn strike-multiples-from-sieve [sieve multiplyer]
  (loop [s sieve x multiplyer]
     (if (< (count sieve) x)
       s
       (recur (assoc s (- x 1) false) (+ x multiplyer))
       )))

(defn find-first-true [vec start]
  (loop [n start]
    (if (< (count vec) n)
      (inc (count vec)) ;if we don't find a 'true' return a number larger than the length of the vector
      (if (= (nth vec (- n 1)) true)
	n
	(recur (inc n))))))

(defn build-base-sieve [size]
  (if (< size 1)
    (vector)
    (loop [sieve (vector false) len (dec size)]
      (if (< len 1)
	sieve
	(recur (assoc sieve (count sieve) true) (dec len))))))

(defn sieve-of-eratosthenes [max]
  (if (< max 2)
    (vector)
    (loop [sieve (build-base-sieve max) primes (vector)]
      (if (and (not= (last primes) nil) (< max (last primes)))
	(pop primes)
	(let [new-prime (find-first-true sieve (if (not= (last primes) nil) (last primes) 1))]
	  (recur (if (> new-prime (. Math (sqrt (float max))))
		   (assoc sieve (dec new-prime) false)
		   (strike-multiples-from-sieve sieve new-prime))
		 (assoc primes (count primes) new-prime)))))))
		 					    
(sieve-of-eratosthenes 1000000)
;;(println (strike-multiples-from-sieve (list true true true true true true true true true true true) 2))