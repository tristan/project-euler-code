(load-file "math.clj")

(defn palindromic? [obj]
  (= (take (ceil (/ (count obj) 2)) obj) (reverse (drop (- (ceil (/ (count obj) 2)) (if (zero? (rem (count obj) 2)) 0 1)) obj))))

(println (sum (filter (fn [x] (and (palindromic? (str x)) (palindromic? (integer-to-binary x)))) (range 1 1000000))))