(defn sum [lst]
  (apply #'* lst))

(defn is-x-divisible-by-all-numbers-in-the-list? [x lst]
  (loop [ilst lst]
    (if (= (count ilst) 0)
      true
      (if (> (rem x (first ilst)) 0)
	false
	(recur (rest ilst))))))

(defn find-smallest-number-evenly-divisible-by-all-numbers-from-one-to [x]
  (let [all-nums (take x (iterate (fn [z] (inc z)) 1))]
     (loop [current (* x x)]
       (if (is-x-divisible-by-all-numbers-in-the-list? current all-nums)
	 current
	 (recur (+ current x))))))

(defn problem-five-example []
  (= (find-smallest-number-evenly-divisible-by-all-numbers-from-one-to 10) 2520))

;(println (problem-five-example))

(defn problem-five []
  (println (find-smallest-number-evenly-divisible-by-all-numbers-from-one-to 20)))

(problem-five)