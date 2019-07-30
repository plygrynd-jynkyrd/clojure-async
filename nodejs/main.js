const request = require('request')

const get = (path) => new Promise((resolve) => {
  const start = new Date()

  request({
    url: `http://localhost:3002/ei?${path}`,
    method: 'GET',
    qs: {  }
  }, (err, body, data) => {

    const end = new Date() - start
    console.log(`[events-generator] sent ${path} time: ${end / 1000}`)
    resolve(data)
  })
})

const start1 = new Date()

const proms = []

for(let i = 0; i < 1000; i += 5) {
  proms.push(get('short'))
  proms.push(get('short'))
  proms.push(get('short'))
  proms.push(get('short'))
  proms.push(get('short'))
}

Promise.all(proms)
  .then(() => {
    const end1 = new Date() - start1
    console.log(`[events-generator] total: ${end1 / 1000}`)

  })

