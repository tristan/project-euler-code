
(load (first *command-line-args*))
(doseq [soln solutions]
  (println (time (soln))))
