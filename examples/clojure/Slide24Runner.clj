; EXECUTION_SCAFFOLD: load unchanged canonical code, observe/check, then shut down executors.
(def result (load-file "src/clojure/slide_24_square_seq.clj"))
(try
(assert (= (list 1 4 9 16 25) (take 5 (square-seq 1))))
  (finally (shutdown-agents)))
