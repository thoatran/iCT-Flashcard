// Load required packages
const FlashcardModel = require('../models/flashcard');
const CollectionModel = require('../models/collection');
const UserModel = require('../models/user');

var FlashcardController = {};

// Load language file
var getText = require('../error_msg_vi.json');


FlashcardController.createFlashcard = function(req, res) {
    if (!req.body.hasOwnProperty("username") ||
    !req.body.hasOwnProperty("token") || 
    !req.body.hasOwnProperty("collection_id")
    ) {
        return res.json({"success": false,
        "message": getText[18412]
        });
    }

    // Check user login
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {
        
        // Check collection existance
        CollectionModel.getCollectionInfo(req.body.collection_id, function(collectionInfo) {

            // Success

            // Check permission
            if (collectionInfo["user_id" ] != userInfo["user_id"]) {
                return res.json({"success": false,
                    "message": getText[18403]
                });
            }

            let word = req.body.word || '';
            let image = req.body.image || '';
            let pronunciation = req.body.pronunciation || '';
            let meaning = req.body.meaning || '';
            let order = req.body.order || -1; // -1 is unordered
            let remember_score = 0;


            FlashcardModel.createFlashcard(word, pronunciation, meaning, image, order, remember_score, collectionInfo["id"], function() {
                // Success
                res.json({'success': true});
            }, function() {
                // Fail
                res.json({'success': false});
            });

        }, function() {

            // Fail
            return res.json({"success": false,
                "message": getText[18411]
            });
        });

        
    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}

FlashcardController.updateFlashcard = function(req, res) {
    if (!req.body.hasOwnProperty("username") ||
    !req.body.hasOwnProperty("token") || 
    !req.body.hasOwnProperty("flashcard_id")
    ) {
        return res.json({"success": false,
        "message": getText[18412]
        });
    }

    // Check user login
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {

        // Check permission
        FlashcardModel.havePermission(username, flashcard_id, function() {
            // Success
            // Get old info
            FlashcardModel.getFlashcardInfo(flashcard_id, function(flashcardInfo) {
                // Success

                // Modify flashcard info
                if (typeof req.body.image != 'undefined') {
                    flashcardInfo.image = req.body.image;
                }
                if (typeof req.body.word != 'undefined') {
                    flashcardInfo.word = req.body.word;
                }
                if (typeof req.body.pronunciation != 'undefined') {
                    flashcardInfo.pronunciation = req.body.pronunciation;
                }
                if (typeof req.body.meaning != 'undefined') {
                    flashcardInfo.meaning = req.body.meaning;
                }
                if (typeof req.body.order != 'undefined') {
                    flashcardInfo.order = req.body.order;
                }
                if (typeof req.body.remember_score != 'undefined') {
                    flashcardInfo.remember_score = req.body.remember_score;
                }
                
                // Update info
                FlashcardModel.updateFlashcardInfo(flashcardInfo, function() {
                    return res.json({"success": true});
                }, function() {
                    return res.json({"success": false});
                });

            }, function() {
                // Fail
                return res.json({"success": false});
            });

        }, function() {
            // Fail
            return res.json({"success": false,
            "message": getText[18403]
            });
        });

    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}

FlashcardController.getFlashcard = function(req, res) {
    if (!req.body.hasOwnProperty("username") ||
    !req.body.hasOwnProperty("token") || 
    !req.body.hasOwnProperty("flashcard_id")
    ) {
        return res.json({"success": false,
        "message": getText[18412]
        });
    }

    // Check user login
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {

        // Check permission
        FlashcardModel.havePermission(username, flashcard_id, function() {
            // Success
            // Get info
            FlashcardModel.getFlashcardInfo(flashcard_id, function(flashcardInfo) {
                return res.json({"success": true,
                    'data': flashcardInfo
                });
            }, function() {
                return res.json({"success": false});
            });
           
        }, function() {
            // Fail
            return res.json({"success": false,
            "message": getText[18403]
            });
        });

    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}

FlashcardController.deleteFlashcard = function(req, res) {
    if (!req.body.hasOwnProperty("username") ||
    !req.body.hasOwnProperty("token") || 
    !req.body.hasOwnProperty("flashcard_id")
    ) {
        return res.json({"success": false,
        "message": getText[18412]
        });
    }

    let flashcard_id = req.body.flashcard_id;
    // Check user login
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {

        // Check permission
        FlashcardModel.havePermission(username, flashcard_id, function() {
            // Success
            // Delete flashcard
            FlashcardModel.deleteFlashcard(flashcard_id, function() {
                return res.json({"success": true});
            }, function() {
                return res.json({"success": false});
            });
            
        }, function() {
            // Fail
            return res.json({"success": false,
            "message": getText[18403]
            });
        });

    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}

module.exports = FlashcardController;