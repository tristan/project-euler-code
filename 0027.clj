(load-file "primes.clj")

(def prime-sieve (sieve 10000))
(println "done building primes")
(defn prime? [nbr] 
  (if (< nbr 2)
    false
    (loop [sqrtnbr (sqrt nbr) ps prime-sieve]
      (if (nil? ps)
	(do
	  (println "WARNING: EXCEEDED SIZE OF SIEVE")
	  true)
	(if (< sqrtnbr (first ps))
	  true
	  (if (zero? (rem nbr (first ps)))
	    false
	    (recur sqrtnbr (rest ps))))))))
; TODO: update to use prime? from primes.clj

(defn quadratic [n a b]
  (+ (* n n) (* a n) b))

;(println (quadratic 2 1 41))
;(println (apply quadratic '(2 1 41)))

(defn funky-soln [] ; attempt to use maps and such. got to this point then flattening the lists got a bit complicated
  (let [limit 10] 
                                     ; stuff starts here
  (println 
   (map (fn [a] 
	  (filter #(not (nil? %))
	  (map (fn [b] 
		 (take-while #(prime? (apply quadratic %)) 
			     (map #(list % a b) 
				  (iterate #(inc %) 0)
				  )))
	       (range (* -1 (dec limit)) limit))) )
	(range (* -1 (dec limit)) limit))
   ))
)

; TODO: make quicker by iterating through a list of primes lower than 1000 rather than simply incrementing b (n=0 = b, thereform b must be prime)
(defn long-soln [lim]
  (let [res
	(reduce #(if (< (last %1) (last %2)) %2 %1)
		(loop [a (* -1 (dec lim)) b (* -1 (dec lim)) solns '()]
		  (if (> a lim)
		    solns
		    (if (> b lim)
		      (recur (inc a) (* -1 (dec lim)) solns)
		      (if (prime? (quadratic 0 a b))
			(recur a (inc b) (cons (list a b (inc (count 
							       (take-while #(prime? (apply quadratic %)) ; keep testing values of n until the result is not a prime
									   (map #(list % a b)
										(iterate #(inc %) 1))))))
					       solns))
			(recur a (inc b) solns))))))]
    (println "quadratic:" res)
    (println "product of coefficients:" (* (first res) (second res)))))
(long-soln 1000)