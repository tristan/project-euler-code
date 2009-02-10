(load-file "math.clj")

(defn solve [width height]
  (apply #'+ (map #(apply #'+ %) (map #(map (fn [x] (* % x)) (range 1 (inc width))) (range 1 (inc height))))))

; faster solve, taken from forums
(defn solve [width height]
  (/ (* width (inc width) height (inc height)) 4))

(defn problem-eighty-five []
  (loop [width 3 height 2 cycle 1 c-val 0 c-w 0 c-h 0]
    (if (< width 2)
      (println "soln:" c-w "x" c-h "=" (* c-w c-h))
      (let [sln (solve width height)]
	;(println "trying:" width "x" height "=" sln)
					; if the stored closest is further away then the new solution
	(if (< (math/abs (- 2000000 sln)) (math/abs (- 2000000 c-val)))
	  (do
	    ;(println "new closest:" width "x" height "=" sln)
	    (if (= 1 cycle)
	      (recur (inc width) height cycle sln width height)
	      (recur (dec width) height cycle sln width height)))
	  (if (and (< sln 2000000) (= 1 cycle))
	    (recur (inc width) height cycle c-val c-w c-h)
	    (if (and (> sln 2000000) (= -1 cycle))
	      (recur (dec width) height cycle c-val c-w c-h)
	      (recur width (inc height) -1 c-val c-w c-h))))))))

; cleaner solution
(defn problem-eighty-five []
  (loop [width
	 (loop [width 3]
	   (if (> (solve width 2) 2000000)
	     width
	     (recur (inc width))))
	 height 2
	 c-val 0 ; closest value
	 c-w 0 ; width of closest value
	 c-h 0 ; height of closest value
	 ]
    (if (> 2 width)
      (* c-w c-h)
      (let [sln (solve width height)]
	(if (< (math/abs (- 2000000 sln)) (math/abs (- 2000000 c-val)))
	  (recur (dec width) height sln width height)
	  (if (> sln 2000000)
	    (recur (dec width) height c-val c-w c-h)
	    (recur width (inc height) c-val c-w c-h)))))))


(println 
(time
(problem-eighty-five)
))


; TODO: optimise
; cycle width up until first value greater than 2000000 is found, then
; increase the height and cycle the width down until the first value
; lower than 2000000, repeat till