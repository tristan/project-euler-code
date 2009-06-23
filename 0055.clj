(load-file "list-lib.clj")

(defn palindromic? [obj]
  (= (take (math/ceil (/ (count obj) 2)) obj) (reverse (drop (- (math/ceil (/ (count obj) 2)) (if (zero? (rem (count obj) 2)) 0 1)) obj))))

(defn lychrel? 
  ([num] (lychrel? num 1))
  ([num itr]
     (if (< 50 itr)
       true
       (let [l (math/list-numbers-in num)]
	 (let [v (+ (list-to-number l) (list-to-number (reverse l)))]
	 (if (palindromic? (math/list-numbers-in v))
	   false
	   (recur v (inc itr))))))))
    
(defn problem-fifty-five []
  (loop [nbr 10 lych 0]
    (if (> nbr 10000)
      lych
      (if (lychrel? nbr)
	(do
	  (println "found lychrel:" nbr)
	  (recur (inc nbr) (inc lych)))
	(recur (inc nbr) lych)))))

(time (println (problem-fifty-five)))