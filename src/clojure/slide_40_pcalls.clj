(defn wait-with-for [limit]
  (loop [ctr 0]
    (Thread/sleep 500)
    (println "Ctr=" ctr)
    (if (< ctr limit)
      (recur (inc ctr))
      ctr)))

(def wait-seq (pcalls #(wait-with-for 1) #(wait-with-for 2) #(wait-with-for 3)))
(println (first wait-seq))
