
(defn list-numbers [nbr]
  (let [one (rem nbr 10)]
    (list (/ (- nbr one) 10) one)))

(defn get-common-value [l1 l2]
  (first (filter #(not (= % false)) (map #(if (= %1 %2) %1 false) l1 (reverse l2))))) ;ignoring where numbers are in the same place

(println (time
(loop [denominator 10 oresults '()]
  (if (< 100 denominator)
    (apply #'* oresults)
    (let [results
     (loop [divisor 10 iresults '()]
       (if (<= denominator divisor)
	 iresults
	 (let [de (list-numbers denominator) di (list-numbers divisor) real-result (/ divisor denominator)]
	   (if (= (last de) (last di) 0) ;ignore trivial case
	     (recur (inc divisor) iresults)
	     (let [common-val (get-common-value di de)]
	       (if (nil? common-val)
		 (recur (inc divisor) iresults)
		 ;(let [ndiv (first (filter #(not (= % common-val)) di)) nden (first (filter #(not (= % common-val)) de))]
		 (let [ndiv (if (= (first di) common-val) (last di) (first di)) nden (if (= (first de) common-val) (last de) (first de))]
		   (if (or (zero? nden))
		     (recur (inc divisor) iresults) ; avoid divide by zero
		     (if (= real-result (/ ndiv nden))
		       (do
			 (println divisor "/" denominator "=" ndiv "/" nden)
			 (recur (inc divisor) (cons (/ ndiv nden) iresults)))
		       (recur (inc divisor) iresults))))))))))]
      (recur (inc denominator) (concat results oresults)))))
	  ))

; quicker solution using if a/b = c/d then a*d = c*b
(println (time
(loop [denominator 10 results '()]
  (if (< 100 denominator)
    (apply #'* results)
    (recur (inc denominator)
	   (concat results
		   (loop [divisor 10 found '()]
		     (if (<= denominator divisor)
		       found
		       (let [de (list-numbers denominator) di (list-numbers divisor) real-result (/ divisor denominator)]
			 (let [common-val (get-common-value di de)]
			   (if (nil? common-val)
			     (recur (inc divisor) found)
			     (if (or (= (* denominator (first di)) (* divisor (last de))) (= (* denominator (last di)) (* divisor (first de))))
			       (do
				 (println divisor "/" denominator)
				 (recur (inc divisor) (cons (/ divisor denominator) found)))
			       (recur (inc divisor) found)))))))))))
))