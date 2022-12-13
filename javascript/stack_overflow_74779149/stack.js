
const R = require('ramda');

const options = [{value: 1, name: 'test'}, {value: 2, name: 'test 1'}]
const inputValue =  {value: 1, name: 'test'}

const option = R.find(R.propEq('name',inputValue['name']))(options)

console.log(`TRACER option: ${JSON.stringify(option)}`)

// ------ main

console.log(`TRACER Ready.`)

