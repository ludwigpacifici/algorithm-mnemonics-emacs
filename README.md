# algorithm-mnemonics-emacs #

> Algorithm Mnemonics: Increase Productivity with STL Algorithms.

Original idea from [Tommy Bennett](https://github.com/tommybennett). It's a port of [algorithm-mnemonics](https://github.com/tommybennett/algorithm-mnemonics) for GNU Emacs. It converts the algorithm mnemonic XML for [YASnippet](https://github.com/joaotavora/yasnippet).

## Usage of the Clojure script ##

Build it with Leiningen:

    lein uberjar

Create the snippets:

    $ java -jar <path_to_algorithm-mnemonics-emacs-X.Y.Z-standalone.jar> -p <path_to_write_snippets>

## Installation ##

### Via Yasnippet ###

It should be [soon available](https://github.com/AndreaCrotti/yasnippet-snippets/pull/163) by default in YASnippet.

### Manual ###

Add to your emacs configuration file:

    (add-to-list 'yas-snippet-dirs "<path_to_snippets>")

Enable YASnippet:

<kbd>M-x</kbd> `yas-minor-mode`

If necessary, relaod snippets:

<kbd>M-x</kbd> `yas-reload-all`

## Bugs ##

Please use [GitHub issues](https://github.com/ludwigpacifici/algorithm-mnemonics-emacs/issues).

## CppCon 2016 ##

[Tommy Bennett - “Algorithm Mnemonics: Increase your Productivity with STL Algorithms"](https://www.youtube.com/watch?v=tSq7yDwS1vM)
