

(defn get-number-of-sequences [start]
  (loop [n start count 1]
    (if (= n 1)
      count
      (if (zero? (rem n 2))
	(recur (/ n 2) (inc count))
	(recur (+ (* 3 n) 1) (inc count))))))

(defn find-largest-sequence-under [limit]
  (loop [n 1 largest-n 1 no-of-sequences-in-largest-n 1]
    (if (>= n limit)
      (list largest-n no-of-sequences-in-largest-n)
      (let [sequences (get-number-of-sequences n)]
	(if (< no-of-sequences-in-largest-n sequences)
	  (recur (inc n) n sequences)
	  (recur (inc n) largest-n no-of-sequences-in-largest-n))))))

(println (find-largest-sequence-under 1000000))