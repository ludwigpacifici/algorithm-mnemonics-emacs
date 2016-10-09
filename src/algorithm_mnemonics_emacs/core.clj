(ns algorithm-mnemonics-emacs.core
  (:require [clojure.xml :as xml]
            [clojure.string :as str]
            [clojure.tools.cli :as cli])
  (:gen-class))

(def cli-options
  [["-p" "--path PATH" "Path to write the snippets"
    :default "./"]
   ["-f" "--file FILE" "XML file to read. Can be a local file or an URL."
    :default "https://raw.githubusercontent.com/tommybennett/algorithm-mnemonics/master/algorithm_mnemonics.xml"]
   ["-h" "--help"]])

(defn- parse [s]
  (xml/parse
   (java.io.ByteArrayInputStream. (.getBytes s))))

(defn- snippet-name [mnemonic]
  "Name of the mnemonic"
  (get-in mnemonic [:attrs :n]))

(defn- snippet-code [mnemonic]
  "C++ code of the mnemonic"
  (get-in mnemonic [:content 0 :content 0]))

(defn- snippet-write [filename snippet directory]
  "Write the snippet <str> in a file <filename>"
  (let [path (str directory filename)]
    (println "Writting snippet in " path)
    (spit path snippet)))

(defn- snippet-data [name code]
  (str "# -*- mode: snippet -*-\n"
       "# name: " name "\n"
       "# key: " name "\n"
       "# contributor: Tommy BENNETT and Ludwig PACIFICI <ludwig@lud.cc>\n"
       "# --"
       code
       "$0\n"))

(defn- sanitize [code]
  (-> code
      (str/replace "\t" "")
      (str/replace #"(\(|\{|\[) +" "$1")
      (str/replace #" +(\)|\}|\])" "$1")))

(defn- convert-placeholders [code]
  (-> code
      (str/replace-first "%\\m C%" "${1:container}")
      (str/replace "%\\m C%" "$1")
      (str/replace-first "%\\c" "$2")
      (str/replace-first "%\\c" "$3")
      (str/replace-first "%\\c" "$4")
      (str/replace-first "%\\c" "$5")
      (str/replace-first "%\\c" "$6")
      (str/replace-first "%\\c" "$7")
      (str/replace-first "%\\c" "$8")
      (str/replace-first "%\\c" "$9")
      (str/replace-first "%\\c" "$10")))

(defn- snippet [mnemonic directory]
  (let [name (snippet-name mnemonic)
        code (-> (snippet-code mnemonic)
                 sanitize
                 convert-placeholders)]
    (snippet-write name (snippet-data name code) directory)))

(defn- do-it [path file]
  (let [raw-mnemonics (parse (slurp file))
        mnemonics (get-in raw-mnemonics [:content])
        directory (-> (str path "/")
                      str/trim
                      (str/replace "//" "/"))]
    (.mkdir (java.io.File. directory))
    (doseq [m mnemonics] (snippet m directory))
    (println (count mnemonics) "generated snippets")))

(defn -main
  "Convert the algorithm mnemonic XML file to YASnippet files"
  [& args]
  (let [options (cli/parse-opts args cli-options)
        help (get-in options [:options :help])
        file (get-in options [:options :file])
        path (get-in options [:options :path])]
    (if help
      (println (:summary options))
      (do-it path file))))
