(defn square-seq [n]
  (lazy-seq
    (cons (*' n n)
          (square-seq (inc' n)))))

(println (doall (take 5 (square-seq 1))))
