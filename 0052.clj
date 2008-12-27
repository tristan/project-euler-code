(load-file "math.clj")

(defn list-numbers-in 
  ([nbr] (list-numbers-in nbr 1))
  ([nbr tenth]
     (if (= nbr (rem nbr tenth))
       nil
       (cons (rem (/ (- nbr (rem nbr tenth)) tenth) 10) (list-numbers-in nbr (* tenth 10))))))

(println 
 (loop [x 1]
   (if (apply #'= (map (comp sort list-numbers-in) (map #(* x %) (range 2 7))))
     x
     (recur (inc x)))))


     