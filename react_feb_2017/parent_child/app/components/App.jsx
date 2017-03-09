
import "../table.css"

import React from 'react'

import Foo from './Foo.jsx'

export default class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            data: this.props.initialData
        };

        this.appConcat = this.appConcat.bind(this);
    }

    getInitialState() {
        return {
            data: ''
        };
    }

    appConcat() {
        this.setState({
            data: (this.state.data + ' app')
        });
    }

    render() {
        console.log("TRACER app render");
        return (
            <div>
                <h4>Main App</h4>
                <hr />
                <Foo abc={this.state.data} appConcat={this.appConcat} />
            </div>
        );
    }
}
