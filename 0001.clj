(defn problem-one-recursive [current max sum]
      (if (< current max)
	  (if (or (= (rem current 3) 0) (= (rem current 5) 0))
	      (problem-one-recursive (+ current 1) max (+ sum current))
	      (problem-one-recursive (+ current 1) max sum)
	      )
	  (println sum)
	  )
      )
(defn problem-one [max]
  (problem-one-recursive 1 max 0))
(problem-one 10)
(problem-one 1000)