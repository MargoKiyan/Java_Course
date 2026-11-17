(def versions
  (reductions conj [] (range 32)))

(println (first versions))
(println (last versions))
