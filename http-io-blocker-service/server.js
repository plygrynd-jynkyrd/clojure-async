const express = require('express')
const app = express()


const blockEvent = (seconds) => new Promise((resolve) => {
  setTimeout(() => {
    resolve()
  }, seconds * 1000)
})

app.get('/long', (req, res) => {
  console.log(`[http-blocker] long request`)

  blockEvent(2).then(() => {
    console.log(`[http-blocker] long response`)
    res.sendStatus(200)
  })
})

app.get('/short', (req, res) => {
  console.log(`[http-blocker] short request`)

  blockEvent(0.2).then(() => {
    console.log(`[http-blocker] short response`)
    res.sendStatus(200)
  })
})

app.listen('3000', () => {
  console.log('[http-blocker] listening at :3000')
})