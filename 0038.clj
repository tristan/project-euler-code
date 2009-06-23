(load-file "0041.clj")

(defn problem-thirty-eight []
  (loop [n (range (dec (math/! 9)) -1 -1)]
    (let [p (get-permutation (first n) 9)]
      (let [r (loop [x 1 a 0 b 1 cnt 2]
		(if (< 6 x)
		  nil
		  (if (= b 9)
		    p
		    (let [t (* (list-to-number (take x p)) cnt)]
		      (if (= t (list-to-number (take (count (str t)) (drop b p))))
			(recur x b (+ b (count (str t))) (inc cnt))
			(recur (inc x) 0 (inc x) 2))))))]
	(if (nil? r)
	  (recur (rest n))
	  r)))))