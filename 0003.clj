(defn problem-three 
  ([num] (problem-three num 2))
  ([num div]
     (if (<= num 1)
       div
       (if (zero? (rem num div))
	 (recur (/ num div) div)
	 (recur num (inc div)))))
)

(defn test []
  (= (problem-three 13195) 29))

(println (problem-three 600851475143))