(load-file "math.clj")

(defn in? [lst n]
  (if (nil? lst)
    false
    (if (= (first lst) n)
      true
      (in? (rest lst) n))))

(defn index-of-max 
  ([lst] (index-of-max (rest lst) (first lst) 1 1))
  ([lst max max-index ctr]
     (if (nil? lst)
       max-index
       (let [newmax (< max (first lst))]
	 (index-of-max (rest lst) (if newmax (first lst) max) (if newmax ctr max-index) (inc ctr))))))

(defn get-recuring-sequence 
  [numbers] 
  (if (or (< (count numbers) 2) (not (zero? (rem (count numbers) 2))))
    nil
    (loop [nums1 numbers nums2 (rest numbers)]
      ;(println nums1 nums2)
      (if (nil? nums2)
	(if (nil? (rest nums1))
	  nil
	  (recur (rest nums1) (rest (rest nums1))))
	(if (= (first nums1) (first nums2))
	  (let [sequ (take-while (fn [x] (not (nil? x))) (map (fn [x y] (if (= x y) x nil)) nums1 nums2))]
	    (if (= (count sequ) (- (count nums1) (count nums2)))
	      (cons sequ (reverse (take (- (- (count numbers) (count nums1)) 1) numbers)))
	      (recur nums1 (rest nums2))))
	  (recur nums1 (rest nums2)))))))
; note: this can be done quicker if we know that (rest numbers) has no recuring 

(defn get-recuring-sequence-if-no-recuring-sequences-in-rest [numbers]
  (if (or (< (count numbers) 2) (not (zero? (rem (count numbers) 2))))
    nil
    (let [a (split-at (/ (count numbers) 2) numbers)]
      (if (= (first a) (last a))
	(first a)
	nil))))

(defn check-for-recuring-sequence [numbers]
  (not (nil? (get-recuring-sequence numbers))))

(defn unique [lst]
  (loop [l lst n (list)]
    (if (nil? l)
      (sort n)
      (if (in? n (first l))
	(recur (rest l) n)
	(recur (rest l) (cons (first l) n))))))

(defn cycle-list [lst] (concat (rest lst) (list (first lst))))

(defn list-to-number [lst]
  (if (nil? lst)
    0
    (+ (* (first lst) (pow 10 (dec (count lst)))) (list-to-number (rest lst)))))

(defn and-list 
  ([lst] (if (nil? lst)
	   false
	   (and-list lst true)))
  ([lst a]
     (if (nil? lst)
       true
       (if (not (first lst))
	 false
	 (and (first lst) (and-list (rest lst) true)))))
)