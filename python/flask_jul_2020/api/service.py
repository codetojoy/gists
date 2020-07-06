import collections
import flask
app = flask.Flask(__name__)

import mock_storage as ms

@app.route('/api/v1/ping', methods=['GET','POST'])
def ping():
    if flask.request.method == 'POST':
        return f'TRACER: ping {flask.request.method} {flask.request.json}'
    else:
        return f'TRACER: ping {flask.request.method}'

@app.route('/api/v1/composers', methods=['GET'])
def get_composers():
    composers = ms.get_composers()
    return flask.jsonify(composers=composers)

@app.route('/api/v1/composer', methods=['POST'])
def new_composer():
    name = flask.request.json.get("name", "")
    era = flask.request.json.get("era", "")
    composer = ms.new_composer(name, era)
    result = flask.jsonify(Composer=composer)
    return result

@app.route('/api/v1/composer/<int:id>', methods=['GET', 'PUT', 'DELETE'])
def handle_composer_by_id(id):
    result = ""
    if flask.request.method == "GET":
        composer = ms.get_composer_by_id(id)
        result = flask.jsonify(Composer=composer)
    elif flask.request.method == "PUT":
        name = flask.request.json.get("name", "")
        era = flask.request.json.get("era", "")
        composer = ms.update_composer(id, name, era)
        result = flask.jsonify(Composer=composer)
    elif flask.request.method == "DELETE":
        ms.delete_composer(id)
    return result

if __name__ == '__main__':
    app.debug = True
    app.run(host='0.0.0.0', port=4140)
