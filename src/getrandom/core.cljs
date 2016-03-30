(ns getrandom.core)

(defn getrandom
  ([](rand-int Number/MAX_SAFE_INTEGER))
  ([size](mod (rand-int) size)))

(defn getrandom-string [generator]
  (str (generator)))

; (def consonants '("b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z", "nh", "ch"))
; (def vocals '("a", "e", "i", "o", "u", "y", "ae", "io", "ao", "oa", "ie"))
;
; (defn getsilable []
;   ())
