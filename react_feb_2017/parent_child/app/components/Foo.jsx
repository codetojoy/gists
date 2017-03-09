
import React from 'react'

export default class Foo extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <hr />
                <p>Foo : {this.props.abc}</p>
                <button onClick={this.props.appConcat}>Concat 'app'</button>
                <hr />
            </div>
        );
    }
}
