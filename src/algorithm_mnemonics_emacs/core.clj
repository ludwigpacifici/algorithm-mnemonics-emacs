(ns algorithm-mnemonics-emacs.core
  (:require [clojure.xml :as xml]
            [clojure.string :as str])
  (:gen-class))

(def url "https://raw.githubusercontent.com/tommybennett/algorithm-mnemonics/master/algorithm_mnemonics.xml")

(def directory "c++-algorithm/")

(defn -parse [s]
  (xml/parse
   (java.io.ByteArrayInputStream. (.getBytes s))))

(defn- snippet-name [mnemonic]
  "Name of the mnemonic"
  (get-in mnemonic [:attrs :n]))

(defn- snippet-code [mnemonic]
  "C++ code of the mnemonic"
  (get-in mnemonic [:content 0 :content 0]))

(defn- snippet-write [filename snippet]
  "Write the snippet <str> in a file <filenamed>"
  (spit (str directory filename) snippet))

(defn- snippet-data [name code]
  (str "# -*- mode: snippet -*-\n"
       "# name: " name "\n"
       "# key: " name "\n"
       "# --"
       code))

(defn- snippet [mnemonic]
  (let [name (snippet-name mnemonic)
        code (str/replace (snippet-code mnemonic) "\t" "")]
    (snippet-write name (snippet-data name code))))

(defn -main
  "Convert the algorithm mnemonic XML file to YASnippet files"
  [& args]
  (let [mnemonics (parse (slurp url))]
    (.mkdir (java.io.File. "c++-algorithm"))
    (map snippet (get-in mnemonics [:content]))))
