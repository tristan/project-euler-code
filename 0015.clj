

; start at x,y 0,0
; recurse left, recurse down

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

(loop [i 1]
  (if (< 20 i)
    nil
    (do 
      (println (format "paths in a %sx%s grid: %s" i i (find-all-paths i i)))
      (recur (inc i)))))
	 