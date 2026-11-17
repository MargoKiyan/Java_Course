(require '[clojure.test :refer [is deftest run-tests]])
(deftest one-is-one
(is (= 1 1))) ;; Перевірка рівності 1 і 1
(run-tests) ;; Запуск тестів
