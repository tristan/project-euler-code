(def solutions (list (fn []

(let [ones (map count '("one" "two" "three" "four" "five" "six" "seven" "eight" "nine"))
      teens (map count '("ten" "eleven" "twelve" "thirteen" "fourteen" "fifteen" "sixteen" "seventeen" "eighteen" "nineteen"))
      tens (map count '("twenty" "thirty" "forty" "fifty" "sixty" "seventy" "eighty" "ninety"))
      to99 (+
            (apply + ones)
            (apply + teens)
            (apply + (map (fn [t] (apply + (cons t (map (fn [o] (+ t o))
                                               ones)))) tens)))
      hundreds (map #(+ (count "hundred") %) ones)
      soln (+ to99
              (apply + hundreds)
              (apply + (map (fn [h]
                              (+ to99 (* 99
                                         (+ h 3)))) hundreds)))
      soln (+ soln (count "onethousand"))
      ]
  soln)

)))