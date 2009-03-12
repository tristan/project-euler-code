(ns libs.math
  (:require (clojure.contrib [math :as contrib-math])))

(defn list-divisors
  ([n] (list-divisors n
		     (filter (fn [x] (zero? (rem n x))) (range 1 (contrib-math/ceil (contrib-math/sqrt (inc n)))))))
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
      (apply #'/ (take (+ 2 (contrib-math/abs pwr)) (iterate #'float (float nbr))))
      (* nbr (pow nbr (dec pwr))))))

; uses a loop to prevent stack overflows with large numbers
(defn pow [nbr pwr]
  (loop [n nbr p pwr r 1]
    (if (= p 0)
      r
      (if (< p 0)
	(apply #'/ (take (+ 2 (contrib-math/abs pwr)) (iterate #'float (float nbr))))
	(recur n (dec p) (* r n))))))

; used by fast-pow
(defn build-equation 
  ([total denominations] (build-equation total denominations '()))
  ([total denominations result] 
     (if (empty? denominations)
       result
       (let [fits (/ (- total (rem total (first denominations))) (first denominations))]
	 (recur 
	  (- total (* fits (first denominations)))
	  (rest denominations)
	  (concat result
		  (take fits (repeat (first denominations)))))))))

(defn fast-pow [a n]
  (let [lim (/ n 2)]
    (loop [r {1 a} i 1]
      (if (> (* 2 i) lim)
	(let [eqn (build-equation n (reverse (sort (keys r))))]
	  (apply #'* (map #(get r %) eqn))
	  )
	(recur (assoc r (* 2 i) (* (get r i) (get r i))) (* 2 i))))))

(defn log [x] (. Math (log x)))

(defn log10 [x] (. Math (log10 x)))

(defn ! [nbr]
  (if (<= nbr 1)
    1
    (* nbr (! (dec nbr)))))

; TODO: redo cleaner and without dependence on string libraries
;(defn longdiv [dividend divisor]
;  (loop [result (list) dividend (map char-to-number (seq (str dividend))) remainder 0 pushed-decimal false loop-watcher (list)]
;    (if (and (empty? dividend) (zero? remainder))
;      (if (not (= (first (rest result)) \'))
;	(reverse (cons 0 (cons \' result)))
;	(reverse result))
;      (if (or (= (first dividend) \.) (and (not pushed-decimal) (nil? dividend) (not (zero? remainder))))
;	(recur (cons \. result) (rest dividend) remainder true loop-watcher) ; push the decimal point through
;	(let [divisee (+ (* 10 remainder) (if (empty? (first dividend)) 0 (first dividend)))]
;	  (if (and pushed-decimal (< 0 (count (filter #(= divisee %) loop-watcher))))
;	    (recur (cons (inc (count (take-while (fn [x] (not (= x divisee))) loop-watcher))) (cons \' result)) nil 0 true nil) ; found a loop, done
;	    (let [val (contrib-math/floor (/ divisee divisor)) remain (rem divisee divisor)]
;	      (recur (cons val result) (rest dividend) remain pushed-decimal (if pushed-decimal (cons divisee loop-watcher) loop-watcher))
;	      )))))))


(defn integer-to-binary-helper [nbr base]
  (if (< nbr 1)
    nil
    (cons (rem nbr base) (integer-to-binary-helper (contrib-math/floor (/ nbr base)) base))))

(defn integer-to-binary
  ([nbr] (integer-to-binary nbr 2))
  ([nbr base] (reverse (integer-to-binary-helper nbr base))))

(defn binary-to-integer
  ([bin] (binary-to-integer (reverse bin) 1))
  ([bin n]
	(if (empty? bin)
	    0
	  (+ (* n (first bin)) (binary-to-integer (rest bin) (* n 2))))))
   

(defn prime? [nbr]
  (if (< nbr 2)
    false
    (if (< nbr 4)
      true
      (let [limit (contrib-math/sqrt nbr)]
	(loop [ctr 2]
	  (if (< limit ctr)
	    true
	    (if (zero? (rem nbr ctr))
	      false
	      (recur (inc ctr)))))))))

(defn triangle? [nbr]
  ; from http://en.wikipedia.org/wiki/Triangular_number#Tests_for_triangular_numbers
  (let [a (/ (- (contrib-math/sqrt (+ (* 8 nbr) 1)) 1) 2)]
    (if (= a (contrib-math/floor a))
      true
      false)))

; binary GCD algorithm ported from the C code at:
; http://en.wikipedia.org/wiki/Binary_GCD_algorithm#Implementation_in_C
(defn binary-gcd [u v]
  (if (or (zero? u) (zero? v))
    (bit-or u v)
    (loop [shift 0 u (contrib-math/abs u) v (contrib-math/abs v)]
      (if (not (zero? (bit-and (bit-or u v) 1)))
	(loop [u u]
	  (if (not (zero? (bit-and u 1)))
	    (loop [u u v v]
	      (if (zero? v)
		(bit-shift-left u shift)
		(let [v (loop [v v] (if (not (zero? (bit-and v 1))) v (recur (bit-shift-right v 1))))]
		  (recur
		   (if (< u v) u v)
		   (if (< u v) (bit-shift-right (- v u) 1) (bit-shift-right (- u v) 1))))))
	    (recur (bit-shift-right u 1))))
	(recur (inc shift) (bit-shift-right u 1) (bit-shift-right v 1))))))

(defn euclidean-gcd [a b]
  (if (<= a 0)
    b
    (recur (rem b a) a)))

(def gcd euclidean-gcd)

(defn coprime? 
  ([a b] (= 1 (gcd a b)))
  ([a b prime-sieve]
     (let [lim (min (contrib-math/sqrt a) (contrib-math/sqrt b))]
       (if (or (zero? (rem a b)) (zero? (rem b a)))
	 false
	 (loop [ps prime-sieve]
	   (if (> (first ps) lim)
	     true
	     (if (and (zero? (rem a (first ps))) (zero? (rem b (first ps))))
	       false
	       (recur (rest ps)))))))))

(defn get-factors [n]
  (let [limit (contrib-math/floor (contrib-math/sqrt n))]
    (loop [n n i 2 factors '()]
      (if (>= i n)
	(if (empty? factors)
	  factors
	  (distinct (cons n factors)))
	(if (and (empty? factors) (> i limit))
	  factors
	  (if (zero? (rem n i))
	    (recur (/ n i) i (cons i factors))
	    (recur n (inc i) factors))))))) ; TODO: speedup by checking for primes after so long

(defn eulers-totient [n]
  (let [factors (get-factors n)]
    (if (empty? factors)
      (dec n)
      (loop [facts factors r n]
	(if (empty? facts)
	  r
	  (recur (rest facts) (- r (/ r (contrib-math/floor (first facts))))))))))

(defn phi ([n]
  (loop [k 2 c 1]
    (if (> k n)
      c
      (recur (inc k) (if (coprime? n k) (inc c) c)))))
  ([n prime-sieve]
     (loop [k 2 c 1 remaining-primes prime-sieve]
       (if (> k n)
	 c
	 (if (= k (first remaining-primes))
	   (recur (inc k) (inc c) (rest remaining-primes))
	   (recur (inc k) 
		  (if (coprime? n k prime-sieve) (inc c) c)
		  remaining-primes
		  ))))))

(defn C [n r]
  (/ (! n) (* (! r) (! (- n r)))))
