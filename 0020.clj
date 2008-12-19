(load-file "0016.clj")

(defn ! [n]
  (if (< n 2)
    1
    (* n (! (dec n)))))

(println (sum-numbers-in-string (str (! 100))))