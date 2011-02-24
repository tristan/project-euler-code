
(defn problem-five 
  ([] (problem-five 20))
  ([n] (problem-five n (* n n)))
  ([n val] ; TODO: try to make this faster
     (if (> (dec n) 
	    (count (take-while #(zero? %) (map #(rem val %) (range n 1 -1)))))
       (recur n (+ n val))
       val))
  ([#^Integer n #^Integer val a] ; this is much faster. but not as lispy
     (if (loop [#^Integer n n]
	   (if (> 1 n)
	     true
	     (if (zero? (rem val n))
	       (recur (dec n))
	       false)))
       val
       (recur n (+ n val) 1))))

(println (problem-five))