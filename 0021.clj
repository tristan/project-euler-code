(load-file "math.clj")

(defn list-divisors
  ([n] (list-divisors n
		     (filter (fn [x] (zero? (rem n x))) (range 1 (sqrt (inc n))))))
  ([n divisors-up-to-sqrt-n]
     (sort (concat divisors-up-to-sqrt-n (map (fn [x] (/ n x)) (rest divisors-up-to-sqrt-n)))))) ; (rest divisors) ignores 1 so that n isn't counted as a divisor

(defn amicable? [a]
  (let [b (apply #'+ (list-divisors a))]
    (let [sumb (apply #'+ (list-divisors b))]
      (if (and (= a sumb) (not (= a b)))
	(do
	  (println "d(" a ") = " b " and d(" b ") = " a)
	  true)
	false))))

;(println (amicable? 220))

(defn problem-twenty-one [limit]
  (if (< (dec limit) 0)
    0
    (+ (if (amicable? (dec limit))
	 (dec limit)
	 0)
       (problem-twenty-one (dec limit)))))

(println (problem-twenty-one 10000))