(load-file "math.clj")

(println (count (filter #(triangle? (sum (map (fn [x] (- (int x) 64)) %))) (map last (re-seq #"\"([A-Z]+)\"" (slurp "words.txt"))))))