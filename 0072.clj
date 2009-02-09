(load-file "primes.clj")

(time
(do
  (def sieve (primes/sieve 1000000))
  (println "done generating" (count sieve) "primes")))

(defn list-prime-factors 
  [n prime-sieve]
  (let [lim (math/ceil (/ n 2))]
    (loop [r '() remaining-primes prime-sieve]
      (if (> (first remaining-primes) lim)
	r
	(recur
	 (if (zero? (rem n (first remaining-primes)))
	   (cons (first remaining-primes) r)
	   r)
	 (rest remaining-primes))))))

(defn phi [n]
  (let [r (* n (apply #'* (map #(- 1 (/ 1 %)) (list-prime-factors n sieve))))]
    (if (= r n)
      (dec r)
      r)))

(defn reduce-fraction [denominator]
  (loop [n (dec denominator) cnt 0]
    (if (< n 1)
      cnt
      (recur (dec n)
	     (if (= 1 (math/gcd n denominator))
	       (inc cnt)
	       cnt)))))

;(println (time (phi 1000000)))
;(println (time (reduce-fraction 1000000)))

;;; FAILED!!

(defn count-reduced [d]
  (loop [n (dec d) cnt 0]
    (if (< n 2)
      (inc cnt)
      (recur (dec n) (if (zero? (rem d n)) cnt (inc cnt))))))

(println (time
(loop [n 1000000 cnt 0 remaining-primes (reverse sieve)]
  (println n cnt)
  (if (> 2 n)
    cnt
    (if (= n (first remaining-primes))
      (recur (dec n) (+ cnt (count (rest remaining-primes))) (rest remaining-primes))
      (recur (dec n) (+ cnt (phi n)) remaining-primes))))
))