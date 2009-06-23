(load-file "math.clj")

(defn problem-twenty-eight []
  (loop [lst (range 2 (inc (* 1001 1001))) diag1 '(1) diag2 '() cnt 2]
    (if (nil? lst)
      (println (+ (sum diag1) (sum diag2)))
      (recur (nthrest lst (* cnt 4)) 
	     (cons (nth lst (dec (* cnt 1))) (cons (nth lst (dec (* cnt 3))) diag1))
	     (cons (nth lst (dec (* cnt 2))) (cons (nth lst (dec (* cnt 4))) diag2))
	     (+ cnt 2)))))

(problem-twenty-eight)