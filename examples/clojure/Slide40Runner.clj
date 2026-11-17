; EXECUTION_SCAFFOLD: load unchanged canonical code, observe/check, then shut down executors.
(def result (load-file "src/clojure/slide_40_pcalls.clj"))
(try
(let [values (doall wait-seq)]
  (prn values)
  (assert (= (list 1 2 3) values)))
  (finally (shutdown-agents)))
