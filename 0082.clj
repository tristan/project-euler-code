
; just add the numbers from each row
(defn first-pass [this-col next-col]
  (if (empty? this-col)
    '()
    (concat (list (+ (first this-col) (first next-col)))
	       (first-pass (rest this-col) (rest next-col)))))

; find if the current stored shotest path can be shortened by
; going up or down
(defn nth-pass [this-col last-results]
  (loop [n 0 new-results '()]
    (if (= n (count this-col))
      new-results
      (if (= 0 n)
	(recur (inc n)
	       (concat new-results
		       (list
			(min (nth last-results n)
			     (+ (nth this-col n)
				(nth last-results (inc n))))
			)))
	(if (= (dec (count this-col)) n)
	  (recur (inc n)
		 (concat new-results
			 (list
			  (min (nth last-results n)
			       (+ (nth this-col n)
				  (nth last-results (dec n))))
			  )))
	  (recur (inc n)
		 (concat new-results
			 (list
			  (min (nth last-results n)
			       (+ (nth this-col n)
				  (nth last-results (dec n)))
			       (+ (nth this-col n)
				  (nth last-results (inc n)))))))
	  )))))

; run first pass, then continually run nth-pass
; until the results returned are the same as the
; previous run
(defn shortest-path [this-col next-col]
  (loop [results (first-pass this-col next-col)]
    (let [new-results (nth-pass this-col results)]
      (if (= results new-results)
	results
	(recur new-results)))))

(require '(clojure.contrib [str-utils :as str-utils]))

(defn string-list-to-number-list [lst]
  (if (empty? lst)
    '()
    (concat (list (. Integer (parseInt (first lst)))) (string-list-to-number-list (rest lst)))))

(defn rows-to-columns 
  ([rows] (rows-to-columns rows 0 '()))
  ([rows n columns]
     (if (= (count rows) n)
       columns
       (recur rows
	      (inc n)
	      (concat columns
		      (list
		       (loop [rows rows col-n '()]
			 (if (empty? rows)
			   col-n
			   (recur (rest rows)
				  (concat col-n (list (nth (first rows) n)))
				  )))
		       ))))))
  

(defn matrix-file-reader [file-name]
  (let [file-in (slurp file-name)]
    (let [lines (map #(string-list-to-number-list (str-utils/re-split #"[,]" %))
		     (map #(str-utils/re-gsub #"\r\n" "" %)
			  (filter #(not (or (= "" %) (= "\n" %) (= "\r\n" %))) 
				  (str-utils/re-partition #"[0-9,]+"
							  file-in))))]
      (rows-to-columns lines)
      )))

(defn solve []
  (let [input (reverse (matrix-file-reader "matrix.txt"))]
    (loop [last-col (first input)
	   remain (rest input)]
      (if (empty? remain)
	(apply #'min last-col)
	(recur
	 (shortest-path (first remain) last-col)
	 (rest remain))))))

(println (time (solve)))