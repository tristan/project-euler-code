(require '(clojure.contrib [math :as contrib-math]))
(require '(libs [math :as math]))
(load-file "primes.clj")

(time (def prime-sieve (sieve (contrib-math/sqrt 1000000))))

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

; slower for the moment.. probably due to map operations rather than array
(defn solve-using-sieve [limit]
  (apply #'+ (keys
	      (loop [n 2 phis {}]
		(if (> n limit)
		  phis
		  (recur
		   (inc n)
		   (if (nil? (get phis n))
		     (loop [m (+ n n) phis (assoc phis n (dec n))]
		       (if (> m limit)
			 phis
			 (let [old-m (get phis m)]
			   (let [old-m (if (nil? old-m) m old-m)]
			     (recur (+ m n) (assoc phis m (- old-m (/ old-m n)))))))))))))))

(println (time (solve-using-sieve 1000000)))