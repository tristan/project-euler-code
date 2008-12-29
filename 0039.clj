(load-file "math.clj")

; using triplet generation from: "http://en.wikipedia.org/wiki/Pythagorean_triple#V."

(defn factor-pairs [z]
  (let [r (/ (* z z) 2)]
    (let [divisors (cons r (reverse (list-divisors r)))]
      (let [pairs (split-at (/ (count divisors) 2) divisors)]
	(map #(list %1 %2) (first pairs) (reverse (last pairs)))
	))))

(defn build-triplets [z]
  (loop [pairs (factor-pairs z) triplets (list)]
    (if (nil? pairs)
      triplets
      (recur (rest pairs) 
	     (cons (list (+ (first (first pairs)) z)
			 (+ (last (first pairs)) z)
			 (+ (first (first pairs)) (last (first pairs)) z))
		   triplets)))))

(loop [z 2 store (hash-map)]
  (if (not (nil? (get store :done nil)))
    (println "result:" (get store :max))
    (recur (+ 2 z)
	   (let [trips (filter #(> 1000 (apply #'+ %)) (build-triplets z))]
	     (if (nil? trips)
	       (assoc store :done 1)
	       (loop [triplets trips tmpstore store]
		 (if (nil? triplets)
		   tmpstore
		   (recur (rest triplets)
			  (let [sumt (apply #'+ (first triplets))]
			    (let [oldcount (get tmpstore sumt 0) 
				  oldmax (get tmpstore :max 0)]
			      (assoc (assoc tmpstore sumt (inc oldcount))
				:max (if (> (inc oldcount) (get tmpstore oldmax 0))
				       sumt
				       oldmax))))))))))))
