(ns consumer.server
  (:require [consumer.client :as client])
  (:import (java.net InetSocketAddress))
  (:import (com.sun.net.httpserver HttpServer HttpHandler HttpExchange)))

(defn send-http-response [httpExchange id]
  (.sendResponseHeaders httpExchange 200 (count id))
  (let [os (.getResponseBody httpExchange)]
    (.write os (.getBytes id))
    (.close os)))

(defn create-send-callback [httpExchange id]
  #(send-http-response httpExchange id))

(defn my-handler [request-handler]
  (proxy [HttpHandler] []
    (handle [httpExchange]
      (let [query (.getQuery (.getRequestURI httpExchange))
            [type id] (clojure.string/split query #"=")]
        (request-handler id type (create-send-callback httpExchange id))))))
 
(defn create-server [request-handler]
  (let [socket (new InetSocketAddress (int 3002))
        server (HttpServer/create socket 0)]
    (doto server
      (.createContext "/listen" (my-handler request-handler))
      (.setExecutor nil)
      (.start))))
