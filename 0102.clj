(load-file "math.clj")

(defn vector-diff [a b]
  (list
   (- (first b) (first a))
   (- (first (rest b)) (first (rest a)))))

(defn det [u v]
  (-
   (* (first u) (first (rest v)))
   (* (first (rest u)) (first v))))

(defn solve-for-a-and-b [v v0 v1 v2]
  (list
   (/ 
    (- (det v v2) (det v0 v2))
    (det v1 v2))
   (* -1
      (/
       (- (det v v1) (det v0 v1))
       (det v1 v2)))))

(defn in-triangle? [A B C v]
  (let [s (solve-for-a-and-b
	   v
	   A
	   (vector-diff A B)
	   (vector-diff A C))]
    (let [a (first s) b (first (rest s))]
      (if (and (> a 0) (> b 0) (< (+ a b) 1))
	true
	false))))

(println
(time
(let [input
 (map #(list (list (math/parse-integer (first (rest %)))
		   (math/parse-integer (first (rest (rest %)))))
	     (list (math/parse-integer (first (rest (rest (rest %)))))
		   (math/parse-integer (first (rest (rest (rest (rest %)))))))
	     (list (math/parse-integer (first (rest (rest (rest (rest (rest %)))))))
		   (math/parse-integer (first (rest (rest (rest (rest (rest (rest %))))))))))
      (re-seq #"([\-0-9]+),([\-0-9]+),([\-0-9]+),([\-0-9]+),([\-0-9]+),([\-0-9]+)" 
	      (slurp "triangles.txt")))]
  (loop [triangles input
	 cnt 0]
    (if (nil? triangles)
      cnt
      (recur (rest triangles)
	     (if (in-triangle?
		  (first (first triangles))
		  (first (rest (first triangles)))
		  (first (rest (rest (first triangles))))
		  '(0 0))
	       (inc cnt)
	       cnt)))))
))
      
  