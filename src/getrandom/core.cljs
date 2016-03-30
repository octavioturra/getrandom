(ns getrandom.core)

(def alphanum ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"])
(def alphanum-len (count alphanum))

(defn getrandom-number
  ([](rand-int (.-MAX_SAFE_INTEGER js/Number)))
  ([size](#(rand-int size))))

(defn getrandom-string
  ([generator] (str (generator)))
  ([generator size] (clojure.string/join (take size (repeatedly #(get alphanum (generator alphanum-len)))))))

(def consonants ["b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z", "nh", "ch"])
(def consonants-len (count consonants))
(def vocals ["a", "e", "i", "o", "u", "y", "ae", "io", "ao", "oa", "ie"])
(def vocals-len (count vocals))

(defn getrandom-syllable
  ([generator] (+ (get consonants (generator consonants-len)) (get vocals (generator vocals-len))))
  ([generator size] (clojure.string/join (repeatedly size #(getrandom-syllable generator)))))
