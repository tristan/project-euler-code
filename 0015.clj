(defn find-all-paths 
  ([max-x max-y] (find-all-paths 0 0 max-x max-y))
  ([x y max-x max-y]
     (if (and (zero? x) (zero? y))
       (* (find-all-paths (inc x) y max-x max-y) 2) ; only go left as doubling the result will give the right answer
       (if (= x y)
	 (find-all-paths 0 0 (- max-x x) (- max-y y)) ; recude the problem to a smaller graph
	 (if (and (= x max-x) (= y max-y))
	   1
	   (if (not (or (= x max-x) (= y max-y)))     
	     (+ (find-all-paths (inc x) y max-x max-y) (find-all-paths x (inc y) max-x max-y))
	     1 ; if we've hit a boundary, there is only 1 path to follow from there till the end
	     ))))))

(def find-all-paths (memoize find-all-paths))

(def solutions (list (fn []
                       (loop [i 1]
                         (if (< 19 i)
                           (find-all-paths i i)
                           (do 
                             (find-all-paths i i)
                             (recur (inc i))))))))
	 