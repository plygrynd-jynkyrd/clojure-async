(ns consumer.core
  (:require [consumer.server :as server])
  (:require [consumer.client :as client]))

(defn consumer-request-handler-async
  "send each consumer request to the client"
  [id type callback]
  (future
    (let [result (client/request id type)]
      (callback)
      (println (str "[consumer] " type " message #" id " sent: " result)))))

(defn consumer-request-handler
  "send each consumer request to the client"
  [id type callback]
  (let [result (client/request id type)]
    (callback)
    (println (str "[consumer] " type " message #" id " sent: " result))))
    
(defn -main
  "receive multiple http requests from consumer"
  [& args]
  (do
    (println (str "[consumer] avaiable processors: " (.availableProcessors (java.lang.Runtime/getRuntime))))
    (server/create-server consumer-request-handler-async)
    ;(server/create-server consumer-request-handler)
    (println "[consumer] server listening at :3001")))
  

