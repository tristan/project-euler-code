
(defn fn0 []
  (apply #'+ (filter #(or (zero? (rem % 5)) (zero? (rem % 3))) (range 1000))))

(def solutions (list fn0))
