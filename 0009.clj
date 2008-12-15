(defn brute-force-problem-nine []
  (loop [a 100 b 101]
    (if (< b (inc a)) ; if a gets greater than or equal to b then increment b
      (recur 100 (inc b))
      (let [c (int (. Math (sqrt (+ (* a a) (* b b)))))]
	(if (and (= (+ a b c) 1000) (= (rem (+ (* a a) (* b b)) c) 0))
	  (* a b c)
	  (recur (inc a) b))))))

(println (brute-force-problem-nine))