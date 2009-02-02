(load-file "math.clj")

; METHOD: convert the input into a triangle similar to the input of problem 67
; padding with 9999999999 for values outside the square, then run the same method
; used to solve 67.

(def test-square
'(131 673 234 103 18
201 96  342 965 150
630	803	746	422	111
537	699	497	121	956
805	732	524	37	331)
)

(defn compute [lns]
  (loop [sums (first lns) lines (rest lns)]
    (if (nil? lines)
      sums
      (recur
       (map #(min %1 %2) (map #(+ %1 %2) (first lines) sums) (map #(+ %1 %2) (first lines) (rest sums)))
       (rest lines))))
)

(defn build-triangle [square]
  (let [sides (math/sqrt (count square))]
    (loop [lines '()]
      (let [line-length (inc (count lines))]
	(if (< (dec (* sides 2)) line-length)
	  lines
	  (recur (cons
		  (loop [x 0 y (dec line-length) line '()]
		    (if (< y 0)
		      line
		      (recur (inc x) (dec y) (cons (if (or (<= sides x) (<= sides y)) 999999999 (nth square (+ (* sides y) x))) line))))
		  lines)))))))

(def data (map #(math/parse-integer %) (re-seq #"[0-9]+" (slurp "matrix.txt"))))
;(println (count data) (math/sqrt (count data)))

(println (compute (build-triangle data)))