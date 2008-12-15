(load-file "sieveoferatosthenes.clj")

(defn problem-ten [limit]
  (loop [primes (vector 2 3 5 7) lim (+ limit 100)]
    (if (< (last primes) limit)
      (recur (expand-vector-of-primes primes lim) (+ lim 100))
      (apply #'+ (take-while (fn [x] (< x limit)) primes)))))

(println (problem-ten 2000000))