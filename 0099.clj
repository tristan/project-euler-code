(load-file "math.clj")

(defn very-very-slow-solution []
  (loop [values (map #(list (math/parse-integer (first (rest %))) (math/parse-integer (last %))) 
		     (re-seq #"([0-9]+),([0-9]+)" (slurp "base_exp.txt")))
	 result nil
	 value 0]
    (println (first values))
    (if (nil? values)
      result
      (let [v (math/fast-pow (first (first values)) (last (first values)))]
	(if (> v value)
	  (recur (rest values) (first values) v)
	  (recur (rest values) result value))))))

(defn do-sort [arr]
  (let [m (loop [a arr m {}]
	    (if (nil? a)
	      m
	      (recur (rest a)
		     (assoc m (last (first a)) (first (first a))))))]
    (loop [r '() ks (reverse (sort (keys m)))]
      (if (nil? ks)
	r
	(recur (cons (list (get m (first ks)) (first ks)) r)
	       (rest ks))))))

; uses the following knowledge:
; a^n = i
; b^m = j
; (a^(n/m))^m = i
; thus modifying each base so that all exponents are the same will give us the largest value

(defn experimental-solution []
  (let [values (map #(list (math/parse-integer (first (rest %))) (math/parse-integer (last %))) 
		     (re-seq #"([0-9]+),([0-9]+)" (slurp "base_exp.txt")))]
    (let [smallest-power (last (first (do-sort values)))]
      (loop [m {} v values]
	(if (nil? v)
	  (get m (last (sort (keys m))))
	  (let [n (. Math (pow (first (first v)) (/ (last (first v)) smallest-power)))]
	    (recur (assoc m n (first v))
		   (rest v))))))))

; using log(a^n) = n * log(a)
(defn log-solution []
  (loop [values (map #(list (math/parse-integer (first (rest %))) (math/parse-integer (last %))) 
		     (re-seq #"([0-9]+),([0-9]+)" (slurp "base_exp.txt")))
	 counter 1
	 resultval 0
	 resultline 0]
    (if (nil? values)
      resultline
      (let [val (* (last (first values)) (math/log (first (first values))))]
	(if (> val resultval)
	  (recur (rest values) (inc counter) val counter)
	  (recur (rest values) (inc counter) resultval resultline))))))

(time
(let [r (experimental-solution)]
  (println (format "%d,%d" (first r) (last r))))
)

(time
 (println "line number:" (log-solution))
)
