(defn problem-three 
  ([num] (problem-three num 2))
  ([num div]
     (if (<= num 1)
       div
       (if (zero? (rem num div))
	 (recur (/ num div) div)
	 (recur num (inc div)))))
)

(defn fn1 []
  (loop [num 600851475143 div 2]
    (if (<= num 1)
      div
      (if (zero? (rem num div))
        (recur (/ num div) div)
        (recur num (inc div))))))

(defn test-solution []
  (= (problem-three 13195) 29))

(def solutions (list (fn [] (problem-three 600851475143)) fn1))