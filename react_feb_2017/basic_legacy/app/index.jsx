
var React = require('react');
var ReactDOM = require('react-dom');

var App = React.createClass({
    render: function() {
        return (
            <p>BOOYAH, World! I can't believe it worked.</p>
        )
    }
});

ReactDOM.render(<App />, document.getElementById('app'));
