(defn sieve-helper [x n li]
  (if (= x n)
    li
    (if (= 0 (reduce #'* (map (fn [y] (rem x y)) li)))
      (sieve-helper (+ x 1) n li)
      (sieve-helper (+ x 1) n (cons x li)))))

; this one runs rather slowly
(defn sieve [n]
  (reverse (sieve-helper 2 n '(2))))
