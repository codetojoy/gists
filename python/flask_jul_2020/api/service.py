import flask
app = flask.Flask(__name__)

@app.route('/')
def index():
    return 'TRACER: index'

@app.route('/ping', methods=['GET','POST'])
def ping():
    if flask.request.method == 'POST':
        return f'TRACER: ping {flask.request.method} {flask.request.json}'
    else:
        return f'TRACER: ping {flask.request.method}'
