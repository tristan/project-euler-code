(ns math
    (:load "string-lib"))

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

(defn pow [nbr pwr]
  (if (= pwr 0)
    1
    (if (< pwr 0)
      (apply #'/ (take (+ 2 (abs pwr)) (iterate #'float (float nbr))))
      (* nbr (pow nbr (dec pwr))))))

(defn loop-pow [nbr pwr]
  (loop [n nbr p pwr r 1]
    (if (= p 0)
      r
      (if (< p 0)
	(apply #'/ (take (+ 2 (abs pwr)) (iterate #'float (float nbr))))
	(recur n (dec p) (* r n))))))

(defn log10 [x] (. Math (log10 x)))

(defn ! [nbr]
  (if (<= nbr 1)
    1
    (* nbr (! (dec nbr)))))

(defn longdiv [dividend divisor]
  (loop [result (list) dividend (map char-to-number (seq (str dividend))) remainder 0 pushed-decimal false loop-watcher (list)]
    (if (and (nil? dividend) (zero? remainder))
      (if (not (= (first (rest result)) \'))
	(reverse (cons 0 (cons \' result)))
	(reverse result))
      (if (or (= (first dividend) \.) (and (not pushed-decimal) (nil? dividend) (not (zero? remainder))))
	(recur (cons \. result) (rest dividend) remainder true loop-watcher) ; push the decimal point through
	(let [divisee (+ (* 10 remainder) (if (nil? (first dividend)) 0 (first dividend)))]
	  (if (and pushed-decimal (< 0 (count (filter #(= divisee %) loop-watcher))))
	    (recur (cons (inc (count (take-while (fn [x] (not (= x divisee))) loop-watcher))) (cons \' result)) nil 0 true nil) ; found a loop, done
	    (let [val (floor (/ divisee divisor)) remain (rem divisee divisor)]
	      (recur (cons val result) (rest dividend) remain pushed-decimal (if pushed-decimal (cons divisee loop-watcher) loop-watcher))
	      )))))))


(defn integer-to-binary-helper [nbr base]
  (if (< nbr 1)
    nil
    (cons (rem nbr base) (integer-to-binary-helper (floor (/ nbr base)) base))))

(defn integer-to-binary
  ([nbr] (integer-to-binary nbr 2))
  ([nbr base] (reverse (integer-to-binary-helper nbr base))))

(defn prime? [nbr]
  (if (< nbr 2)
    false
    (if (< nbr 4)
      true
      (loop [ctr (ceil (sqrt nbr))]
	(if (< ctr 2)
	  true
	  (if (zero? (rem nbr ctr))
	    false
	    (recur (dec ctr))))))))


(defn triangle? [nbr]
  ; from http://en.wikipedia.org/wiki/Triangular_number#Tests_for_triangular_numbers
  (let [a (/ (- (sqrt (+ (* 8 nbr) 1)) 1) 2)]
    (if (= a (floor a))
      true
      false)))

(defn list-numbers-in 
  ([nbr] (list-numbers-in nbr 1))
  ([nbr tenth]
     (if (= nbr (rem nbr tenth))
       nil
       (cons (rem (/ (- nbr (rem nbr tenth)) tenth) 10) (list-numbers-in nbr (* tenth 10))))))
