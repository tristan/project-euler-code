(load-file "math.clj")

(defn C [n r]
  (/ (! n) (* (! r) (! (- n r)))))


; r > 1/2n = reverse of r < 1/2n

(defn problem-fifty-three-dec []
(time (println "result:"
(loop [n 1 r 0 totalcnt 0 cnt 0]
  (if (> n 100)
    totalcnt
    (if (<= (C n r) 1000000)
      (recur (inc n)
	     (floor (/ (inc n) 2))
	     (+ totalcnt
		(if (zero? cnt)
		  0
		  (if (zero? (rem n 2))
		    (dec (* 2 cnt)) ; if even, times by two and minus one
		    (* 2 cnt)))) ; if odd, just times by two
	     0)
      (recur n (dec r) totalcnt (inc cnt)))))
)))

(defn problem-fifty-three-inc []
(time (println "result:"
(loop [n 1 r 1 totalcnt 0 cnt 0]
  (if (> n 100)
    totalcnt
    (let [ncr (C n r)]
      (if (>= ncr 1000000)
	(recur (inc n)
	     1
	     (let [s (- (floor (/ n 2)) cnt)]
	       (+ totalcnt
		  (if (zero? (rem n 2))
		    (dec (* 2 s)) ; if even, times by two and minus one
		    (* 2 s)))) ; if odd, just times by two
	     0)
	(if (<= (floor (/ n 2)) r)
	  (recur (inc n)
		 1
		 totalcnt
		 0)
	  (recur n (inc r) totalcnt (inc cnt)))))))
)))

(problem-fifty-three-dec)
(problem-fifty-three-inc)