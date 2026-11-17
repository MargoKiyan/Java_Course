(require '[clojure.test.check :as tc]
'[clojure.test.check.generators :as gen]
'[clojure.test.check.properties :as prop])
(defn bid-price [prices] (apply max prices))
(def bigger-than-minimum
(prop/for-all [prices (gen/not-empty (gen/list gen/nat))]
(<= (apply min prices) (bid-price prices))))
(tc/quick-check 100 bigger-than-minimum)
