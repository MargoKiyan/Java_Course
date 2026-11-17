(defn wait-and-log [coll value]
  (Thread/sleep 1000)
  (conj coll value))
(def str-coll (agent []))
(send-off str-coll wait-and-log "foo")
(await str-coll)
(println @str-coll)
