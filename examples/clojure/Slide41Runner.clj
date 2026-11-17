; EXECUTION_SCAFFOLD: load unchanged canonical code, observe/check, then shut down executors.
(def result (load-file "src/clojure/slide_41_stm_ref.clj"))
(try
(assert (= 4000 (:bal @my-acc)))
  (finally (shutdown-agents)))
