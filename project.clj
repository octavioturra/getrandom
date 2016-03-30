(defproject get-random "0.1.7-SNAPSHOT"
  :description "Project with multiple random tools"
  :url "https://github.com/octavioturra/getrandom"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.145"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]]

  :plugins [[lein-cljsbuild "1.0.5"]
            [lein-doo "0.1.7-SNAPSHOT"]]

  :source-paths ["src" "test"]

  :clean-targets ^{:protect false} [:target-path "resources/public/js/" "out"]

  :doo {:build "test"
        :paths {:slimer "./node_modules/.bin/slimerjs"}
        :alias {:browsers [:chrome :firefox]
                :all [:browsers :headless]}}

  :jvm-opts ["-Xmx1g"]

  :cljsbuild
  {:builds {:dev                {:source-paths ["src"]
                                 :compiler     {:output-to     "resources/public/js/dev.js"
                                                :main          getrandom.core
                                                :optimizations :none}}
            :test               {:source-paths ["src" "test"]
                                 :compiler     {:output-to     "out/testable.js"
                                                :main          'getrandom.runner
                                                :optimizations :simple}}
            :advanced           {:source-paths ["src" "test"]
                                 :compiler     {:output-to     "out/testable.js"
                                                :main          "getrandom.runner"
                                                :optimizations :advanced}}
            :none-test          {:source-paths ["src" "test"]
                                 :compiler     {:output-to     "out/testable.js"
                                                :main          getrandom.runner
                                                :source-map    true
                                                :optimizations :none}}
            :node-none          {:source-paths ["src" "test"]
                                 :compiler     {:output-to     "out/testable.js"
                                                :main          getrandom.runner
                                                :optimizations :none
                                                :target        :nodejs}}
            :node-advanced      {:source-paths ["src" "test"]
                                 :compiler     {:output-to     "out/testable.js"
                                                :main          getrandom.runner
                                                :optimizations :advanced
                                                :target        :nodejs}}
            }
   })
