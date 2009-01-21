(load-file "math.clj")

(defn pentagon [n]
  (/ (* n (- (* 3 n) 1)) 2))

(defn pentagonal? [n]
  (zero? (rem (/ (inc (math/sqrt (inc (* 24 n)))) 6) 1)))

(defn solver [n m]
  (if (= n m)
      (recur (inc n) 1)
    (if (and
	 (pentagonal? (+ (pentagon n) (pentagon m)))
	 (pentagonal? (- (pentagon n) (pentagon m))))
	(do
	    (println "P" m "& P" n)
	    (- (pentagon n) (pentagon m)))
      (recur n (inc m)))))

(defn problem-forty-four []
  (solver 1 1))