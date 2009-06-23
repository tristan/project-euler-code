(defn lastrest [lst]
  (reverse (rest (reverse lst))))

(defn get-while-n-plus-1-gt-n-helper [numbers]
  (if (or (nil? numbers) (nil? (rest numbers)) (> (first numbers) (first (rest numbers))))
    (list (first numbers) (rest numbers)) ; return the list with 
    (cons (first numbers) (get-while-n-plus-1-gt-n-helper (rest numbers)))))

(defn get-while-n-plus-1-gt-n [numbers]
  (let [result (get-while-n-plus-1-gt-n-helper numbers)]
    (list (lastrest result) (last result))))

(defn push-into-second-last-element [lst nbr]
  (concat (lastrest lst) (list nbr) (list (last lst))))

(defn get-next-permutation [numbers] ; numbers must be reversed. i.e. 9876543210 not 0123456789
  (let [maxnbr (apply max numbers)] ; get the maximum number in the list
  (let [split (get-while-n-plus-1-gt-n numbers)] ; step 1: build a list of all the numbers for which n+1 > n
    (let [lhs (first split) rhs (last split)] ; lhs is list of all number which n+1 > n, rhs is the remaining numbers
      (println numbers lhs rhs)
      (if (nil? rhs)
	nil ; there is no next permutation!
	(if (= (last lhs) maxnbr)
	  (if (= (dec (last lhs)) (first rhs))
	    (concat (reverse (sort (lastrest (push-into-second-last-element lhs (first rhs))))) (cons (last lhs) (rest rhs)))
	    (concat (push-into-second-last-element (reverse (sort lhs)) (first rhs)) (rest rhs)))
	  (concat (sort (push-into-second-last-element lhs (first rhs))) (rest rhs))
	  ))))))

(defn list-lexicographic-permutations [numbers]
  (let [nxt (reverse (get-next-permutation (reverse numbers)))]
    (if (nil? nxt)
      (list numbers)
      (cons numbers (list-lexicographic-permutations nxt)))))



(defn test []
  ;(println (get-while-n-plus-1-gt-n '(9 8 7 6 5 4 3 2 1 0)))
  ;(println (get-while-n-plus-1-gt-n '(7 8 9 6 5 4 3 2 1 0)))
  ;(println (get-while-n-plus-1-gt-n '(8 7 9 6 5 4 3 2 1 0)))
  ;(println (get-while-n-plus-1-gt-n '(1 2 3 4 5 6 7 8 9 0)))
  ;(println (get-while-n-plus-1-gt-n '(0 1 2 3 4 5 6 7 8 9)))
  ;(println (get-next-permutation '(9 8 7 6 5 4 3 2 1 0)))
  ;(println (get-next-permutation '(2 1 0)))

  (println (list-lexicographic-permutations '(0 1 2)))
  (println (list-lexicographic-permutations '(0 1 2 3)))
)

(test)