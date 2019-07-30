(ns threading.server
  (:require [threading.client :as client])
  (:import (java.net InetSocketAddress))
  (:import (com.sun.net.httpserver HttpServer HttpHandler HttpExchange)))

(defn future-done [httpExchange id]
  (println id)
  (.sendResponseHeaders httpExchange 200 0)
  (let [os (.getResponseBody httpExchange)]
    (.write os (.getBytes ""))
    (.close os)
    (println (str "finished id: " id))))

(def my-handler (proxy [HttpHandler] []
                    (handle [httpExchange]
                      (let [id (clojure.string/replace (.getQuery (.getRequestURI httpExchange)) "=" "")
                            oi (println id)
                            request (future-done httpExchange (client/request id))]
                        (println (str "received id: " id))))))



(defn create-server []
  (let [socket (new InetSocketAddress (int 3002))
        server (HttpServer/create socket 0)]
    (doto server
      (.createContext "/ei" my-handler)
      (.setExecutor nil)
      (.start))))
