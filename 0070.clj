(require '(clojure.contrib [math :as math]))
(require '(libs [math :as my-math]))
(require '(libs [list-utils :as list-utils]))
(load-file "primes.clj")

(def start 2)
(def limit (math/sqrt 4268929))
(println
(time (def prime-sieve (sieve limit)))
)

; took over 153 CPU hours to get to the solution
(defn solve-forward []
  (println (time
	    (loop [n start r nil p (drop-while #(< % start) prime-sieve)]
	      (let [is-n-prime? (= (first p) n)]
		(if (>= n limit)
		  r
		  (recur (inc n)
			 (if is-n-prime?
			   (do
			     ;(println "." n)
			     r)
			   (let [i (my-math/eulers-totient n)]
			     (if (and (or (nil? r) (< (/ n i) r)) (= (sort (list-utils/number-to-list i)) (sort (list-utils/number-to-list n))))
			       (do
				 ;(println "n:" n "phi(n):" i "..." (/ n i) r (if (nil? r) r (< (/ n i) r)))
				 (/ n i))
			       r)))
			 (if is-n-prime?
			   (rest p)
			   p)
			 )))
	      ))))

;(solve-forward)
;(solve-without-primes)
