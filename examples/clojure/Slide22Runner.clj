; EXECUTION_SCAFFOLD: load unchanged canonical code, observe/check, then shut down executors.
(def result (load-file "src/clojure/slide_22_for.clj"))
(try
(prn (doall result))
(assert (= (list 1 4 9) result))
  (finally (shutdown-agents)))
