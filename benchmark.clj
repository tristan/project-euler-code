
(load (first *command-line-args*))
(println "==== CLOJURE ====")
(doseq [soln solutions]
  (println (time (soln))))
