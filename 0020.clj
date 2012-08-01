(def solutions (list (fn []
                       (apply + (map #(- (int %) (int \0)) (str (reduce *' (range 1 101))))))))