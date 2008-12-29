(load-file "math.clj")
; hashmap
; on each add, check if len == 3

(defn triangle [n]
  (/ (* n (inc n)) 2))

(defn pentagonal [n]
  (/ (* n (dec (* 3 n))) 2))

(defn hexagonal [n]
  (* n (dec (* 2 n))))



(defn fast-solution []
  (let [res
	(loop [n 2 store (hash-map)]
	  (if (not (nil? (get store :result nil)))
	    (get store :result)
	    (recur (inc n)
		   (let [t (triangle n) p (pentagonal n) h (hexagonal n)]
		     (let [valt (assoc (get store t) :t n)]
		       (if (and (= (count valt) 3) (not (= t 40755)))
			 (do
			   (println "found:" t valt)
			   (assoc store :result valt))
			 (let [storet (assoc store t valt)]
			   (let [valp (assoc (get storet p) :p n)]
			     (if (and (= (count valp) 3) (not (= p 40755)))
			       (do
				 (println "found:" p valp)
				 (assoc storet :result valp))
			       (let [storep (assoc storet p valp)]
				 (let [valh (assoc (get storep h) :h n)]
				   (if (and (= (count valh) 3) (not (= h 40755)))
				     (do
				       (println "found:" h valh)
				       (assoc storep :result valh))
				     (assoc storep h valh)
				     ))))))))))))
	] (println "result:" res)))

(defn very-slow-solution []
  (loop [t (list) p (list) h (list) n 1]
    (let [tn (triangle n) pn (pentagonal n) hn (hexagonal n)]
      (let [lstp (take-while #(>= % tn) p)
	    lsth (take-while #(>= % tn) h)]
					;(println tn (last lstp) (last lsth))
	(if (and (= tn (last lstp) (last lsth)) (not (= tn 40755)))
	  (do 
	    (println tn n (- (count p) (dec (count lstp))) (- (count h) (dec (count lsth))))
	    tn)
	  (recur (cons tn t) (cons pn p) (cons hn h) (inc n)))))))
  
(defn fastest-solution []
  (loop [n 144]
    (let [h (hexagonal n)]
      (let [k (/ (+ 1.0 (sqrt (+ 1.0 (* 24.0 h)))) 6.0)]
	(if (= (floor k) k)
	  (do
	    (println "result:" h)
	    h)
	  (recur (inc n)))))))

(time (fast-solution))
(time (fastest-solution))
;(time (very-slow-solution))