(require '(libs [math :as my-math]))
(require '(clojure.contrib [math :as math]))
(require '(libs [pandigital :as pandigital]))
(require '(libs [list-utils :as list-utils]))

(defn make-number [substitute]
  (loop [i '(1 2 3 4 5 6 7 8 9) j substitute sum 0]
    (if (empty? i)
      (* 10 sum)
      (recur (rest i) (rest j) (+ (* 10 (+ (* 10 sum) (first i))) (first j))))))

(defn pad-zeroes [sequ]
  (concat (take (- 9 (count sequ)) (repeat 0)) sequ))

(defn brute-force []
  (println
   (time
    (loop [n 0]
      (when (zero? (rem n 100000))
	(println n))
      (let [v (make-number (pad-zeroes (list-utils/number-to-list n)))]
	(if (integer? (math/sqrt v))
	  (math/sqrt v)
	  (recur (inc n)))))
    )))

(defn pandig []
  (println
   (time
    (let [limit (my-math/! 10)]
      (println "limit:" limit)
      (loop [n 0]
	(when (zero? (rem n 1000))
	  (println n))
	(if (< limit n)
	  nil
	  (let [v (make-number (pandigital/get-permutation n 0 9))]
	    (if (integer? (math/sqrt v))
	      (math/sqrt v)
	      (recur (inc n)))))))
    )))

;; TODO: too slow, OPTIMISE
(defn squaring []
  (println 
   (time
    (let [limit (int (math/ceil (math/sqrt 1929394959697989990)))]
      (loop [n (int (math/floor (math/sqrt 1020304050607080900)))]
	(when (zero? (rem n 100000))
	  (println n))
	(if (> n limit)
	  nil
	  (if (not (empty? (re-seq #"1.2.3.4.5.6.7.8.9.0" (str (math/expt n 2)))))
	    n
	    (recur (inc n))))))
    )))

(squaring)