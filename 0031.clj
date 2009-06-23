(load-file "math.clj")

(def coins '(200 100 50 20 10 5 2 1))

(defn divide-into-denominations [value highest-coin]
  (if (nil? highest-coin)
    nil
    (loop [denominations (drop-while #(> % highest-coin) coins) remainder value new-coins '()]
      (if (or (nil? denominations) (zero? remainder))
	new-coins
	(recur (rest denominations) (rem remainder (first denominations)) (concat new-coins (take (floor (/ remainder (first denominations))) (repeat (first denominations)))))))))

(defn build-permutations 
  ([total] (inc (build-permutations total (drop-while #(>= % total) coins))))
  ([total denominations]
     ; if we are at the last denomination
     (if (<= (count denominations) 1)
       1;(list (divide-into-denominations total (first denominations)))
       (if (> (first denominations) total)
	 (build-permutations total (rest denominations))
	 (let [division (divide-into-denominations total (first denominations))]
	   (+ ;(concat
	    (if (> (count division) 1)
	      (build-permutations (apply #'+ (rest division)) denominations)
	      ;(map #(cons (first division) %) (build-permutations (apply #'+ (rest division)) denominations))
	      1) ;(list division))
	    (build-permutations total (rest denominations)))
	   )))))

(defn count-down 
  [total]
  (loop [a (range total -1 -200)
	 cnta 0]
    (if (nil? a)
      cnta
      (recur (rest a)
	     (+ cnta (loop [b (range (first a) -1 -100) cntb 0]
	       (if (nil? b)
		 cntb
		 (recur (rest b)
			(+ cntb (loop [c (range (first b) -1 -50) cntc 0]
			  (if (nil? c)
			    cntc
			    (recur (rest c)
				   (+ cntc (loop [d (range (first c) -1 -20) cntd 0]
				     (if (nil? d)
				       cntd
				       (recur (rest d)
					      (+ cntd (loop [e (range (first d) -1 -10) cnte 0]
						(if (nil? e)
						  cnte
						  (recur (rest e)
							 (+ cnte (loop [f (range (first e) -1 -5) cntf 0]
							   (if (nil? f)
							     cntf
							     (recur (rest f)
								    (+ cntf (count (range (first f) -1 -2)))
								    ))))))))))))))))))))))))


(defn count-down ([total] (count-down total '(200 100 50 20 10 5 2 1)))
  ([total denominations]
     (loop [x (range total -1 (* -1 (first denominations))) cnt 0]
       (if (nil? x)
	 cnt
	 (if (nil? (rest (rest denominations)))
	   (+ cnt (count x))
	   (recur (rest x)
		  (+ cnt (count-down (first x) (rest denominations)))))))))

(defn problem-thirty-one []
  (println (time (build-permutations 1)))
  (println (time (count-down 1)))
  (println (time (build-permutations 2)))
  (println (time (count-down 2)))
  (println (time (build-permutations 5)))
  (println (time (count-down 5)))
  (println (time (build-permutations 10)))
  (println (time (count-down 10)))
  (println (time (build-permutations 20)))
  (println (time (count-down 20)))
  (println (time (build-permutations 50)))
  (println (time (count-down 50)))
  (println (time (build-permutations 100)))
  (println (time (count-down 100)))
  (println (time (build-permutations 200)))
  (println (time (count-down 200)))
   )

(problem-thirty-one)