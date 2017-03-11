
import "../main.css"

import React, {Component} from 'react'
import RaisedButton from 'material-ui/RaisedButton'
import Dialog from 'material-ui/Dialog'
import {deepOrange500, lightGreenA400 } from 'material-ui/styles/colors'
import FlatButton from 'material-ui/FlatButton'
import getMuiTheme from 'material-ui/styles/getMuiTheme'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'

import MenuItem from 'material-ui/MenuItem'
import Drawer from 'material-ui/Drawer'

const styles = {
  container: {
    textAlign: 'center',
    paddingTop: 200,
  },
};

const muiTheme = getMuiTheme({
  palette: {
    accent1Color: lightGreenA400,
  },
});

export default class App extends React.Component {
  constructor(props, context) {
    super(props, context);

    this.myLogger = this.myLogger.bind(this);
    this.handleRequestClose = this.handleRequestClose.bind(this);
    this.handleTouchTap = this.handleTouchTap.bind(this);
    this.handleToggle = this.handleToggle.bind(this);

    this.state = {
      openDialog: false,
      openDrawer: false
    };
  }

  handleRequestClose() {
    this.setState({
      openDialog: false,
    });
  }

  handleTouchTap() {
    this.setState({
      openDialog: true,
    });
  }

// -------------------- 
    myLogger() {
        console.log("TRACER hello");
    }

    handleToggle() {
        this.setState({
            openDrawer: !this.state.openDrawer
        });
    }
// -------------------- 
 
  render() {
    const standardActions = (
      <FlatButton
        label="Ok"
        primary={true}
        onTouchTap={this.handleRequestClose}
      />
    );

    return (
      <MuiThemeProvider muiTheme={muiTheme}>
        <div style={styles.container}>
          <Dialog
            open={this.state.openDialog}
            title="Super Secret Password"
            actions={standardActions}
            onRequestClose={this.handleRequestClose}
          >
            1-2-3-4-5
          </Dialog>
          <h1>Material-UI</h1>
          <h2>example project</h2>
          <RaisedButton
            label="Test Button"
            secondary={true}
            onTouchTap={this.handleTouchTap}
          />

        <hr />
        <FlatButton label="Default" onTouchTap={this.myLogger} />
        <FlatButton label="Primary" primary={true} />
        <FlatButton label="Secondary" secondary={true} />
        <FlatButton label="Disabled" disabled={true} />
        <hr />
        <RaisedButton
          label="Toggle Drawer"
          onTouchTap={this.handleToggle}
        />
        <Drawer open={this.state.openDrawer}>
          <MenuItem>Menu Item</MenuItem>
          <MenuItem>Menu Item 2</MenuItem>
        </Drawer>
        </div>
      </MuiThemeProvider>
    );
  }
}

