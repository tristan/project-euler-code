(load-file "list-lib.clj")

; TOO SLOW
(println (count (filter (fn [x] (and-list (map prime? (take (count (str x)) (map list-to-number (iterate cycle-list (map char-to-number (seq (str x))))))))) (range 1 1000000))))