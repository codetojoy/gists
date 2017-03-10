
import React from 'react'

let Toolbar = props => (
    <div className='toolbar'>
        <button onClick={props.toggleSearch} >Search</button>
        <a onClick={props.exportJSON} href='data.json' >Export JSON</a>
        <a onClick={props.exportCSV} href='data.csv' >Export CSV</a>
    </div>
)

export default Toolbar;

