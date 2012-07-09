(def primes
  (concat 
   [2 3 5 7]
   (lazy-seq
    (let [primes-from
	  (fn primes-from [n [f & r]]
	    (if (some #(zero? (rem n %))
		      (take-while #(<= (* % %) n) primes))
	      (recur (+ n f) r)
	      (lazy-seq (cons n (primes-from (+ n f) r)))))
	  wheel (cycle [2 4 2 4 6 2 6 4 2 4 6 6 2 6  4  2
			6 4 6 8 4 2 4 2 4 8 6 4 6 2  4  6
			2 6 6 4 2 4 6 2 6 4 2 4 2 10 2 10])]
      (primes-from 11 wheel)))))


(defn proper-divisors [n]
  (let [sqrt-n (int (Math/sqrt n))
        divs-upto-sqrt (filter #(zero? (rem n %)) (range 2 (inc sqrt-n)))
        rest-of-divs (reverse (map #(/ n %) divs-upto-sqrt))]
    (concat '(1) divs-upto-sqrt
      (if (= n (* sqrt-n sqrt-n))
        (drop 1 rest-of-divs)
        rest-of-divs))))
 
(defn abundant? [n]
  (> (apply + (proper-divisors n)) n))
 
(defn sum-abnums [upto]
  (apply +
         (let [abnums (filter abundant? (range 1 upto))
               abnums (distinct
                       (for [x abnums
                             y abnums
                             :while (and (<= y x) (<= (+ x y) upto))]
                         (+ x y)))]
           (println abnums)
           abnums)))
 
(def test-upto 28123)
 
(defn euler-23 []
  (- (/ (* test-upto (inc test-upto)) 2)
     (sum-abnums test-upto)))

(println (euler-23))