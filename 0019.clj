(defn leap-year? [year]
  (if (zero? (rem year 4))
    (if (zero? (rem year 100))
      (if (zero? (rem year 400))
	true
	false)
      true)
    false))

(defn days-in-month [year month]
  (if (= month 2)
    (if (leap-year? year)
      29
      28)
    (if (or (= month 4) (= month 6) (= month 9) (= month 11))
      30
      31)))

(defn problem-nineteen []
  (loop [year 1901 month 1 week-day 2 sum 0] ; two signifies tuesday, zero indexed from sunday
    (if (> year 2000)
      sum
      (if (zero? week-day)
	(recur (if (= month 12) (inc year) year) 
	       (if (= month 12) 1 (inc month))
	       (rem (+ week-day (days-in-month year month)) 7)
	       (inc sum))
	(recur (if (= month 12) (inc year) year) 
	       (if (= month 12) 1 (inc month))
	       (rem (+ week-day (days-in-month year month)) 7)
	       sum)))))

(def solutions (list problem-nineteen))