(load-file "math.clj")

(defn iterate-chain [n]
  (math/sum (map #'math/! (math/list-numbers-in n))))

(defn count-chain [start cache]
  (loop [vals '() n start]
    ;(println vals n)
    (let [cnt (get cache n)]
      (if (not (nil? cnt))
	(list (+ cnt (count vals))
	      (loop [remain (reverse vals) new-cache cache]
		(if (nil? remain)
		  new-cache
		  (recur (rest remain)
			 (assoc new-cache (first remain) (+ cnt (count remain)))))))
	(recur (cons n vals) (iterate-chain n))))))

(defn problem-seventy-four []
  (loop [cache {169 3
		363601 3
		1454 3
		169 3
		871 2
		45361 2
		872 2
		45362 2
		69 5
		78 4
		540 2
		145 1
		1 1
		2 1
		40585 1} ; gah!
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
       