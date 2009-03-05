(require '(clojure.contrib [math :as contrib-math]))

(defn sieve-helper [x n li]
  (if (= x n)
    li
    (if (= 0 (reduce #'* (map (fn [y] (rem x y)) li)))
      (sieve-helper (+ x 1) n li)
      (sieve-helper (+ x 1) n (cons x li)))))

; this one runs rather slowly, and due to lack of tail-recursion fails with large numbers
(defn sieve [n]
  (reverse (sieve-helper 2 n '(2))))

; the same sieve with a few optimisations and a loop to deal with tail-recursion
; still too slow
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
  (loop [x 3 li [2]]
    (if (>= x n)
      li
      ;(if (> (count li) (count (take-while #(not (zero? (rem x %))) li)))
      ; custom loop is much faster
      ; speed up gained by only testing up to sqrt of x
      (if (let [sqrtx (contrib-math/sqrt x)]
	    (loop [tst (rest li)] ; can skip 2 since we already removed that case
	      (if (or (empty? tst) (> (first tst) sqrtx))
		false
		(if (zero? (rem x (first tst)))
		  true
		  (recur (rest tst))))))
	(recur (+ 2 x) li) ; plus 2 instead of 1, since no even cases will ever work
	(recur (+ 2 x) (assoc li (count li) x))))))

(defn prime? 
  ([nbr] (prime? nbr (sieve 100000)))
  ([nbr prime-sieve] 
  (if (< nbr 2)
    false
    (let [sqrtnbr (contrib-math/sqrt nbr)]
      (loop [ps prime-sieve]
	(if (empty? ps)
	  (do
	    (println "WARNING: EXCEEDED SIZE OF SIEVE")
	    true)
	  (if (< sqrtnbr (first ps))
	    true
	    (if (zero? (rem nbr (first ps)))
	      false
	      (recur (rest ps))))))))))

; TODO: benchmark both versions of prime
(defn quick-prime?
  ([nbr] (prime? nbr (sieve 100000)))
  ([nbr prime-sieve] 
  (= nbr (last (take-while #(>= nbr %) prime-sieve)))))
      