; EXECUTION_SCAFFOLD: load unchanged canonical code, observe/check, then shut down executors.
(def result (load-file "src/clojure/slide_42_agent.clj"))
(try
(await str-coll)
(assert (= ["foo"] @str-coll))
  (finally (shutdown-agents)))
