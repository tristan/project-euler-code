(ns libs.pandigital)

(defn fact [permutation start end]
  (loop [j 1 k permutation f '()]
    (if (< (inc (- end start)) j)
      f
      (recur (inc j) 
	     (int (. Math (floor (/ k j))))
	     (cons (+ start (rem k j)) f)))))

(defn get-permutation [p start end]
  (loop [i (- end start) f (map #'inc (fact p start end))]
    (if (< i 1)
      (map #'dec f)
      (recur (dec i)
	     (let [st (take i f)]
	       (concat st
		       (map #(if (>= % (last st)) (inc %) %)
			    (drop i f))))
	     ))))
