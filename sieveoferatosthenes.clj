;; get the first number in the sequence missing from the supplied list
(defn find-missing-number 
    ([lst] (find-missing-number lst 1))
    ([lst num]
	  (if (= (first (nthrest lst (- num 1))) num)
	      (find-missing-number lst (+ num 1))
	      num
	      )))

;; add all multiples of inc up to max to lst
(defn add-multiples-to-list 
    ([lst inc max] (add-multiples-to-list lst inc max inc))
    ([lst inc max sum]
	  (print (format ".%d." sum))
	  (if (> sum max)
	      lst
	  (if (= (some (fn [x] (= x sum)) lst) nil)
	      ;;(sort (lazy-cons sum (add-multiples-to-list lst inc max (+ sum inc))))
	      (let [x (split-with (fn [x] (< x sum)) lst)]
	          (add-multiples-to-list (concat (first x) [sum] (first (rest x))) inc max (+ sum inc)))
	      (add-multiples-to-list lst inc max (+ sum inc))))))

(defn strike-multiples-from-sieve
    ;;([sieve mult] (strike-multiples-from-sieve sieve mult mult))
    ;;([sieve mult curr]
    [sieve mult]
	    ;;(println (format "count: %d, curr: %d" (count sieve) curr))
	    ;;(if (< curr (count sieve))
	    (for [x (range mult (count sieve) mult)]
		 (def sieve (assoc sieve (- x 1) false)))
	    sieve)
	    ;;(strike-multiples-from-sieve (assoc sieve (- curr 1) false) mult (+ curr mult))
            ;;    sieve
	    ;;)))


(defn find-first-true [vec start]
      (if (< (- start 1) (count vec))
	  (if (= (nth vec (- start 1)) true)
	      start
	      (find-first-true vec (+ start 1)))
	  (+ (count vec) 1)))

(defn build-base-sieve 
    ([size] 
     (if (< size 1) 
	 (vector)
	 (build-base-sieve (- size 1) (vector false))))
    ([size sieve]
     (if (< size 1)
	 sieve
	 (build-base-sieve (- size 1) (assoc sieve (count sieve) true))
	 )))
      

(defn sieve-of-eratosthenes 
 ;;([max] (sieve-of-eratosthenes 2 (cons false (take (- max 1) (iterate (fn [x] x) true)))))
 ;;([max] (lazy-cons 2 (sieve-of-eratosthenes max 2 (sort (add-multiples-to-list (list 1 2) 2 max)))))
  ([max] (sieve-of-eratosthenes 1 (last (take (- max 1) (iterate (fn [x] (conj x true)) (vector false))))))
  ([last-prime sieve]
	;;(let [a (find-missing-number sieve last-prime)]
	(let [a (find-first-true sieve last-prime)]
	     ;;(println (format "got prime: %d" a))
	     ;;(println sieve)
	     (if (< a (count sieve))
		 (lazy-cons a (sieve-of-eratosthenes a (strike-multiples-from-sieve sieve a)))
		 (list)))))

(defn sieve-of-eratosthenes [max]
  (def sieve (build-base-sieve max))
  (for [x (range 2 (count sieve) 2)]
       (def sieve (assoc sieve (- x 1) false)))
  sieve)

(println (sieve-of-eratosthenes 10000))
;;(println (strike-multiples-from-sieve (list true true true true true true true true true true true) 2))