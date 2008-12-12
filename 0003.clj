(load-file "sieveoferatosthenes.clj")

;; get all primes up to sqrt of number
;; (sieve-of-eratosthenes (int (Math/sqrt (float num))))
;;
;; reverse iterate until a factor has been found

(defn factor-of? [factor of]
      (if (= (rem of factor) 0)
	  true
	  false))
(defn find-largest-factor-of-number 
    ([num] (find-largest-factor-of-number num (reverse (sieve-of-eratosthenes (int (Math/sqrt (float num)))))))
    ([num lst]
	  (println lst)
	  (if (= lst nil)
	      nil
	      (if (factor-of? (first lst) num)
		  (first lst)
		  (find-largest-factor-of-number num (rest lst))
		  ))))

(defn problem-three 
    [num]
  (find-largest-factor-of-number num))

(try 
(if (= (problem-three 13195) 29)
    (problem-three 600851475143)
    (println "tests failed"))
(catch Exception e (print "Error")))