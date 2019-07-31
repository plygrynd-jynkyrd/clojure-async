const express = require('express')
const app = express()


const blockEvent = (seconds) => new Promise((resolve) => {
  setTimeout(() => {
    resolve()
  }, seconds * 1000)
})

app.get('/long', ({ query }, res) => {
  console.log(`[http-client] long request #${query.id}`)

  blockEvent(2).then(() => {
    console.log(`[http-client] long response #${query.id}`)
    res.sendStatus(200)
  })
})

app.get('/short', ({ query }, res) => {
  console.log(`[http-client] short request #${query.id}`)

  blockEvent(0.2).then(() => {
    console.log(`[http-client] short response #${query.id}`)
    res.sendStatus(200)
  })
})

app.listen('3000', () => {
  console.log('[http-client] listening at :3000')
})