; EXECUTION_SCAFFOLD: load unchanged canonical code, observe/check, then shut down executors.
(def result (load-file "src/clojure/slide_39_transient_vector.clj"))
(try
(assert (= (vec (range 64)) persistent-vector))
  (finally (shutdown-agents)))
