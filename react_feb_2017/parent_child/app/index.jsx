
import React from 'react'
import ReactDOM from 'react-dom'

import App from './components/App.jsx'

const data = 'INIT';

ReactDOM.render(<App initialData={data} />, document.getElementById('app'));
