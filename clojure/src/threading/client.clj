(ns threading.client
  (:import (org.apache.http.impl.client HttpClients))
  (:import (org.apache.http.client.methods HttpGet)))

(def url "http://localhost:3000/")

(defn request [id]
  (let [client (HttpClients/createDefault)
        method (new HttpGet (str url "/" id))
        response (.execute client method)
        status (.getStatusCode (.getStatusLine response))]
    (println (str "finished " id))
    id))
