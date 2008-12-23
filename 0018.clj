


(def input-data (list 75
		      95 64
		      17 47 82
		      18 35 87 10
		      20  4 82 47 65
		      19  1 23 75  3 34
		      88  2 77 73  7 63 67
		      99 65  4 28  6 16 70 92
		      41 41 26 56 83 40 80 70 33
		      41 48 72 33 47 32 37 16 94 29
		      53 71 44 65 25 43 91 52 97 51 14
		      70 11 33 28 77 73 17 78 39 68 17 57
		      91 71 52 38 17 14 91 43 58 50 27 29 48
		      63 66  4 68 89 53 67 30 73 16 69 87 40 31
		       4 62 98 27 23  9 70 98 73 93 38 53 60  4 23))

(defn triangle-number [n]
  (* n (/ (+ n 1) 2)))

(defn find-n-for-triangle-number-above [nbr]
  (loop [cnt 0 sum 0]
    (if (>= sum nbr)
      (dec cnt)
      (recur (inc cnt) (+ sum cnt)))))

(defn find-n-for-triangle-number-equaling [nbr]
  (loop [cnt 0 sum 0]
    (if (> sum nbr)
      -1
      (if (= sum nbr)
	(- cnt 1)
	(recur (inc cnt) (+ sum cnt))))))

(defn get-level-and-column-at [ptr]
  (let [lvl (find-n-for-triangle-number-above ptr)]
    (let [clm (- ptr (triangle-number (dec lvl)))]
      (list lvl clm))))

(defn get-pointer [level column]
  (+ (triangle-number (- level 1)) column))

;(println (find-n-for-triangle-number-equaling (count input-data)))

(defn make-network 
  ([lst] (make-network lst 1))
  ([lst ptr]
     (let [lvlcol (get-level-and-column-at ptr)]
       (let [lvl (first lvlcol) col (last lvlcol)]
	 (if (= lvl (find-n-for-triangle-number-equaling (count lst)))
	   (nth lst (dec ptr))
	   (list (nth lst (dec ptr))
		 (make-network lst (get-pointer (inc lvl) col))
		 (make-network lst (get-pointer (inc lvl) (inc col)))))))))

;(println (make-network input-data))
	       
(defn get-all-paths
  ([lst] (get-all-paths lst 1 (nth lst 0)))
  ([lst ptr sum]
     (let [lvlcol (get-level-and-column-at ptr)]
       (let [lvl (first lvlcol) col (last lvlcol)]
	 (if (= lvl (find-n-for-triangle-number-equaling (count lst)))
	   sum
	   (let [left (get-pointer (inc lvl) col) right (get-pointer (inc lvl) (inc col))]
	     (list
	      (get-all-paths lst left (+ sum (nth lst (dec left)))) 
	      (get-all-paths lst right (+ sum (nth lst (dec right)))))))))))

(defn flatten-list [lst] 
  (loop [old-list lst new-list (list) stack (list)]
    ;(println (count stack))
    (if (nil? old-list)
      new-list
	(if (list? (first old-list))
	  (if (nil? (rest old-list))
	    (recur (first old-list) new-list stack)
	    (recur (first old-list) new-list (cons (rest old-list) stack)))
	  (if (nil? (rest old-list))
	    (recur (first stack) (cons (first old-list) new-list) (rest stack))
	    (recur (rest old-list) (cons (first old-list) new-list) stack))))))
    

;(let [blah (get-all-paths input-data)]
;  (println (apply max (flatten-list blah))))