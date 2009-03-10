(require '(clojure.contrib [math :as math]))
(require '(libs [math :as my-math]))
(require '(libs [list-utils :as list-utils]))
(load-file "primes.clj")

(def limit (math/expt 10 7))
;(println (time (def p (sieve (math/floor (math/sqrt 100000000))))))
(time (def prime-sieve (sieve (math/expt 10 7))))

(defn solve-forward []
  (println (time
	    (loop [n 2239261 r nil p (drop-while #(< % 2239261) prime-sieve)]
	      (let [is-n-prime? (= (first p) n)]
		(if (>= n limit)
		  r
		  (recur (inc n)
			 (if is-n-prime?
			   (do
			     (println "." n)
			     r)
			   (let [i (my-math/eulers-totient n)]
			     (if (or (nil? r) (< (/ n i) r))
			       (if (= (sort (list-utils/number-to-list i)) (sort (list-utils/number-to-list n)))
				 (do
				   (println "n:" n "phi(n):" i "..." (/ n i) r (if (nil? r) r (< (/ n i) r)))
				   (/ n i))
				 r)
			       r)))
			 (if is-n-prime?
			   (rest p)
			   p)
		       )))
	    ))))

(defn solve-backward []
  (println (time
	    (loop [n limit r nil]
	      (if (< n 2)
		r
		(recur (dec n)
		       (let [i (my-math/eulers-totient n)]
			 (if (or (nil? r) (< (/ n i) r))
			   (if (= (sort (list-utils/number-to-list i)) (sort (list-utils/number-to-list n)))
			     (do
			       (println "n:" n "phi(n):" i "..." (/ n i) r (< (/ n i) (if (nil? r) 1 r)))
			       (/ n i))
			     r)
			   r))
		       )))
	    )))

(solve-forward)
;(solve-backward)
; n: 49435 phi(n): 39544
;n: 609714 phi(n): 174096