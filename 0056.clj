(load-file "math.clj")

(defn problem-fifty-six 
;  ([] (problem-fifty-six 1 1))
;  ([a b]
[]
     (loop [a 1 b 1 maxim 0]
       (if (< 99 a)
	 maxim
	 (recur (if (< 99 b) (inc a) a) 
		(if (< 99 b) 1 (inc b))
		(max maxim
		     (math/sum (math/list-numbers-in (math/loop-pow a b))))
		))))

;  (loop [a 1 b 1 maximum 0]
;    (if (> b 99)
;      (recur (inc a) 1)
;      (if (> a 99)
;	maximum
	

(time (println "max:" (problem-fifty-six)))