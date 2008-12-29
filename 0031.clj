(load-file "math.clj")

(defn sum-coins [coins]
  (apply #'+ (map #'* (vals coins) (keys coins))))

(defn get-largest-available-denomination [coins]
  (loop [coin-denominations (keys coins) denomination-of-max 0 max 0]
    (if (nil? coin-denominations)
      denomination-of-max
      (if (>= (get coins (first coin-denominations)) max)
	(recur (rest coin-denominations) (first coin-denominations) (get coins (first coin-denominations)))
	(recur (rest coin-denominations) denomination-of-max max)))))

(defn get-smallest-available-denomination-that-is-not-the-lowest-possible [coins]
  (loop [coin-denominations (rest (sort (keys coins)))]
    (if (nil? coin-denominations)
      nil
      (if (> (get coins (first coin-denominations)) 0)
	(first coin-denominations)
	(recur (rest coin-denominations))))))

(defn get-next-smallest-denomination [denomination coins]
  (first (drop-while #(>= % denomination) (keys coins))))

(defn get-next-largest-denomination [denomination coins]
  (last (take-while #(< % denomination) (reverse (keys coins)))))

(defn number-of-coins-of-denomination [coins denomination]
  (get coins denomination))

;(defn break-up-denomination-into-lower-denomination

(defn iterate-coins [coins]
  (let [total (sum-coins coins)]
    (println "sum" total)
    (let [denominations (take-while #(<= % total) (sort (keys coins)))]
      ; find smallest denomination which can be broken up
      (let [usable-denominations
	    (reverse (take (inc (count (take-while #(> 1 (get coins %)) denominations))) denominations))]
	(println "usable-denominations: " usable-denominations)
	(loop [usd usable-denominations remainder 1 change {}]
	  (if (> 1 remainder)
	    change
	    (let [remain (rem (first usd) (first (rest usd)))]
	      (let [val (/ (- (first usd) remain) (first (rest usd)))]
		(println "denomination:" (first usd) "val:" val "remainder:" remain)
		(recur (rest usd) remain (merge change {(first usd) (+ (get change (first usd) 0) val)}))))))))))
    

(defn iterate-coins [coins]
  ; store largest available denomination
  (let [largest (get-largest-available-denomination coins)]
    ;find smallest coin which is larger than 1
    (let [smallest-useable (get-smallest-available-denomination-that-is-not-the-lowest-possible coins)]
      (if (nil? smallest-useable)
	nil ; we have nothing to break down, so return nil for now, later we can re-combine to largest possible coin
	nil))))

(def coins '(200 100 50 20 10 5 2 1))

(defn get-next-lower-denomination [d]
  (if (nil? d)
    nil
    (first (drop-while #(>= % d) coins))))

(defn count-lower-denominations [d]
  (count (drop-while #(>= % d) coins)))

(defn divide-into-denominations [value highest-coin]
  (if (nil? highest-coin)
    nil
    (loop [denominations (drop-while #(> % highest-coin) coins) remainder value new-coins '()]
      (if (or (nil? denominations) (zero? remainder))
	new-coins
	(recur (rest denominations) (rem remainder (first denominations)) (concat new-coins (take (floor (/ remainder (first denominations))) (repeat (first denominations)))))))))

; if len is gt 2

(defn build-permutations [cns]
  (if (= (* (last coins) (count (rest cns))) (apply #'+ (rest cns)))
    (cons cns nil)
    (let [splt (split-with #(not (= % (last coins))) cns)]
      (loop [denominations (drop-while #(>= % (last (first splt))) (last (first splt))) vals '()]
	(if (nil? denominations)
	  vals
	  (recur (rest denominations)
		 (cons cns (build-permutations (concat (reverse (rest (reverse (first splt)))) 
						       (divide-into-denominations (last (first splt)) (first denominations))
						       (last splt))))))))))

(defn build-permutations 
  ([total] (build-permutations total (dec total)))
  ([total highest-denomination]
     (println "starting for total" total)
     (if (= total (last coins))
       (do
	 (println "finished for total" total)
	 (list (list total)))
       (loop [lower-denominations (drop-while #(> % highest-denomination) coins) permutations '()]
	 (println "total, lower-denoms:" total lower-denominations permutations)
	 (if (< (count lower-denominations) 1)
	   (do
					;(let [a (cons (divide-into-denominations total (last coins)) permutations)]
	     (let [a permutations]
	       (println "finished for total" total a)
	       a))
	   (let [cns (divide-into-denominations total (first lower-denominations))]
	     (do
	       (println "cns:" cns)
	       (if (= (first cns) (last coins))
		 (recur (rest lower-denominations)
			(cons cns permutations))
		 (if (= (count cns) 1)
		   (recur (rest lower-denominations) permutations)
		   (recur (rest lower-denominations)
			  (concat permutations (cons cns (map #(cons (first cns) %) (build-permutations (apply #'+ (rest cns)) (first cns)))))
			  ))))))))))


(defn build-permutations 
  ([total] (cons (divide-into-denominations total (first (drop-while #(> % total) coins)))
		 (build-permutations total (drop-while #(>= % total) coins))))
  ([total denominations]
     ; if we are at the last denomination
     (if (<= (count denominations) 1)
       (list (divide-into-denominations total (first denominations)))
       (if (> (first denominations) total)
	 (build-permutations total (rest denominations))
	 (let [division (divide-into-denominations total (first denominations))]
	   (concat 
	    (if (> (count division) 1)
	      (map #(cons (first division) %) (build-permutations (apply #'+ (rest division)) denominations))
	      (list division))
	    (build-permutations total (rest denominations)))
	   )))))

(defn helper [d]
  (divide-into-denominations d (get-next-lower-denomination (get-next-lower-denomination d))))

(defn problem-thirty-one []
  ;(println (build-permutations '(5 5)))
  ;(println (build-permutations '(2 2 2 2 2)))
  (println (count (build-permutations 1)))
  (println (count (build-permutations 2)))
  (println (count (build-permutations 5)))
  (println (count (build-permutations 10)))
  (println (count (build-permutations 20)))
  (println (count (build-permutations 50)))
  (println (count (build-permutations 100)))
  (println (count (build-permutations 200)))
  ;(println (count (build-permutations 200)))
  ;(println (build-permutations '(5 5 5 5)))
  ;(println (build-permutations '(2 2 2 2 2 2 2 2 2 2)))

  ;(println 1 (helper 1))
  ;(println 2 (helper 2))
  ;(println 5 (helper 5))
  ;(println 10 (helper 10))
  ;(println 20 (helper 20))
  ;(println 50 (helper 50))
  ;(println 100 (helper 100))
  ;(println 200 (helper 200)))
;  (let [initial-coins {200 0 100 0 50 0 20 0 10 0 5 2 2 0 1 0}]
;    (iterate-coins initial-coins)))
;    (loop [coins initial-coins total (sum-coins initial-coins) permutations 0]
;      (if (= (get coins 1) total)
;	permutations
;       nil))))
   )

(problem-thirty-one)