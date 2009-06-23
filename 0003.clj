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
       (loop [l lst]
	 (if (= (count l) 0)
	   nil
	   (if (factor-of? (first l) num)
	     (first l)
	     (recur (rest l)))))))

(defn problem-three 
    [num]
  (find-largest-factor-of-number num))

(defn test []
  (= (problem-three 13195) 29))

(println (problem-three 600851475143))