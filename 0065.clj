(load-file "math.clj")

(defn solve [start recuring places]
  (loop [nums (reverse (take (rem (- places (count start)) (count recuring)) recuring)) 
	 left (- places (count start))
	 total 0]
    ;(print (first nums) ", ")
    (if (> 1 left)
      (if (zero? left)
	(recur (reverse start) (dec left) total)
	(if (nil? (rest nums))
	  (+ total (first nums))
	  (recur (rest nums) left (/ 1 (+ (first nums) total)))))
      (if (nil? nums)
	(recur (reverse recuring) left total)
	(recur (rest nums) (dec left) (/ 1 (+ (first nums) total)))))))

(defn solve-reverse [start recuring]
  (loop [nums recuring
	 total 0]
    (if (nil? nums)
      (loop [nums start total total]
	(if (nil? (rest nums))
	  (+ total (first nums))
	  (recur (rest nums) (/ 1 (+ (first nums) total)))))
      (recur (rest nums) (/ 1 (+ (first nums) total))))))

;(println (+ 4.0 (/ 1 (+ 1 (/ 1 (+ 3 (/ 1 (+ 1 (/ 1 8)))))))))

(defn gen-e ([n] (gen-e n 0 1))
  ([n p k]
     (println "gen-e:" n p k)
     (when (pos? n)
       (lazy-cons (if (= p 1) (* 2 k) 1) 
		  (gen-e (dec n) 
			 (rem (inc p) 3)
			 (if (= p 1) (inc k) k))))))

(defn reverse-gen-e 
  ([n]
     (reverse-gen-e n (rem (- 3 (rem n 3)) 3) (int (math/round (/ n 3) 0))))
  ([n p k]
     (when (pos? n)
       (lazy-cons (if (= p 1) (* 2 k) 1)
		  (reverse-gen-e (dec n)
				 (rem (inc p) 3)
				 (if (= p 1) (dec k) k))))))


;(loop [n 0] (if (> n 10) nil (do (println (solve-reverse '(2) (reverse-gen-e n))) (recur (inc n)))))

(println (time (math/sum (math/list-numbers-in (.numerator (solve-reverse '(2) (reverse-gen-e 99)))))))