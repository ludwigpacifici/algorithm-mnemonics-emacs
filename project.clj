(defproject algorithm-mnemonics-emacs "0.1.0"
  :description "Algorithm Mnemonics: Increase Productivity with STL Algorithms."
  :url "https://github.com/ludwigpacifici/algorithm-mnemonics-emacs"
  :license {:name "GNU General Public License Version 3, 29 June 2007"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.cli "0.3.5"]]
  :main ^:skip-aot algorithm-mnemonics-emacs.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
