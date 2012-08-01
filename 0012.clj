(defn istriangle? [n]
  (loop [x n i 1]
    (if (neg? x)
      false
      (if (zero? x)
	true
	(recur (- x i) (inc i))))))

(defn nth-triangle-number [n]
  (apply + (range 1 (inc n))))

(defn list-factors [n]
  (filter (fn [x] (zero? (rem n x))) (range 1 (inc n))))

(defn find-fist-triangle-number-to-have-over-five-hundred-divisors []
  (loop [n 1 tri 1]
    (let [divisors (list-factors tri)]
      ;(println tri ":" (nthrest divisors (- (count divisors) 10)))
      (if (> (count divisors) 500)
	(list tri (nthrest divisors (- (count divisors) 10)))
	(recur (inc n) (+ tri (inc n)))))))


(defn list-all-factors-of-all-numbers-upto [x]
  (loop [n 1]
    (println n ":" (list-factors n))
    (if (> n x)
      nil
      (recur (inc n)))))

;(list-all-factors-of-all-numbers-upto 200)

;(println (find-fist-triangle-number-to-have-over-five-hundred-divisors))

(defn find-smallest-factor [n]
  (if (< n 2) ; 1 and lower don't have factors (does 1 count as a factor?)
    nil
    (loop [x 2]
      (if (zero? (rem n x))
	x
	(recur (inc x))))))

(defn find-largest-factor [n]
  (if (< n 2) ; 1 and lower don't have factors
    nil
    (/ n (find-smallest-factor n))))

; (1 2 ... 499 998)

;(println (step-one 6))
; (1 2 3 4 5 6)
; (a b c d e f)
; (1 2 3 4 5 10)
; not all factors of 10
; find next value for e > 5 where e * 2 is a triangle
; (1 2 3 4 14 28)
; (1 2 4 5 14 28)
; (1 2 4 6 14 28)
; (1 2 4 7 14 28)


(defn build-initial-vector
  ([size] (build-initial-vector size (dec size)))
  ([size second-last-val]
     (if (< size 2)
       (vector)
       (loop [v (vector)]
	 (if (< (- size 3) (count v))
	   (assoc (assoc v (- size 2) second-last-val) (dec size) (* second-last-val 2))
	   (recur (assoc v (count v) (inc (count v)))))))))

(defn increase-all-if-needed [vect ptr]
  (loop [v vect p ptr]
    (if (> p (- (count v) 3))
      v
      (if (>= (nth v (dec p)) (nth v p))
	(recur (assoc v p (inc (nth v (dec p)))) (inc p))
	(recur v (inc p))))))

(defn get-next-triangle-larger-than [x]
  (loop [n 1 tri 0]
    (if (> tri x)
      tri
      (recur (inc n) (+ tri n)))))

(defn test-solution []
  (let [initial (build-initial-vector 7 6)]
    (loop [vect initial ptr 2]
      (println ptr vect)
      (if (= ptr (- (count vect) 1))
	vect
	(if (>= (nth vect (- (count vect) 3)) (nth vect (- (count vect) 2)))
	  (recur (build-initial-vector 7 (inc (nth vect (- (count vect) 2)))) 2)
	  (if (zero? (rem (last vect) (nth vect ptr)))
	    (recur vect (inc ptr))
	    (recur (increase-all-if-needed (assoc vect ptr (inc (nth vect ptr))) ptr) ptr)))))))

(defn get-next-second-last-value-for-triangle [num]
  (loop [x num]
    (let [nxt (get-next-triangle-larger-than (* x 2))]
      (println x nxt)
      (if (zero? (rem nxt 2))
	(/ nxt 2)
	(if (< x (/ nxt 3))
	  (/ nxt 3)
	  (recur (Math/ceil (/ nxt 2.0))))))))

(defn find-first-triange-with-at-least-min-divisors [min]
  (let [initial (build-initial-vector min (get-next-second-last-value-for-triangle (dec min)))]
    (loop [vect initial ptr 2]
      ;(println ptr vect)
      (if (= ptr (- (count vect) 1))
	vect
	(if (>= (nth vect (- (count vect) 3)) (nth vect (- (count vect) 2)))
	  (recur (build-initial-vector min (get-next-second-last-value-for-triangle (nth vect (- (count vect) 2)))) 2)
	  (if (zero? (rem (last vect) (nth vect ptr)))
	    (recur vect (inc ptr))
	    (recur (increase-all-if-needed (assoc vect ptr (inc (nth vect ptr))) ptr) ptr)))))))

;(println (find-first-triange-with-at-least-min-divisors 501))
;(println (get-next-triangle-larger-than 10))

(defn find-if-number-has-x-factors [number x]
  (let [limit (Math/sqrt number)]
    (loop [found 0 n 2]
      (if (= (* found 2) x)
	true
	(if (> n limit)
	  false
	  (if (zero? (rem number n))
	    (recur (inc found) (inc n))
	    (recur found (inc n))))))))


(defn find-first-triangle-to-have-more-than-five-hundred-divisors []
  (loop [n 2 tri 1]
    (if (find-if-number-has-x-factors tri 500)
      tri
      (recur (inc n) (+ tri n)))))

(def solutions (list find-first-triangle-to-have-more-than-five-hundred-divisors))