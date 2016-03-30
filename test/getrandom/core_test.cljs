(ns getrandom.core-test
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [cljs.test :refer-macros [async deftest is testing]]
            [cljs.core.async :as async :refer [chan put! <!]]
            [getrandom.core :as core]))

(defn log-n-ret [d] (print "log: " d) d)

(deftest getrandom-number-returns-number
  (is (number? (core/getrandom-number))))

(deftest getrandom-number-returns-different-numbers
  (is (not= (core/getrandom-number) (core/getrandom-number))))

(deftest getrandom-number-returns-a-limited-range
  (is (< (reduce max (take 99 (repeatedly #(core/getrandom-number 5)))) 5)))

(deftest getrandom-string-returns-a-string
  (is (string? (core/getrandom-string core/getrandom-number))))

(deftest getrandom-string-returns-a-limited-size-string
  (is (== (.-length (core/getrandom-string core/getrandom-number 5)) 5)))

(deftest getrandom-string-returns-n-sized-string
  (is (reduce #(and %1 %2)
              (map-indexed #(== (.-length (core/getrandom-string core/getrandom-number %1)) %1)
                           (make-array 99)))))

(deftest getrandom-string-returns-an-alphanumeric-string
  (is (let [s (core/getrandom-string core/getrandom-number 99)]
        (and
          (re-find #"\w" s)
          (re-find #"\d" s)))))

(deftest getrandom-syllable-returns-a-consonant-and-a-vocal
  (is (re-find #"([bcdefghjklmnopqrstvwxyz][nh]?[aeiouy])" (log-n-ret (core/getrandom-syllable core/getrandom-number)))))

(deftest getrandom-syllable-returns-n-count-syllables
  (is (re-find #"([bcdefghjklmnopqrstvwxyz][nh]?[aeiouy]{1,2}){3}" (log-n-ret (core/getrandom-syllable core/getrandom-number 3)))))
