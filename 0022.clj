

(println 
(loop [names (sort (map (fn [x] (last x)) (re-seq #"\"([A-Z]+)\"" (slurp "names.txt"))))
       sum 0
       level 1]
  (if (nil? names)
    sum
    (recur (rest names) (+ sum (* level (apply #'+ (map (fn [x] (- (int x) 64)) (first names))))) (inc level))))
)


; same thing without loop.
; would like to do it without let, but this would require doing the first step twice, not desireable
(println
(let [names (sort (map (fn [x] (last x)) (re-seq #"\"([A-Z]+)\"" (slurp "names.txt"))))]
  (apply #'+ (map #'* (map (fn [z] (apply #'+ (map (fn [x] (- (int x) 64)) z))) names) (range 1 (inc (count names))))))
)