(load-file "math.clj")

(defn count-down ([total] (count-down total '(200 100 50 20 10 5 2 1)))
  ([total denominations]
     (loop [x (range total -1 (* -1 (first denominations))) cnt 0]
       (if (nil? x)
	 cnt
	 (if (nil? (rest (rest denominations)))
	   (+ cnt (count x))
	   (recur (rest x)
		  (+ cnt (count-down (first x) (rest denominations)))))))))

(defn slow-solution []
  (println (count-down 5 (range 4 0 -1)))
  (println (count-down 100 (range 99 0 -1))))

; see item 11 on: http://mathworld.wolfram.com/PartitionFunctionP.html
(defn euler-generating-function 
  ([n] 
     (println "N:" n)
     (if (= n 0)
       1
       (if (< n 0)
	 0
	 (euler-generating-function n 1 0))))
  ([n k r]
     (if (> k n)
       r
       (recur n (inc k)
	      (+
	       (* (math/fast-pow -1 (inc k))
		  (+ (euler-generating-function (- n (* (/ 1 2) k (dec (* 3 k)))))
		     (euler-generating-function (- n (* (/ 1 2) k (inc (* 3 k)))))))
	       r)))))

;(def euler-generating-function (memoize euler-generating-function))

;(println (euler-generating-function 1))
;(println (euler-generating-function 2))
;(println (euler-generating-function 3))
;(println (euler-generating-function 4))
;(println (euler-generating-function 5))
;(println (euler-generating-function 6))
;(println (euler-generating-function 7))
(println (euler-generating-function 100))