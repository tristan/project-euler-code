(load "primes")

(defn problem-ten [limit]
  (apply +' (take-while (fn [x] (< x limit)) (hashtable-primes))))

(def solutions (list (fn [] (problem-ten 2000000))))