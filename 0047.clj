(load-file "primes.clj")

; TODO: this is brute force, slow, need to optimise
(defn solver 
  ([n] (solver n (primes/sieve 100000)))
  ([n sieve]
     (loop [i 4 results '()]
       (if (>= (count results) n)
	 results
	 ;(if (primes/prime? i sieve)
	 ;  (recur (inc i) '())
	   (let [prime-factors (loop [s sieve factors '()]
				 (if (or (> (first s) (/ i 2)) (>= (count factors) n))
				   factors
				   (if (zero? (rem i (first s)))
				     (recur (rest s) (cons (first s) factors))
				     (recur (rest s) factors))))]
	     (if (>= (count prime-factors) n)
	       (if (and (not (nil? (first results))) (= (first (first results)) (dec i)))
		 (recur (inc i) (cons (list i prime-factors) results))
		 (recur (inc i) (list (list i prime-factors))))
	       (recur (inc i) '())))))));)

(defn count-prime-factors 
  ([i max-needed sieve] (count-prime-factors i max-needed (/ i 2) sieve 0))
  ([i max-needed limit s cnt]
     (if (or (> (first s) limit) (= cnt max-needed))
       cnt
       (if (zero? (rem i (first s)))
	 (recur i max-needed limit (rest s) (inc cnt))
	 (recur i max-needed limit (rest s) cnt)))))

; TODO: this runs quicker, but is still brute force...
(defn solver
  ([n] (solver n 4 0 0 (primes/sieve 100000)))
  ([n i cnt last sieve]
     (if (= cnt n)
       (- i n)
       ; checking if the number is prime is redundant, if it has 0 factors it's prime
       ; and having to check for primes means we run thru all values twice
       ;(if (primes/prime? i sieve)
	; (recur n (inc i) 0 0 sieve)
	 (if (= (count-prime-factors i n sieve) n)
	   (if (or (zero? last) (< last (dec i)))
	     (recur n (inc i) 1 i sieve)
	     (recur n (inc i) (inc cnt) i sieve))
	   (recur n (inc i) 0 0 sieve)))));)

(defn problem-forty-seven []
  (time (println (solver 2)))
  (time (println (solver 3)))
  (time (println (solver 4)))
)