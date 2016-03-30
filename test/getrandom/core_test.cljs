(ns getrandom.core-test
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.test :refer-macros [async deftest is testing]]
            [cljs.core.async :as async :refer [chan put! <!]]
            [getrandom.core :as core]))

(deftest random-is-number
  (is (== (js/isNaN (core/getrandom)) false)))

(deftest two-different-random
  (is (not= (core/getrandom) (core/getrandom))))

(deftest max-size-random
  (is (== (reduce max (map (core/getrandom 5) (make-array 99))) 5)))

(deftest random-string
  (is (str? (core/getrandom-string core/getrandom))))
; (deftest adding
;   (is (= 2 (core/adder 1 1))))
;
; (deftest async-test
;   (async done
;     (let [a 1]
;       (js/setTimeout (fn []
;                        (is (= 1 a))
;                        (done))
;         100)
;       (is (= 1 a)))))
;
; (deftest csp-test
;   (async done
;     (let [val 1
;           ch (chan)]
;       (go (let [a (<! ch)]
;             (is (= a val))
;             (done)))
;       (put! ch val))))
