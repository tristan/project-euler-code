(load-file "math.clj")

(defn set-extend [lhs rhs]
  (apply #'conj (cons lhs rhs)))

(defn reduce-fractions [limit lower-bound upper-bound]
  (loop [n (dec limit) d limit r #{} next-max limit]
    ;(print n "/" d)
    (if (< n 1)
      r
      (let [frac (/ n d)]
	(if (and (> upper-bound frac) (< lower-bound frac))
	  (if (= 1 (math/gcd n d))
	    (do
	      ;(println " ***")
	      ;(println n "/" d)
	      (recur (dec n) d (conj r frac) next-max))
	    (do
	      ;(println "")
	      (recur (dec n) d r next-max)))
	  (if (>= lower-bound frac)
	    (do
	      ;(println "")
	      (recur next-max (dec d) r next-max))
	    (do
	      ;(println "")
	      (recur (dec n) d r (dec n)))))))))

(defn slow-soln []
(println
 (time
  (count (reduce-fractions 10000 (/ 1 3) (/ 1 2)))
)))

(defn get-all-fractions [from to with-denominator]
  (let [lim (math/ceil (* (first to) (/ with-denominator (last to)))) 
	n (math/ceil (* (first from) (/ with-denominator (last from))))]
    (loop [n (if (= (/ n with-denominator) (/ (first from) (last from))) (inc n) n)
	   r 0]
      ;(println "n:" n)
      (if (>= n lim)
	r
	(if (= 1 (math/gcd n with-denominator))
	  (recur (inc n) (inc r))
	  (recur (inc n) r))))))

;(println (get-all-fractions '(1 3) '(1 2) 8))

(println (time
(loop [n 1 cnt 0]
  (if (> n 10000)
    cnt
    (let [fracs (get-all-fractions '(1 3) '(1 2) n)]
      ;(println "n:" n "fracs:" fracs)
      (recur (inc n)
	     (+ cnt fracs)))))
))