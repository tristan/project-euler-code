(load-file "math.clj")

(defn abundant? [nbr]
  (> (sum (list-divisors nbr)) nbr))

(defn deficient? [nbr]
  (< (sum (list-divisors nbr)) nbr))

(defn perfect-number? [nbr]
  (= (sum (list-divisors nbr)) nbr))

(defn get-all-abundant-numbers-less-than [limit]
  (if (< limit 1)
    nil
    (if (abundant? limit)
      (cons limit (get-all-abundant-numbers-less-than (dec limit)))
      (get-all-abundant-numbers-less-than (dec limit)))))

(defn unique [lst]
  (loop [l lst n (list)]
    (if (nil? l)
      (sort n)
      (if (in? n (first l))
	(recur (rest l) n)
	(recur (rest l) (cons (first l) n))))))
      

(defn get-all-sums-of-pairs-in-list
  [initial-lst limit]
  (loop [lst initial-lst alt-lst initial-lst sums (list)]
    ;(when (zero? (rem (count sums) 10000))
    ;(println (count sums)))
    (if (nil? lst)
      (unique (sort sums))
      (if (nil? alt-lst)
	(recur (rest lst) (rest lst) sums)
	(let [s (+ (first lst) (first alt-lst))]
	  ;(println (first lst) "+" (first alt-lst) "=" s (list-divisors s))
	  (if (< limit s)
	    (recur (rest lst) (rest lst) sums)
	    (recur lst (rest alt-lst) (cons s sums))))))))
	
(defn can-be-written-as-the-sum-of-two-numbers-in-the-list? [the-list nbr]
  (let [largest-pair (loop [lst the-list]
		       (if (nil? lst)
			 (last the-list)
			 (if (>= nbr (* 2 (first lst)))
			   (first lst)
			   (recur (rest lst)))))]
    (if (< (* 2 largest-pair) nbr)
      false
      (if (= (* 2 largest-pair) nbr)
	true
	false))))

; TODO: OPTIMIZE!
(defn problem-twenty-three []
  (println "starting")
  (let [sums (get-all-sums-of-pairs-in-list (reverse (get-all-abundant-numbers-less-than 28123)) 28123)]
    (println "step done")
    (println "got" (count sums) "sums")
    ;(println sums)
    (let [s (loop [lst sums ctr 1 s 0]
	      (if (> ctr (last sums))
		(do
		  ;(println "left over sums" lst)
		  s)
		(if (nil? lst)
		  s
		  (do
		    ;(println (first lst) "=" ctr "?:" (= (first lst) ctr))
		    (if (= (first lst) ctr)
		      (do 
			;(println "removing" ctr "from list")
			(recur (rest lst) (inc ctr) s))
		      (recur lst (inc ctr) (+ ctr s)))))))]
      (println s)
      )))

(defn test []
  (println (get-all-abundant-numbers-less-than 100))
  (println (get-all-sums-of-pairs-in-list (reverse (get-all-abundant-numbers-less-than 100)) 100))
  (let [a (list-divisors 4)]
    (println a)
    (println (sum a)))
  (println (abundant? 4))
  (println (deficient? 4)))

(problem-twenty-three)
;(test)