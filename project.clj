(defproject server "0.1.0-SNAPSHOT"
  :description "Interactive Datalog Tutorial"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [ring/ring-core "1.8.1"]
                 [org.clojure/clojurescript "1.10.339"]
                 [ring/ring-jetty-adapter "1.8.1"]
                 [datomic-query-helpers "0.1.1"]
                 [fipp "0.6.23"]
                 [hiccup "1.0.5"]
                 [compojure "1.6.2"]
                 [markdown-clj "1.10.5"]
                 [com.taoensso/timbre "5.1.0"]
                 [com.datomic/datomic-free "0.9.5697" :exclusions [com.google.guava/guava]]
                 [hylla "0.2.0"]
                 [hiccups "0.3.0"]
                 [domina "1.0.3"]]

  :plugins [[lein-ring "0.12.5"]
            [cider/cider-nrepl "0.25.3"]
            [lein-cljsbuild "1.1.4"]]
  :ring {:handler learndatalogtoday.handler/app}
  :main learndatalogtoday.handler
  :uberjar-name "learndatalog-standalone.jar"
  :source-paths ["src/clj"]
  :jvm-opts ["-XX:+IgnoreUnrecognizedVMOptions" "--add-modules java.xml.bind"]
  :min-lein-version "2.0.0"
  :aot [learndatalogtoday.handler]
  :repl-options {:init-ns server.core}
  :cljsbuild {:builds [{:source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/app.js"
                                   :optimizations :advanced
                                   :externs ["externs.js"]
                                   :static-fns true}}]})
