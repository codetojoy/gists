
import React from 'react'

import Profile from './Profile.jsx'
import AddProfile from './AddProfile.jsx'

import {getProfiles} from '../utils/profileApi'

var MyComponent = props => (
    <p>I am a functional component x: {props.x}</p>
)

export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            profiles: []
        };
        this.addUser = this.addUser.bind(this);
    }

    componentDidMount() {
        getProfiles().then(profiles => {
            this.setState({
                profiles: profiles
            });
        });
    }

    addUser(newProfile) {
        this.setState({
            profiles: this.state.profiles.concat([newProfile])
        })
    }

    render() {
        let profiles = this.state.profiles.map(p => {
            return (
                <Profile name={p.name} age={p.age} bio={p.bio} hobbies={p.hobbies} />
            )
        }); 
        return (
            <div>
                { profiles }
                <AddProfile addUser={this.addUser} />
                <MyComponent x="5150"/>
            </div>
        )
    }
}

