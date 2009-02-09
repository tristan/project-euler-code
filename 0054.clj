

(defn card-to-numeric [card]
  (let [c (first card) s (last card)]
    (list 
     (if (and (> (int c) 48) (< (int c) 58))
       (- (int c) 48)
       (if (= c \T)
	 10
	 (if (= c \J)
	   11
	   (if (= c \Q)
	     12
	     (if (= c \K)
	       13
	       14)))))
     (if (= s \C)
       1
       (if (= s \S)
	 2
	 (if (= s \D)
	   3
	   4))))))

(defn get-pairs [cards]
  (if (< (count cards) 2)
    '()
    (if (= (first cards) (first (rest cards)))
      (if (= (first cards) (first (rest (rest cards)))) ; ignore three of a kinds
	(if (= (first cards) (first (rest (rest (rest cards))))) ; ignore four of a kinds
	  (recur (rest (rest (rest (rest cards)))))
	  (recur (rest (rest (rest cards)))))
	(cons (first cards) (get-pairs (rest (rest cards)))))
      (recur (rest cards)))))

(defn get-three-of-a-kind [cards]
  (if (< (count cards) 3)
    0
    (if (= (first cards) (first (rest cards)) (first (rest (rest cards))))
      (if (= (first cards) (first (rest (rest (rest cards))))) ; ignore four of a kinds
	(recur (rest (rest (rest (rest cards)))))
	(first cards)) ; only possible to have 1 three of a kind
      (recur (rest cards)))))

(defn get-four-of-a-kind [cards]
  (if (< (count cards) 4)
    0
    (if (= (first cards) (first (rest cards)) (first (rest (rest cards))) (first (rest (rest (rest cards)))))
      (first cards) ; only possible to have 1 four of a kind
      (recur (rest cards)))))

(defn straight? [cards]
  (if (nil? (rest cards))
    true
    (if (not (= (first cards) (dec (first (rest cards)))))
      (if (and (= (first (rest cards)) 14) (= (first cards) 5))
	true
	false)
      (recur (rest cards)))))

(defn flush? [cards]
  (apply #'= cards))

; one-pair: 1 + highest pair
; two-pair: 20 + highest pair
; three-of-a-kind: 40 + highest pair
; straight: 60 + highest card
; flush: 80 + highest card
; full-house: 100 + highest card
; four-of-a-kind: 120 + hightest pair
; straight-flush: 140 + highest card
; royal-flush: 200

(defn score-hand [cards]
  (let [numbers (sort (map #'first cards)) suits (map #'last cards)]
    (max 
     (let [four (get-four-of-a-kind numbers)]
       (if (not (zero? four))
	 (+ 120 four) ; four of a kind
	 (let [three (get-three-of-a-kind numbers) pairs (get-pairs numbers)]
	   (if (not (zero? three))
	     (if (= 1 (count pairs))
	       (+ 100 three) ; full house
	       (+ 40 three)) ; three of a kind
	     (if (= 2 (count pairs))
	       (+ 20 (apply #'max pairs)) ; two pairs
	       (if (= 1 (count pairs))
		 (+ 1 (first pairs)) ; one pair
		 0))))))
     (if (flush? suits)
       (if (straight? numbers)
	 (if (= (first numbers) 10)
	   200 ; royal flush
	   (+ 140 (apply #'max numbers))) ; straight flush
	 (+ 80 (apply #'max numbers))) ; flush
       (if (straight? numbers)
	 (+ 60 (apply #'max numbers))
	 0)))))

(defn player-one-has-highest-card? [p1-cards p2-cards]
  (if (or (nil? p1-cards) (nil? p2-cards))
    false
    (if (= (first p1-cards) (first p2-cards))
      (recur (rest p1-cards) (rest p2-cards))
      (> (first p1-cards) (first p2-cards)))))

(defn player-one-wins? [p1-cards p2-cards]
  (let [p1-score (score-hand p1-cards) p2-score (score-hand p2-cards)]
    ;(print p1-cards p2-cards p1-score p2-score)
    (if (= p1-score p2-score)
      (player-one-has-highest-card? (reverse (sort (map #'first p1-cards))) (reverse (sort (map #'first p2-cards))))
      (if (> p1-score p2-score)
	true
	false))))

(println (time
(loop [cards (map #'card-to-numeric (re-seq #"[1-9TJQKA][CSDH]" (slurp "poker.txt")))
       p1-wins 0]
  (if (nil? cards)
    p1-wins
    (recur
     (nthrest cards 10)
     (if (player-one-wins? (take 5 cards) (take 5 (nthrest cards 5)))
       (do
	 ;(println "player 1 wins")
	 (inc p1-wins))
       (do
	 ;(println "")
	 p1-wins)))))
))
  
  