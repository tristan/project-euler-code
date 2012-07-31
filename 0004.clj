

(defn palindromic? [obj]
  ; if length is not even then false straight away
  (if (not (= (rem (count obj) 2) 0))
    false
    (let [splt (split-at (/ (count obj) 2) obj)]
      (= (first splt) (reverse (last splt))))))

(defn unit-test-palindromic? []
  (if (= (palindromic? "abccba") true)
    (println "passed test 1")
    (println "failed test 1"))
  (if (= (palindromic? (list 1 2 3 4 5 6)) false)
    (println "passed test 2")
    (println "failed test 2"))
  (if (= (palindromic? (vector 4 3 2 2 3 4)) true)
    (println "passed test 3")
    (println "failed test 3"))
  (if (= (palindromic? "123") false)
    (println "passed test 4")
    (println "failed test 4"))
  (if (= (palindromic? '(4 5 6 6 5 4)) true)
    (println "passed test 5")
    (println "failed test 5")))

;(unit-test-palindromic?)

(defn problem-four1 []
  (loop [x 100 y 100 largest 0]
    (if (> x 999)
      largest
      (if (> y x)
	(recur (inc x) 100 largest)
	(let [num (* x y)]
	  (if (palindromic? (print-str num))
	    (recur x (inc y) (max num largest))
	    (recur x (inc y) largest)))))))

(defn problem-four2 []
  (loop [x 999 y 999 largest 0]
    (if (< y 100)
      (recur (dec x) 999 largest)
      (let [num (* x y)]
	(if (< num largest) ; if the product is less than the largest found palindrome
	  (if (< x y) ; and x is less than y
	    largest ; then we have found the largest possible palindrome
	    (recur (dec x) 999 largest)) ; otherwise decrement x and reset y
	  (if (palindromic? (print-str num))
	    (recur x (dec y) (max num largest))
	    (recur x (dec y) largest)))))))

(def solutions (list problem-four1 problem-four2))