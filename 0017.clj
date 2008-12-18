(load-file "math.clj")

(defn how-many-hundreds? [n]
  (if (< 999 n)
    (how-many-hundreds? (rem n 1000))
    (floor (/ n 100))))

(defn how-many-tens? [n]
  (if (< 99 n)
    (how-many-tens? (rem n 100))
    (floor (/ n 10))))

(defn how-many-teens? [n]
  (if (< 99 n)
    (how-many-teens? (rem n 100))
    (if (< 19 n)
      nil
      n)))

(defn how-many-ones? [n]
  (if (< 9 n)
    (how-many-ones? (rem n 10))
    n))


; "one thousand" = 11
; "one" = 3
; "two" = 3
; "three" = 5
; "four" = 4
; "five" = 5
; "six" = 3
; "seven" = 5
; "eight" = 5
; "nine" = 4
; "ten" = 3
; "eleven" = 6
; "twelve" = 6
; "thirteen" = 8
; "fourteen" = 8
; "fifteen" = 7
; "sixteen" = 7
; "eighteen" = 8
; "nineteen" = 8
; "twenty" = 6
; "thirty" = 6
; "fourty" = 6
; "fifty" = 5
; "sixty" = 5
; "seventy" = 7
; "eighty" = 6
; "ninety" = 6
; "hundred" = 7
; "and" = 3

; 1000 one thousand
; 999 nine hundred and ninety nine

(def number-to-word-map 
     {1 "one"
      2 "two"
      3 "three"
      4 "four"
      5 "five"
      6 "six"
      7 "seven"
      8 "eight"
      9 "nine"
      10 "ten"
      11 "eleven"
      12 "twelve"
      13 "thirteen"
      14 "fourteen"
      15 "fifteen"
      16 "sixteen"
      17 "seventeen"
      18 "eighteen"
      19 "nineteen"
      20 "twenty"
      30 "thirty"
      40 "forty"
      50 "fifty"
      60 "sixty"
      70 "seventy"
      80 "eighty"
      90 "ninety"
})

(defn wordify 
  ([lst]
     (if (< (count lst) 3) ; if we only have a list of 2 or less
       (wordify lst false)
       (if (zero? (first lst))
	 (wordify (rest lst) false)
	 (cons (get number-to-word-map (first lst)) (cons "hundred" (wordify (rest lst) true))))))
  ([lst has-hundreds?]
     (let [res 
	   (if (zero? (first lst))
	     (if (zero? (last lst))
	       nil
	       (list (get number-to-word-map (last lst))))
	     (if (zero? (last lst))
	       (list (get number-to-word-map (* 10 (first lst))))
	       (list (get number-to-word-map (* 10 (first lst))) (get number-to-word-map (last lst)))))]
       (if (nil? res)
	 (list)
	 (if has-hundreds?
	   (cons "and" res)
	   res)))))
	 

(defn parameterise [n]
  (let [hundreds (how-many-hundreds? n)]
    (let [tens (how-many-tens? n)]
      (let [ones (if (= tens 1)
		   (how-many-teens? n)
		   (how-many-ones? n))]
	(list hundreds (if (= tens 1) 0 tens) ones)))))

(defn sum-characters-in-list [lst]
  (if (zero? (count lst))
    0
    (+ (count (first lst)) (sum-characters-in-list (rest lst)))))

;(println (sum-characters-in-list (wordify (parameterise 999))))
;(println (wordify (parameterise 417)))
;(println (wordify (parameterise 44)))
;(println (wordify (parameterise 1)))
;(println (wordify (parameterise 10)))
;(println (wordify (parameterise 404)))

(defn problem-seventeen [n]
  (if (< n 1)
    0
    (+ (sum-characters-in-list (wordify (parameterise n))) (problem-seventeen (dec n)))))

(defn test []
  (loop [n 1]
    (if (< 999 n)
      nil
      (do
	(println (wordify (parameterise n)))
	(recur (inc n))))))

(test)

(println (+ (sum-characters-in-list (list "one" "thousand")) (problem-seventeen 999)))
;(println (problem-seventeen 5))
