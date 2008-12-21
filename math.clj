(defn ceil [x]
  (int (. Math (ceil x))))

(defn floor [x]
  (int (. Math (floor x))))

(defn sqrt [x]
  (. Math (sqrt x)))

(defn abs [x]
  (if (neg? x)
    (- x)
    x))

(defn in? [lst n]
  (if (nil? lst)
    false
    (if (= (first lst) n)
      true
      (in? (rest lst) n))))

(defn list-divisors
  ([n] (list-divisors n
		     (filter (fn [x] (zero? (rem n x))) (range 1 (ceil (sqrt (inc n)))))))
  ([n divisors-up-to-sqrt-n]
     (sort (concat divisors-up-to-sqrt-n 
		   (filter (fn [x] (not (= (/ n x) x))) ; this filter is to ensure multiple values are not included
			   (map (fn [x] (/ n x)) 
				(rest divisors-up-to-sqrt-n))))))) ; (rest divisors) ignores 1 so that n isn't counted as a divisor

(defn sum [lst]
  (apply #'+ lst))