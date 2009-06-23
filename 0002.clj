(defn sum [lst]
      (if (= (rest lst) nil)
      	  (first lst)
	  (+ (first lst) (sum (rest lst)))))
(defn fib
      ([] (concat [1 2] (fib 1 2)))
      ([a b] (lazy-cons (+ a b) (fib b (+ a b)))))
(defn problem-two [max]
      (sum (filter (fn [x] (even? x)) (take-while (fn [x] (<= x max)) (fib)))))
(if (= (problem-two 89) 44)
    (println (problem-two 4000000))
    (println "tests failed"))
      
      