(load-file "math.clj")

(defn sieve-helper [x n li]
  (if (= x n)
    li
    (if (= 0 (reduce #'* (map (fn [y] (rem x y)) li)))
      (sieve-helper (+ x 1) n li)
      (sieve-helper (+ x 1) n (cons x li)))))

; this one runs rather slowly, and due to lack of tail-recursion fails with large numbers
(defn sieve [n]
  (reverse (sieve-helper 2 n '(2))))


(defn sieve [n]
  (reverse
   (loop [x 2 li '(2)]
     (if (= x n)
       li
       ;(if (zero? (reduce #'* (map #(rem x %) li)))
       ; only iterating li until a match speeds things up
       ; reversing li speeds things up, since it is more likely something 
       ;   divides evenly into a lower prime
       (if (> (count li) (count (take-while #(not (zero? (rem x %))) (reverse li))))
	 (recur (inc x) li)
	 (recur (inc x) (cons x li)))))))

; using vectors speeds things up
(defn sieve [n]
  (loop [x 2 li [2]]
    (if (= x n)
      li
      ;(if (> (count li) (count (take-while #(not (zero? (rem x %))) li)))
      ; custom loop is much faster
      ; speed up gained by only testing up to sqrt of x
      (if (let [sqrtx (sqrt x)]
	    (loop [tst li]
	      (if (or (nil? tst) (> (first tst) sqrtx))
		false
		(if (zero? (rem x (first tst)))
		  true
		  (recur (rest tst))))))
	(recur (inc x) li)
	(recur (inc x) (assoc li (count li) x))))))