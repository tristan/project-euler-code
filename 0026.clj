(load-file "math.clj") ; loads list

(defn problem-twenty-six []
  (println (inc (index-of-max (map (fn [x] (last (longdiv 1 x))) (range 1 1000))))))

(problem-twenty-six)