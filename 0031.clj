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

(defn problem-thirty-one []
  (println (build-permutations 1))
  (println (build-permutations 2))
  (println (build-permutations 5))
  (println (build-permutations 10))
  (println (build-permutations 20))
  (println (build-permutations 50))
  (println (build-permutations 100))
  (println (build-permutations 200))
   )

(problem-thirty-one)