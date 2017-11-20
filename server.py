from flask import Flask, request, jsonify
from flask_cors import CORS, cross_origin
import requests
import json

app = Flask(__name__)
app.config['JSONIFY_PRETTYPRINT_REGULAR'] = True
CORS(app)

@app.route('/')
def get_all():
    response = requests.post('http://localhost:3030/ds/query',
       data={'query': 'SELECT * { ?s ?p ?o . }'})
    return jsonify(response.json())

@app.route('/search')
def search():
    query = request.args.get('query')
    response = requests.post('http://localhost:3030/ds/query',
       data={'query': query})
    return jsonify(response.json())

if __name__ == '__main__':
    app.run(host='0.0.0.0')