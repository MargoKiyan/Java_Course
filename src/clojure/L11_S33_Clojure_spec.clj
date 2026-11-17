(require '[clojure.spec.alpha :as spec])
(spec/def ::even (spec/and int? even?))
;; Перевірка відповідності числа специфікації even
(spec/conform ::even 10) ;; 10
(spec/conform ::even 13) ;; :clojure.spec.alpha/invalid
