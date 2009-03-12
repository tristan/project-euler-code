(require '(clojure.contrib [math :as math]))
(require '(libs [math :as my-math]))
(load-file "primes.clj")

; 11^2 + 2^3 + 2^4 = 145
; 2^2 + 5^3 + 2^4 = 145
; 210 

(def limit 50000000)
(def sqrtlimit (math/sqrt limit))
(println
 sqrtlimit
)
(def primes (sieve (math/floor sqrtlimit)))
(println
 primes
)

(println (time
(loop [c primes n #{}]
  (let [r (loop [b primes n n]
	    (let [r (loop [a primes n n]
		      (if (or (empty? a) (>= (+ (* (first a) (first a))
						(* (first b) (first b) (first b))
						(* (first c) (first c) (first c) (first c)))
					     limit))
			n
			(recur (rest a) (conj n
					      (+ (* (first a) (first a))
						 (* (first b) (first b) (first b))
						 (* (first c) (first c) (first c) (first c))
						 )))))]
	      (if (or (empty? (rest b)) (>= (+ (* (first primes) (first primes))
					       (* (first (rest b)) (first (rest b)) (first (rest b)))
					       (* (first c) (first c) (first c) (first c)))
					    limit))
		r
		(recur (rest b) r))))]
    (if (or (empty? (rest c)) (>= (+ (* (first primes) (first primes))
				     (* (first primes) (first primes) (first primes))
				     (* (first (rest c)) (first (rest c)) (first (rest c)) (first (rest c))))
				  limit))
      (count r)
      (recur (rest c) r))))
))