//import { format } from 'path';
const path = require("path");

// Dictionary server
var logger = require('morgan');
var http = require('http');
var bodyParser = require('body-parser');
var express = require('express');
var cors = require('cors');
var router = express();
var app = express();
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended: false
}));

app.use(cors());

var server = http.createServer(app);
var request = require("request");
const evDict = require('./dict_data.json');


app.get('/dictionary/ev', (req, res) => {
  let lookUpWord = req.param('lookup');
  let result = {};

  if (lookUpWord === undefined) {
    result['success'] = false;
    result['message'] = "Please specify a word to lookup.";
  } else {

    // Decode lookup word
    lookUpWord = decodeURIComponent(lookUpWord);

    // Look up in database
    let found = false;
    for (let i = 0; i < evDict.length; i++) {
      if (evDict[i].word.toLocaleLowerCase() === lookUpWord.toLocaleLowerCase()) {
        result['success'] = true;
        result['word'] = lookUpWord;
        result['ipapron'] = evDict[i].ipapron;
        result['meaning'] = evDict[i].meaning;

        res.setHeader('Content-Type', 'application/json');
        res.send(JSON.stringify(result));
      
        res.json(result);
        found = true;
        break;
      }
    }

    // If not found a word
    if (!found) {
      result['success'] = false;
      result['word'] = lookUpWord;
      result['message'] = "Not found.";
      res.setHeader('Content-Type', 'application/json');
      res.send(JSON.stringify(result));
    }

  }

});


app.set('port', process.env.PORT || 8080);

server.listen(app.get('port'), function() {
  console.log("Dictionary server listening at port %d ", app.get('port'));
});