(load-file "math.clj")

(let [se (str (apply #'+ (map (fn [x] (pow x x)) (range 1 1001))))]
  (println (drop (- (count se) 10) se)))