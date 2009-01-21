(load-file "primes.clj")
(load-file "list-lib.clj")

(defn lni [a]
  (let [b (math/list-numbers-in a)]
       b))

(defn problem-forty-nine []
  (let [a (drop-while #(< % 999) (primes/sieve 10001))]
       (println a)
       (loop [n a m (rest a)]
	     (if (nil? (rest n))
		 nil
	       (if (> (+ (first m) (- (first m) (first n))) (last a))
		   (recur (rest n) (rest (rest n)))
		 (if (= (sort (lni (first n))) (sort (lni (first m))))
		     (if (and (in? m (+ (first m) (- (first m) (first n)))) 
			      (= (sort (lni (first n))) (sort (lni (+ (first m) (- (first m) (first n)))))))
			 (do
			     (println (first n) (first m) (+ (first m) (- (first m) (first n))))
			     (recur n (rest m)))
		       (recur n (rest m)))
		   (recur n (rest m))))))))
		   