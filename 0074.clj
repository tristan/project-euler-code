(load-file "math.clj")

(defn count-chain [start cache]
  (loop [vals '() n start]
    (let [val (sort (math/list-numbers-in n))]
      (let [cnt (get cache val)]
	(if (not (nil? cnt))
	  (list (+ cnt (count vals))
		(loop [remain (reverse vals) new-cache cache]
		  (if (nil? remain)
		    new-cache
		    (recur (rest remain)
			   (assoc new-cache (first remain) (+ cnt (count remain)))))))
	  (recur (cons val vals) (math/sum (map #'math/! val))))))))

(defn problem-seventy-four []
  (loop [cache {'(1 6 9) 3
		'(0 1 3 3 6 6) 3
		'(1 4 4 5) 3
		'(1 7 8) 2
		'(1 3 4 5 6) 2
		'(2 7 8) 2
		'(2 3 4 5 6) 2
		'(6 9) 5
		'(7 8) 4
		'(0 4 5) 2
		'(1 4 5) 1
		'(1) 1
		'(2) 1
		'(0 4 5 5 8) 1} ; gah!
	 n 3
	 equal-to-sixty 0]
    (if (> n 1000000)
      equal-to-sixty
      (let [r (count-chain n cache)]
	;(println n (first r))
	(if (= (first r) 60)
	  (recur (last r) (inc n) (inc equal-to-sixty))
	  (recur (last r) (inc n) equal-to-sixty))))))

;(println (first (count-chain 14558 {})))
(println (time (problem-seventy-four)))
       