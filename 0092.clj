(load-file "math.clj")
(load-file "list-lib.clj")

(defn next-in-chain [n]
  (math/sum (map #(* % %) (math/list-numbers-in n))))

(defn find-loop-in-chain [c]
  (let [nxt (next-in-chain (first c))]
       (if (in? c nxt)
	   (do
	       ;(println (last c) "->" nxt)
	       nxt)
	 (recur (cons nxt c)))))

(defn has-eighty-nine? [n]
  (if (= n 89)
      true
    (if (= n 1)
	false
      (recur (next-in-chain n)))))

; TODO: very slow, optimise
(defn solver [n count]
  (if (> n 9999999)
      count
    (if (has-eighty-nine? n)
	(recur (inc n) (inc count))
      (recur (inc n) count))))

(defn problem-ninety-two []
  (solver 1 0))