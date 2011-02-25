
(defn problem-five 
  ([] (problem-five 20))
  ([n] (problem-five n (* n n)))
  ([#^Long n #^Long val] ; TODO: try to make this faster
     (if (every? #(zero? (rem val %)) (range n 1 -1))
       val
       (recur n (+ n val))))
  ([#^Integer n #^Integer val a] ; this is much faster. but not as lispy
     (if (loop [#^Integer n n]   ; but still not pure-java fast
	   (if (> 1 n)
	     true
	     (if (zero? (rem val n))
	       (recur (dec n))
	       false)))
       val
       (recur n (+ n val) 1))))

;(time
;(println (problem-five))
;)

; best answer from site
(defn gcd [a b] (if (zero? b) a (recur b (mod a b))))
(defn lcm [a b] (/ (* a b) (gcd a b)))
(reduce #(lcm %1 %2) (range 1 1001))