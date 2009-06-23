(println 
 (apply #'+ (filter #(or (zero? (rem % 5)) (zero? (rem % 3))) (range 1000)))
)