(def my-acc (ref {:name "Ben" :bal 5000}))

(defn safe-debit [acc amount]
  (dosync
    (alter acc update :bal - amount)))

(safe-debit my-acc 1000)
(println @my-acc)
