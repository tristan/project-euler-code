(load-file "math.clj")

(defn bouncy? 
  ([n] (bouncy? (map #'int (str n)) false false))
  ([n up down]
     (if (and up down)
       true
       (if (nil? (rest n))
	 false
	 (if (< (first n) (first (rest n)))
	   (recur (rest n) true down)
	   (if (> (first n) (first (rest n)))
	     (recur (rest n) up true)
	     (recur (rest n) up down)))))))

(println (time
(loop [n 101 cnt 0]
  ;(when (zero? (rem n 1000))
  ;  (println (dec n) cnt (/ cnt (dec n))))
  (if (= (/ cnt (dec n)) 99/100)
    (dec n)
    (if (bouncy? n)
      (recur (inc n) (inc cnt))
      (recur (inc n) cnt))))
  ))