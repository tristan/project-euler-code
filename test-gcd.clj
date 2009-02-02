(load-file "primes.clj")

(println (math/gcd 12 18) "= 6")
(println (math/gcd -4 14) "= 2")
(println (math/gcd 5 0) "= 5")
(println (math/gcd 9 28) "= 1")
(println (math/gcd 42 56) "= 14")

(def sieve (primes/sieve 100000))
(println (math/phi 30))