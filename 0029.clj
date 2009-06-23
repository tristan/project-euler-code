(load-file "math.clj")
(load-file "list-lib.clj")

;.............. distinct broken?
(let [a (sort (distinct (apply concat (map (fn [i] (map (fn [j] (pow i j)) (range 2 101))) (range 2 101)))))]
  (println (count a))
  (loop [b (rest a) c (first a)]
    (if (nil? b)
      (println "done")
      (if (= (first b) c)
	(do 
	  (println c)
	  (recur (rest b) (first b)))
	(recur (rest b) (first b))))))
	
      

;(println (count (distinct (sort (apply concat (map (fn [y] (let [a (map (fn [x] (pow y x)) (range 2 11) (range 2 11)))))))