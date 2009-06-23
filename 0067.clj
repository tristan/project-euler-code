;(load-file "0018.clj")
(load-file "string-lib.clj")
(load-file "math.clj")

(defn zip [s1 s2]
  (map (fn [x y] (list x y)) s1 s2))

(println
(let [input (map (fn [x] (parse-integer (first x))) (re-seq #"([0-9][0-9])" (slurp "triangle.txt")))]
  (let [lines
	(loop [lines (list) left input cnt 1]
	  (if (nil? left)
	    lines
	    (recur (cons (take cnt left) lines) (nthrest left cnt) (inc cnt))))
	]
    (loop [sums (first lines) left (rest lines)]
      ;(println "sums" sums)
      ;(println "fleft" (first left))
      (if (nil? left)
	sums
	(let [a (zip sums (first left)) b (zip (rest sums) (first left))]
	  (recur (map (fn [x] (apply max x)) (zip (map sum (zip sums (first left))) (map sum (zip (rest sums) (first left)))))
	       (rest left)))))))
  ;(apply max (flatten-list (get-all-paths input))))
)