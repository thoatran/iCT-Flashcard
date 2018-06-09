var Language = {}
Language.vi = require('../languages/vi.json');
Language.en = require('../languages/en.json');

Language.getText = function(req) {
    if (req.conf_language == "en") {
        return Language.en;
    } else {
        return Language.vi;
    }
}

module.exports = Language;