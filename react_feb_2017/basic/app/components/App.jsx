
import React from 'react'

import Profile from './Profile.jsx'
import AddProfile from './AddProfile.jsx'

var MyComponent = props => (
    <p>I am a functional component x: {props.x}</p>
)

export default class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            profiles: [ 
                {name: "Dave", age: 43, bio: 'D bio', hobbies: ['guitar','finance']}, 
                {name: "Colleen", age: 44, bio: 'C bio', hobbies: ['reading','baseball']}
            ]
        };
        this.addUser = this.addUser.bind(this);
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

