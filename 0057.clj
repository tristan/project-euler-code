
(defn expand-helper [n]
      (if (= 1 n)
	  (/ 1 2)
	(/ 1 (+ 2 (expand-helper (dec n))))))

(defn expand-fraction [n]
  (+ 1 (expand-helper n)))

; the above functions expand the fraction from scratch to the nth extraction
; the following function iterates to the next expansion where n is the previous expansion
(defn expand-fraction [n]
  (+ 1 (/ 1 (+ 1 n))))

(loop [n 1 frac (/ 3 2) counter 0]
      (println n)
      (if (> n 1000)
	  (do
	      (println "result:" counter)
	      counter)
	; fractions made easy by clojure!
	(let [a (take-while #(not (= % \/)) (str frac)) b (rest (drop-while #(not (= % \/)) (str frac)))]
	     (if (> (count a) (count b))
		 (recur (inc n) (expand-fraction frac) (inc counter))
	       (recur (inc n) (expand-fraction frac) counter)))))