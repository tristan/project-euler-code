

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

(defn get-number-of-sequences-using-memory [start memory]
  (loop [n start count 1]
    (let [mem (get memory n)]
      (if (not (nil? mem))
	(+ mem (- count 1))
	(if (zero? (rem n 2))
	  (recur (/ n 2) (inc count))
	  (recur (+ (* 3 n) 1) (inc count)))))))

(defn find-largest-sequence-under-using-memory [limit]
  (loop [n 1 largest-n 1 no-of-sequences-in-largest-n 1 memory-map {1 1}]
    (if (>= n limit)
      (list largest-n no-of-sequences-in-largest-n)
      (let [sequences (get-number-of-sequences-using-memory n memory-map)]
	(if (< no-of-sequences-in-largest-n sequences)
	  (recur (inc n) n sequences (assoc memory-map n sequences))
	  (recur (inc n) largest-n no-of-sequences-in-largest-n (assoc memory-map n sequences)))))))


;(println (find-largest-sequence-under 1000000))
(println (find-largest-sequence-under-using-memory 1000000))