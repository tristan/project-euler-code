(defn string-to-integer [s]
  (. Integer (parseInt s)))

; NOTE: this is the ugliest but quickets solution so far
(defn find-greatest-product-of-consecutive-digets-in-string [string digets]
  (loop [result 0 iter 0]
    (if (> iter (- (count string) digets))
      result
      (recur (max result (apply #'* (loop [lst (list) point (dec digets)]
				    (if (< point 0)
				      lst
				      (recur (cons (string-to-integer (subs string (+ iter point) (+ iter (inc point)))) lst) (dec point)))))) (inc iter)))))

(def problem-eight-data
  (.replace "73167176531330624919225119674426574742355349194934
96983520312774506326239578318016984801869478851843
85861560789112949495459501737958331952853208805511
12540698747158523863050715693290963295227443043557
66896648950445244523161731856403098711121722383113
62229893423380308135336276614282806444486645238749
30358907296290491560440772390713810515859307960866
70172427121883998797908792274921901699720888093776
65727333001053367881220235421809751254540594752243
52584907711670556013604839586446706324415722155397
53697817977846174064955149290862569321978468622482
83972241375657056057490261407972968652414535100474
82166370484403199890008895243450658541227588666881
16427171479924442928230863465674813919123162824586
17866458359124566529476545682848912883142607690042
24219022671055626321111109370544217506941658960408
07198403850962455444362981230987879927244284909188
84580156166097919133875499200524063689912560717606
05886116467109405077541002256983155200055935729725
71636269561882670428252483600823257530420752963450" "\n" ""))

(defn problem-eight1 []
  (reduce max (map (fn [i] (apply * (map (fn [j] (Character/getNumericValue j)) i))) (partition 5 1 problem-eight-data))))

(defn problem-eight2 [] ; it would seem that (map (map ...)) is a bad computation for speed
  (reduce max (map #(reduce * %) (map (fn [v] (map #(Integer/parseInt (str %)) v)) (partition 5 1 problem-eight-data)))))

(defn problem-eight3 []
  (let [data (map #(Character/getNumericValue %) problem-eight-data)]
    (reduce max (map (fn [i] (apply * i)) (partition 5 1 data)))))

(defn problem-eight4 []
  (let [data (map #(Character/getNumericValue %) problem-eight-data)]
    (reduce max (map (fn [i] (reduce * i)) (partition 5 1 data)))))

(defn problem-eight5 []
  (let [data (map #(Character/getNumericValue %) (seq problem-eight-data))]
    (reduce max (map (fn [i] (reduce * i)) (partition 5 1 data)))))

(defn problem-eight []
  (let [data (map (fn [i] (Character/getNumericValue i)) (seq problem-eight-data))]
    (reduce max (map (fn [i] (reduce * i)) (partition 5 1 data)))))


;(println "fgpocdis" (time (find-greatest-product-of-consecutive-digets-in-string problem-eight-data 5)))
;(println "p81" (time (problem-eight1)))
(println "p82" (time (problem-eight2)))
;(println "p83" (time (problem-eight3)))
;(println "p84" (time (problem-eight4)))
;(println "p8" (time (problem-eight)))
;(println "p85" (time (problem-eight5)))

; everything past p8-2 is on average as fast as each other,
; however the original non-lispy version still wins