; EXECUTION_SCAFFOLD: load unchanged canonical code, observe/check, then shut down executors.
(def result (load-file "src/clojure/slide_38_persistent_versions.clj"))
(try
(assert (= [] (first versions)))
(assert (= (vec (range 32)) (last versions)))
  (finally (shutdown-agents)))
