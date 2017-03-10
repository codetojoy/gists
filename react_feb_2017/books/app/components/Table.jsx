
import React from 'react'

import HeaderRow from './HeaderRow.jsx'
import SearchRow from './SearchRow.jsx'
import TableRow from './TableRow.jsx'

export default class Table extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const headerRow = (
            <HeaderRow headers={this.props.headers}
                         sortby={this.props.sortby}
                         sort={this.props.sort}
                         descending={this.props.descending} />
        );
        let dataRows = this.props.data.map((row, rowIndex)  => 
            (
                <TableRow row={row}
                          rowIndex={rowIndex}
                          edit={this.props.edit}
                          save={this.props.save} />
            )
        );

        return (
            <table>
                {headerRow}
                <tbody onDoubleClick={this.props.showEditor}>
                    <SearchRow searchEnabled={this.props.searchEnabled} 
                            search={this.props.search} 
                            headers={this.props.headers} />
                    {dataRows}
                </tbody>
            </table>
        );
    }
}

