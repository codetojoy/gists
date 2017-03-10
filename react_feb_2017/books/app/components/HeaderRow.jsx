
import React from 'react'

export default class HeaderRow extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const headings = this.props.headers.map( (h, index)  => {
            let result = null;

            if (this.props.sortby === index) {
                result = ( <th onClick={this.props.sort}>{h} {this.props.descending ? ' \u2191' : ' \u2193'}</th> );
            } else {
                result = ( <th onClick={this.props.sort}>{h}</th> );
            }

            return result;
        });

        return (<thead><tr>{headings}</tr></thead>);
    }
}

