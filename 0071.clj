(load-file "math.clj")

(defn reduce-fractions [limit target]
  (loop [n (dec limit) d limit r 0]
    (if (< d 1)
      r
      (if (< n 1)
	r
	(if (> target (/ n d))
	  (if (and (< r (/ n d)) (= 1 (math/gcd n d)))
	    (do
	      (println n "/" d)
	      (recur n (dec d) (/ n d)))
	    (recur n (dec d) r))
	  (recur (dec n) d r))))))

(println (time (reduce-fractions 8 (/ 3 7))))
(println (time (reduce-fractions 1000 (/ 3 7))))
(println (time (reduce-fractions 1000000 (/ 3 7))))
