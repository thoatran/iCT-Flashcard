// Load language file
var getText = require('../error_msg_vi.json');
var connection = require('../models/database');

var ImageModel = {};
ImageModel.saveImage = function(user_id, image, cbSuccess, cbFail) {
    // Save image in image list of user
    connection.query(`INSERT INTO images
    (user_id, image) VALUES (?, ?);
    `, [user_id, image], function (error) {

        if (error) {
            cbFail();
        } else {
            cbSuccess();
        }

    });
};

module.exports = ImageModel;