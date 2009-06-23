(load-file "math.clj")

(defn problem-twenty-five []
  (loop [a 1 b 1 term 2]
    (let [newb (+ a b)]
      (if (>= (count (str newb)) 1000)
	(inc term)
	(recur b newb (inc term))))))

(println (problem-twenty-five))
