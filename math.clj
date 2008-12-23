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

(defn log10 [x] (. Math (log10 x)))

(defn char-to-number [c]
  (if (= c \.)
    c
    (if (or (< (int c) 48) (> (int c) 57))
      nil
      (- (int c) 48))))

(defn get-recuring-sequence 
  [numbers] 
  (if (or (< (count numbers) 2) (not (zero? (rem (count numbers) 2))))
    nil
    (loop [nums1 numbers nums2 (rest numbers)]
      ;(println nums1 nums2)
      (if (nil? nums2)
	(if (nil? (rest nums1))
	  nil
	  (recur (rest nums1) (rest (rest nums1))))
	(if (= (first nums1) (first nums2))
	  (let [sequ (take-while (fn [x] (not (nil? x))) (map (fn [x y] (if (= x y) x nil)) nums1 nums2))]
	    (if (= (count sequ) (- (count nums1) (count nums2)))
	      (cons sequ (reverse (take (- (- (count numbers) (count nums1)) 1) numbers)))
	      (recur nums1 (rest nums2))))
	  (recur nums1 (rest nums2)))))))
; note: this can be done quicker if we know that (rest numbers) has no recuring 

(defn get-recuring-sequence-if-no-recuring-sequences-in-rest [numbers]
  (if (or (< (count numbers) 2) (not (zero? (rem (count numbers) 2))))
    nil
    (let [a (split-at (/ (count numbers) 2) numbers)]
      (if (= (first a) (last a))
	(first a)
	nil))))

(defn check-for-recuring-sequence [numbers]
  (not (nil? (get-recuring-sequence numbers))))

(defn longdiv [dividend divisor]
  (reverse 
  (loop [result (list) dividend (map char-to-number (seq (str dividend))) remainder 0 pushed-decimal false]
    (if (and (nil? dividend) (zero? remainder))
      result
      (if (= (first dividend) \.) ; push the decimal point through
	(recur (cons \. result) (rest dividend) remainder true)
	(if (and (nil? dividend) (not pushed-decimal))
	  (recur (cons \. result) nil remainder true)
	  (if (and pushed-decimal (not (nil? (get-recuring-sequence (reverse (take-while (fn [x] (not (= x \.))) result))))))
	    (concat (get-recuring-sequence (reverse (take-while (fn [x] (not (= x \.))) result))) '(\.) (rest (drop-while (fn [x] (not (= x \.))) result)))
	    (let [divisee (+ (* 10 remainder) (if (nil? (first dividend)) 0 (first dividend)))]
	      (recur (cons (floor (/ divisee divisor)) result) (rest dividend) (rem divisee divisor) pushed-decimal)))))))
))