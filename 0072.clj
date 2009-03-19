(require '(clojure.contrib [math :as contrib-math]))
(require '(libs [math :as math]))
(load-file "primes.clj")

(time (def prime-sieve (sieve 1000000)))

; very slow without prime sieve!
; $ clojure 0072.clj
; "Elapsed time: 1.45513124087E7 msecs"
; 303963552391
; with prime sieve quicker, but still too slow
; $ clojure 0072.clj
; "Elapsed time: 33.634722 msecs"
; "Elapsed time: 119379.160201 msecs"
; 303963552391
(defn solve-using-eulers-totient [d]
  (loop [i 2 sum 0]
    (if (<= i d)
      (recur (inc i) (+ sum (math/eulers-totient i prime-sieve)))
      sum)))

;(println (time (solve-using-eulers-totient 1000000)))

; solves in ~38 seconds on average.
(defn solve-using-sieve [limit]
  (let [phis
	(loop [n 2 phis {}]
	       (if (> n limit)
		 phis
		 (recur (inc n)
			(assoc phis n n))))]
    (apply #'+ (vals
		(loop [n 2 phis phis]
		  (if (> n limit)
		    phis
		    (recur
		     (inc n)
		     (if (= n (get phis n))
		       (loop [m n phis phis]
			 (if (> m limit)
			   phis
			   (recur (+ m n) (assoc phis m (- (get phis m) (/ (get phis m) n))))))
		       phis))))))))

;(println (time (solve-using-sieve 1000000)))

(defn get-lowest-prime-factor [n]
  (loop [p prime-sieve]
    (if (zero? (rem n (first p)))
      (first p)
      (recur (rest p)))))

(defn solve [limit]
  (apply #'+ (vals
	      (loop [n 2 phis {} primes-left prime-sieve]
		(if (> n limit)
		  phis
		  (if (= n (first primes-left))
		    (recur (inc n) (assoc phis n (dec n)) (rest primes-left))
		    (recur (inc n)
			   (let [p (get-lowest-prime-factor n)]
			     (let [m (/ n p)]
			       (assoc phis n (* (get phis m)
						(if (zero? (rem m p)) p (dec p))))))
			   primes-left)))))))

; much faster, but only if primes are generated in advance, the extra time to compute primes
; makes the time saved from the sieve version only a few seconds
(println (time (solve 1000000)))
		 