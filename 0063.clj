(load-file "math.clj")

(defn problem-sixty-three []
  (loop [n 1 c 1 r 0 fnd false]
	(let [a (math/pow c n)]
	     (let [acnt (count (math/list-numbers-in a))]
		  (if (= acnt n)
		      (do
			  (println a "=" c "^" n)
			  (recur n (inc c) (inc r) true))
		    (if (> acnt n)
			(if (not fnd)
			    r
			  (do
			      (println c "^" n "len>")
			      (recur (inc n) 1 r false)))
		      (recur n (inc c) r fnd)))))))