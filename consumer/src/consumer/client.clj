(ns consumer.client
  (:import (org.apache.http.impl.client HttpClients))
  (:import (org.apache.http.client.methods HttpGet)))

(def url "http://client:3002")

(defn request [id type]
  (try
    (let [client (HttpClients/createDefault)
          method (new HttpGet (str url "/" type "?id=" id))
          response (.execute client method)
          status (.getStatusCode (.getStatusLine response))]
      status)
    (catch Exception e
      (println "[consumer] ERROR! wait for the client..."))))
