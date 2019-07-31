const request = require('request')

const get = (path, id) => new Promise((resolve, reject) => {
  const start = new Date()

  request({
    url: `http://consumer:3001/listen?${path}=${id}`,
    method: 'GET',
    qs: {  }
  }, (err, data) => {
    if(err) {
      return reject(err)
    }

    const end = new Date() - start
    console.log(`[producer] sent ${path} id: ${id} - ${data.body} time: ${end / 1000}`)
    return resolve(data)
  })
})

const doEvents = async() => {
  const start1 = new Date()

  const proms = []

  for(let i = 0; i < 10; i += 5) {
    proms.push(get('short', i))
    proms.push(get('short', i + 1))
    proms.push(get('short', i + 2))
    proms.push(get('short', i + 3))
    proms.push(get('long', i + 4))
  }

  return Promise.all(proms)
    .then(() => {
      const end1 = new Date() - start1
      console.log(`[producer] total: ${end1 / 1000}`)
    })
    .catch((e) => {
      console.error(`[producer] error: ${e}`)
    })
}


(async () => {
  while(true) {
    console.log(`[producer] initializing events...`)
    await doEvents()
    console.log(`[producer] finished events, next events in 10 seconds...`)
    await new Promise((resolve) => { setTimeout(() => resolve(), 5000)})
  }
})()
