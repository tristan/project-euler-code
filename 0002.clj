
(defn fib
  ([] (concat [1 2] (fib 1 2)))
  ([a b] (lazy-seq (cons (+ a b) (fib b (+ a b))))))

(defn problem-two [max]
  (apply #'+ (filter (fn [x] (even? x)) (take-while (fn [x] (<= x max)) (fib)))))

;(if (= (problem-two 89) 44)
;    (println (problem-two 4000000))
;    (println "tests failed"))

(def solutions (list (fn [] (problem-two 4000000))))