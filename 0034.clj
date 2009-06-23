(load-file "string-lib.clj")
(load-file "math.clj")

(println (sum (filter (fn [x] (= x (sum (map #'+ (map ! (map char-to-number (str x))))))) (range 11 999999))))