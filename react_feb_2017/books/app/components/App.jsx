
import "../table.css"

import React from 'react'

import Table from './Table.jsx'
import Toolbar from './Toolbar.jsx'

import {searchData, sortData} from '../utils/utils'

export default class App extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            headers: this.props.headers,
            data: this.props.initialData,
            preSearchData: this.props.initialData
        };

        this.exportCSV = this.exportCSV.bind(this);
        this.exportJSON = this.exportJSON.bind(this);

        this.save = this.save.bind(this);
        this.search = this.search.bind(this);
        this.showEditor = this.showEditor.bind(this);
        this.sort = this.sort.bind(this);

        this.toggleSearch = this.toggleSearch.bind(this);
    }

    getInitialState() {
        return {
            preSearchData: [],
            data: [], 
            headers: [],
            sortby: null,
            descending: false,
            edit: null,
            searchEnabled: false
        };
    }

    toggleSearch() {
        this.setState({
            searchEnabled: (! this.state.searchEnabled),
            data: this.state.preSearchData 
        });
    }

    sort(e) {
        const column = e.target.cellIndex;
        const useDescending = (this.state.sortby === column) && (! this.state.descending);

        const sortedData = sortData(this.state.data,
                                    column,
                                    this.state.sortby,
                                    useDescending);

        this.setState({
            data: sortedData,
            sortby: column,
            descending: useDescending
        });
    }

    showEditor(e) {
        this.setState({
            edit: {
                row: parseInt(e.target.dataset.row, 10),
                cell: e.target.cellIndex
            }
        });
    }

    save(e) {
        e.preventDefault();
        let input = e.target.firstChild;
        let data = this.state.data.slice();
        data[this.state.edit.row][this.state.edit.cell] = input.value;
        this.setState({
            edit: null,
            data: data,
        });
    }

    exportJSON(ev) {
        const format = 'json';
        const contents = JSON.stringify(this.state.data);
        let URL = window.URL || window.webkitURL;  
        const blob = new Blob([contents], {type: 'text/' + format});  
        ev.target.href = URL.createObjectURL(blob);  
        ev.target.download = 'data.' + format; 
    }

    exportCSV(e) {
        // see book
    }

    search(e) {
        const target = e.target.value;

        if (!target) {
            this.setState({
                data: this.state.preSearchData
            });
        } else {
            const index = e.target.dataset.idx;
            const filteredData = searchData(target, this.state.preSearchData, index); 

            this.setState({
                data: filteredData
            });
        }
    }

    render() {
        return (
            <div>
                <Toolbar toggleSearch={this.toggleSearch}
                         exportJSON={this.exportJSON}
                         exportCSV={this.exportCSV} />

                <hr />
                <Table data={this.state.data}
                       headers={this.state.headers}
                       sortby={this.state.sortby}
                       edit={this.state.edit}
                       searchEnabled={this.state.searchEnabled}
                       save={this.save}
                       search={this.search}
                       showEditor={this.showEditor}
                       sort={this.sort}
                />
            </div>
        );
    }
}
