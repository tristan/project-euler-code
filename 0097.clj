(load-file "math.clj")

(defn drop-pow [nbr pwr digits-to-keep]
  (let [d (math/pow 10 digits-to-keep)]
    (loop [p pwr r 1]
      (if (= p 0)
	r
	(recur (dec p) (rem (* r nbr) d))))))

(time
(let [p (drop-pow 2 7830457 10)]
  (println "last 10 digits of 2**7830457 =" p)
  (println (rem (inc (* 28433 p)) (math/pow 10 10))))
)