// Import language service
var Language = require('../services/language');

// Load required packages
var DictionaryModel = require('../models/dictionary');

var DictionaryController = {};

// Load language file
var getText = require('../languages/vi.json');

/// ### E-V Dictionary
DictionaryController.lookup = function(req, res) {
    const getText = Language.getText(req);

    let lookUpWord = req.query['lookup'];
    let result = {};
  
    if (lookUpWord === undefined) {
      result['success'] = false;
      result['message'] = getText['18404'];
      res.setHeader('Content-Type', 'application/json');
      res.send(JSON.stringify(result));
    } else {
  
      // Decode lookup word
      lookUpWord = decodeURIComponent(lookUpWord);

      DictionaryModel.lookup(lookUpWord, function(result) {
        res.json(result);
      });

    }  

}


module.exports = DictionaryController;