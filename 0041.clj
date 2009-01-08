;(load-file "math.clj")
(load-file "list-lib.clj")

(defn fact [permutation n]
  (loop [j 1 k permutation f '()]
    (if (< n j)
      f
      (recur (inc j) 
	     (math/floor (/ k j)) 
	     (cons (inc (rem k j)) f)))))

(defn get-permutation [p n]
  (loop [i n f (map #'inc (fact p n))]
    (if (< i 1)
      (map #'dec f)
      (recur (dec i)
	     (let [st (take i f)]
	       (concat st
		       (map #(if (>= % (last st)) (inc %) %)
			    (drop i f))))
	     ))))

(defn test-gp []
  (println (get-permutation 0 3))
  (println (get-permutation 1 3))
  (println (get-permutation 2 3))
  (println (get-permutation 3 3))
  (println (get-permutation 4 3))
  (println (get-permutation 5 3))
  (println (get-permutation 6 3)))

;(test-gp)

(defn problem-forty-one []
  (loop [nos 9 p (dec (math/! 9))]
    (if (> 0 nos)
      0
      (if (> 0 p)
	(recur (dec nos) (dec (math/! (dec nos))))
	(let [n (get-permutation p nos)]
	  (if (math/prime? (list-to-number n))
	    n
	    (recur nos (dec p))))))))
       
;(time (println (problem-forty-one)))