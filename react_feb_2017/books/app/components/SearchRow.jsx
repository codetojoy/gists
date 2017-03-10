
import React from 'react'

export default class SearchRow extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        let searchRow = null;

        if (this.props.searchEnabled) {
            const searchFields = this.props.headers.map((heading, index) => {
                return (
                    <td><input type="text" data-idx={index} onChange={this.props.search}/></td>
                );
            });
            searchRow = <tr>{searchFields}</tr>;
        }

        return searchRow;
    }
}
