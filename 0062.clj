(load-file "math.clj")

(println
(time
(loop [n 1 r {}]
  (let [key (sort (math/list-numbers-in (math/pow n 3)))]
    (let [old-list (get r key '())]
      (if (= (count old-list) 4)
	(cons (math/pow (last old-list) 3) (cons n old-list))
	(recur (inc n) (assoc r key (cons n old-list)))))))
))