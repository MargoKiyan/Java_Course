(def persistent-vector
  (persistent!
    (reduce conj! (transient []) (range 64))))

(println persistent-vector)
