(load-file "primes.clj")
(load-file "math.clj")

(time (def prime-sieve (sieve 1000000)))

(defn list-contains? [lst n]
  (if (empty? lst)
    false
    (if (= (first lst) n)
      true
      (recur (rest lst) n))))

(defn list-recuring-numbers [n]
  (let [n (sort (math/list-numbers-in n))]
    (loop [n n r '()]
      (if (empty? (rest n))
	(distinct r)
	(if (= (first (rest n)) (first n))
	  (recur (rest (rest n)) (cons (first n) r))
	  (recur (rest n) r))))))

(defn list-to-number [lst]
  (loop [sum 0 lst lst]
    (if (empty? lst)
      sum
      (recur (+ (* 10 sum) (first lst)) (rest lst)))))

;;TODO: THIS IS TOO SLOW. OPTIMISE
(println
(time
(loop [primes (drop-while #(< (count (math/list-numbers-in %)) 3) prime-sieve)]
  (when-not (empty? primes)
    (let [subject (math/list-numbers-in (first primes))]
      (let [vals
	    (loop [original-numbers '(0 1 2 3 4 5 6 7 8 9) found '()]
	      (if (empty? original-numbers)
		found
		(if (list-contains? subject (first original-numbers))
		  (recur (rest original-numbers) (cons
						  (loop [changed-numbers '(0 1 2 3 4 5 6 7 8 9) found '()]
						    (if (empty? changed-numbers)
						      found
						      (let [new-number (list-to-number 
									(map #(if (= % (first original-numbers)) 
										(first changed-numbers) 
										%) 
									     subject))]
							(if (and (= (count (math/list-numbers-in new-number))
								    (count subject))
								 (prime? new-number primes))
							  (recur (rest changed-numbers) (cons new-number found))
							  (recur (rest changed-numbers) found)))))
						  found))
		  (recur (rest original-numbers) found))))]
	(let [test (loop [i vals]
		    (if (or (empty? i) (not (= (count (first i)) 8)))
		      nil
		      (first i)))]
	  (if (nil? test)
	    (recur (rest primes))
	    test))))))
))