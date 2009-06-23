(load-file "math.clj")

(println (sum (filter (fn [i] (= (sum (map (fn [x] (pow x 5)) (map char-to-number (str i)))) i)) (range 2 1000000))))