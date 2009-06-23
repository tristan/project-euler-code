
(defn sum-of-squares [lst]
  (apply #'* (map (fn [x] (* x x)) lst)))

(defn square-of-sum [lst]
  (let [sum (apply #'* lst)]
    (* sum sum)))

(defn problem-six []
  (let [lst (take 100 (iterate (fn [x] (inc x)) 1))]
    (- (square-of-sum lst) (sum-of-squares lst))))

(defn problem-six []
  (let [lst (take 100 (iterate (fn [x] (inc x)) 1))]
    (loop [l lst sum-of-squares 0 sum 0]
      (if (= (count l) 0)
	(- (* sum sum) sum-of-squares)
	(recur (rest l) (+ sum-of-squares (* (first l) (first l))) (+ sum (first l)))))))

(println (problem-six))