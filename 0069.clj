(load-file "primes.clj")

(def sieve (primes/sieve 1000000))

(defn very-slow []
(println
(time
(loop [n 3 largest-n 2 value 2 remaining-primes sieve]
  (when (zero? (rem n 1000))
    (println n largest-n value))
  (if (> n 1000000)
    largest-n
    (if (= n (first remaining-primes)) ; assume the value isn't going to be a prime
      (recur (inc n) largest-n value (rest remaining-primes))
      (let [ndivphi (/ n (math/phi n sieve))]
	(if (< value ndivphi)
	  (recur (inc n) n ndivphi remaining-primes)
	  (recur (inc n) largest-n value remaining-primes))))))
)))

;;; attempt 2

(defn phi [n phi-map]
  (if (< n 2)
    0
    (if (<= n (count phi-map))
      (get phi-map n)
      (loop [i (math/floor (/ n 2))]
	(if (< i 2)
	  (let [r (math/phi n)]
	    (println "failed to find phi for" n "=" r)
	    r)
	  (if (zero? (rem n i))
	    (if (math/coprime? i (/ n i) sieve)
	      (* (get phi-map i) (get phi-map (/ n i)))
	      (recur (dec i)))
	    (recur (dec i))))))))

(defn still-very-slow []
(println
(time
(loop [n 11 largest-n 6 value 3 remaining-primes (drop-while #(< % 11) sieve) phi-map 
     {2 1
      3 2
      4 2
      5 4
      6 2
      7 6
      8 4
      9 6
      10 4}]
  (when (zero? (rem n 1000))
    (println n largest-n value))
  ;(println "n =" n)
  (if (> n 1000000)
    largest-n
    (if (= n (first remaining-primes)) ; assume the value isn't going to be a prime
      (recur (inc n) largest-n value (rest remaining-primes) (assoc phi-map n (dec n)))
      (let [phin (phi n phi-map)]
	;(println "phi(n) =" phin)
	(let [ndivphi (/ n phin)]
	  ;(println "n/phi(n) =" ndivphi)
	  (if (< value ndivphi)
	    (recur (inc n) n ndivphi remaining-primes (assoc phi-map n phin))
	    (recur (inc n) largest-n value remaining-primes (assoc phi-map n phin))))))))
)))

;;; attempt 3

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

(loop [n 1]
  (if (< 10 n)
    nil
    (do
      (println n (phi n))
      (recur (inc n)))))

; fastest so far... but still slow...
(defn still-slow []
(println
(time
(loop [n 3 largest-n 2 value 1 remaining-primes (drop-while #(< % 3) sieve)]
  (when (zero? (rem n 1000))
    (println n largest-n value))
  ;(println "n =" n)
  (if (> n 1000000)
    largest-n
    (if (= n (first remaining-primes)) ; assume the value isn't going to be a prime
      (recur (inc n) largest-n value (rest remaining-primes))
      (let [phin (phi n)]
	;(println "phi(n) =" phin)
	(let [ndivphi (/ n phin)]
	  ;(println "n/phi(n) =" ndivphi)
	  (if (< value ndivphi)
	    (recur (inc n) n ndivphi remaining-primes)
	    (recur (inc n) largest-n value remaining-primes)))))))
))
)

; idea taken from forum after waiting forever for the above code to give me an answer
; for n/phi(n) to be max, it has to be the product of all primes

(println
(time
(loop [prod 1 remaining-primes sieve]
  (let [a (* prod (first remaining-primes))]
    (if (< 1000000 a)
      prod
      (recur a (rest remaining-primes)))))
))