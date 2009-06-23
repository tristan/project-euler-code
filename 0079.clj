(load-file "math.clj")

(defn load-data [fn]
  (map (comp #'reverse #'math/list-numbers-in #'math/parse-integer) (re-seq #"[0-9][0-9][0-9]" (slurp fn))))

(defn extend-set [s l]
  (if (nil? l)
    s
    (recur (conj s (first l)) (rest l))))

(defn problem-seventy-nine []
  (let [vals
  (apply hash-map 
	 (reverse
	  (apply concat 
		 (map #(list (first %) (count (last %))) 
		      (let [initial-data (load-data "keylog.txt")]
					; build a map with each number as a key, the value for each key
					; is a set of all the numbers found to be in a lower position
					; the the key. once all entries have been processed counting the
					; number of values in each key gives it's position in the result
			(loop [data initial-data m {}] 
			  (if (nil? data)
			    m
			    (recur (rest data)
				   (loop [t (first data) n m]
				     (if (nil? t)
				       n
				       (let [s (get n (first t) #{})]
					 (recur (rest t) (assoc n 
							   (first t)
							   (extend-set s (rest t))))
					 )))))))
		      ))))]
    (loop [k (keys vals) res '()]
      (if (nil? k)
	res
	(recur (rest k) (cons (get vals (first k)) res))))))

(println (problem-seventy-nine))
	  