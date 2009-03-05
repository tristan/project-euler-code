(ns libs.list-utils)

(defn list-contains? [lst n]
  (if (empty? lst)
    false
    (if (= (first lst) n)
      true
      (recur (rest lst) n))))

(defn list-to-number [lst]
  (loop [sum 0 lst lst]
    (if (empty? lst)
      sum
      (recur (+ (* 10 sum) (first lst)) (rest lst)))))

(defn number-to-list 
  ([nbr] (number-to-list nbr 1))
  ([nbr tenth]
     (if (= nbr (rem nbr tenth))
       '()
       (concat (number-to-list nbr (* tenth 10)) (list (rem (/ (- nbr (rem nbr tenth)) tenth) 10))))))
