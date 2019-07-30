(ns threading.core
  (:require [threading.server :as server]))

(defn -main
  [& args]
  (do
    (server/create-server)
    (println "[clojure] server listening at :3002")))
  

