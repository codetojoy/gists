
import React from 'react'

let Profile = props => (
    <div>
        <h3>{props.name}</h3>
        <p>
            {props.age} years old, {props.bio}
        </p>
        <h3>Hobbies</h3>
        <ul>
            {props.hobbies.map(hobby => <li>{hobby}</li>)}
        </ul>
    </div>
)

export default Profile;

/*
import React from 'react'

export default class Profile extends React.Component {
    render() {
        let hobbies = this.props.hobbies.map(hobby => {
            return <li>{hobby}</li>
        });
        return (
            <div>
                <h3>{this.props.name}</h3>
                <p>
                    {this.props.age} years old, {this.props.bio}
                </p>
                <h3>Hobbies</h3>
                <ul>
                    {hobbies}
                </ul>
            </div>
        )
    }
}
*/
