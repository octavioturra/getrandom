(ns getrandom.runner
  (:require [cljs.test :as test]
            [doo.runner :refer-macros [doo-all-tests doo-tests]]
            [getrandom.core-test]))

(doo-tests 'getrandom.core-test)
