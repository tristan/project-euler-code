
(defn using-group-by [x]
  (let [x (group-by identity (apply str x))]
    (not
     (or (contains? x \0) (not (= 9 (count x)))
         (some (fn [x] (> x 1)) (map (fn [x] (-> x second count)) x))))))

(println
 (time
  (reduce + (distinct
             (for [i (range 2 4987)
                   j (range i (/ 9999 i))
                   :when (= "123456789" (apply str (sort (str i j (* i j)))))
                   ]
               (* i j))))))