(defn brute-force-problem-nine []
  (loop [a 3 b 4]
    (if (< b (inc a)) ; if a gets greater than or equal to b then increment b
      (recur 100 (inc b))
      (let [c (int (. Math (sqrt (+ (* a a) (* b b)))))]
	(if (and (= (+ a b c) 1000) (= (rem (+ (* a a) (* b b)) c) 0))
	  (* a b c)
	  (recur (inc a) b))))))

; derived from the solution shown on project euler site
(defn straightforward-approach [s]
  (loop [a 3 b 4]
    (if (> a (/ (- s 3) 3))
      nil
      (if (> b (/ (- s 1 a) 2))
	(recur (inc a) (+ a 2))
	(let [c (- s a b)]
	  (if (= (* c c) (+ (* a a) (* b b)))
	    (* a b c)
	    (recur a (inc b))))))))

(defn ceil [x]
  (int (. Math (ceil x))))

(defn sqrt [x]
  (. Math (sqrt x)))

(defn abs [x]
  (if (neg? x)
    (- x)
    x))

(defn gcd
  ([] 0)
  ([x] (abs x))
  ([x y] (if (zero? y)
	   (abs x)
	   (recur y (rem x y))))
  ([x y & more] (reduce gcd (gcd x y) more)))

(defn parametrisation-approach [s]
  (let [s2 (/ s 2)]
    (let [mlimit (- (ceil (sqrt s2)) 1)]
      (loop [m 2]
	(if (> m mlimit)
	  -1
	  (if (= (rem s2 m) 0)
	    (let [sm (loop [tsm (/ s2 m)]
		       (if (= (rem tsm 2) 0)
			 (recur (/ tsm 2))
			 tsm))]
	      (let [result (loop [k (if (= (rem m 2) 1)
				      (+ m 2)
				      (+ m 1))]
			     (if (and (< k (* 2 m)) (<= k sm))
			       (if (and (= (rem sm k) 0) (= (gcd k m) 1))
				 (let [d (/ s2 (* k m)) n (- k m)]
				   (let [a (* d (- (* m m) (* n n)))
					 b (* 2 d m n)
					 c (* d (+ (* m m) (* n n)))]
				     (* a b c)))
				 (recur (+ k 2)))
			       nil ; nil here for visual aid. the false of an if returns nil if not supplied
			       ))]
		(if (= result nil)
		  (recur (inc m))
		  result)))
	    (recur (inc m))))))))

;(println (brute-force-problem-nine))
;(println (straightforward-approach 1000))
(println (parametrisation-approach 1000))