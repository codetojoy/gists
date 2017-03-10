
import React from 'react'

export default class TableRow extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        const cells = this.props.row.map((cell, index) => { 
            let result = null;
            let edit = this.props.edit;

            if (edit && (edit.row == this.props.rowIndex) && (edit.cell == index)) {
                result = (
                    <form onSubmit={this.props.save}>
                        <input type="text" defaultValue={cell} />
                    </form>
                );
            } else {
                result = (<td data-row={this.props.rowIndex}>{cell}</td>)
            }

            return result;
         });

        return (
            <tr>{cells}</tr>
        );
    }
}
