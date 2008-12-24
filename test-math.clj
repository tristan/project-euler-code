(load-file "math.clj")

;(println (list-divisors 16) " = (1 2 4 8)")
;(println (list-divisors 4) " = (1 2)")

(defn test-recuring-sequence-fn []
  (loop [ctr 1000000]
    (if (> ctr 1000000)
      (println "done")
      (do
	(get-recuring-sequence (concat (range ctr) (range ctr)))
	(recur (inc ctr))))))

(defn test-long-div []
  (loop [ctr 1]
    (if (> ctr 100)
      nil
      (do
	(println "1 /" ctr "=" (longdiv 1 ctr))
	(recur (inc ctr))))))

;(defn test-long-div []
;  (println (longdiv 1 84)))

;(test-long-div)


(defn test-int-to-bin []
  (println (integer-to-binary 585))
  (println (integer-to-binary 580)))

(test-int-to-bin)