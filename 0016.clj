(defn str-to-int [s]
  (. Integer (parseInt s)))

(defn pow [nbr pwr]
  (if (< pwr 2)
    nbr
    (* nbr (pow nbr (dec pwr)))))

(defn sum-numbers-in-string [string]
  (loop [ptr 0 sum 0]
    (if (< (- (count string) 1) ptr)
      sum
      (recur (inc ptr) (+ sum (str-to-int (subs string ptr (inc ptr))))))))

(println (sum-numbers-in-string (str (pow 2 1000))))