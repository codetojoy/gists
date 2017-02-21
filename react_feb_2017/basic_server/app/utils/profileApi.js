
// Note: Tomcat is simply serving up a JSON file
// and required this - http://stackoverflow.com/a/18850438/12704
const ENDPOINT = 'http://localhost:5150/groovlet/profiles.json';

export function getProfiles() {

    return fetch(ENDPOINT)
        .then(res => res.json())
        .catch(err => {
            console.log("TRACER error: " + err);
        });
}
