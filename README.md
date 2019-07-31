# clojure-async-test
![demo](https://github.com/plygrynd-jynkyrd/clojure-async/blob/master/docs/demo.gif?raw=true)

# Test
![architecture](https://github.com/plygrynd-jynkyrd/clojure-async/blob/master/docs/diagram.png?raw=true)

- 75% are short messages (0.2 sec) and 25% are long messages (2 sec)
- Running with a single CPU core and 10% of processor ([via docker](https://github.com/plygrynd-jynkyrd/clojure-async/blob/master/docker-compose.yml))

# Result
|messages|async|sync|
|---|---|---|
|5|2.4 sec|3.2 sec|
|10|2.7 sec|6.5 sec|
|50|2.6 sec|28 sec|
|100|3.5 sec|57 sec|
|1000|19 sec|---|

# How to run
- Change messages size and short/long ratio [here](https://github.com/plygrynd-jynkyrd/clojure-async/blob/master/docker-compose.yml)
- Change async/sync execution [here](https://github.com/plygrynd-jynkyrd/clojure-async/blob/master/consumer/src/consumer/core.clj#L25)
- You may also change IO block times for [short](https://github.com/plygrynd-jynkyrd/clojure-async/blob/e9fe33404025c560cf636ca4a6426a8e18005189/client/server.js#L23) and [long](https://github.com/plygrynd-jynkyrd/clojure-async/blob/master/client/server.js#L14) requests
- Run via docker-compose: `docker-compose up`
