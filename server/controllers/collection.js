// Import language service
var Language = require('../services/language');

// Load required packages
var CollectionModel = require('../models/collection');
var FlashcardModel = require('../models/flashcard');
var UserModel = require('../models/user');

var CollectionController = {};

// Load language file
var getText = require('../languages/vi.json');


CollectionController.createCollection = function(req, res) {
    const getText = Language.getText(req);

    if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
        return res.json({"success": false});
    }
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {
        let name = req.body.name || getText[18413];
        let description = req.body.description || getText[18414];
        let photo = req.body.photo || getText[18415];
        CollectionModel.createCollection(name, description, photo, userInfo['user_id'], function(collection_id) {

            // Create a default word
            FlashcardModel.createFlashcard(getText[18416], getText[18417], getText[18418], getText[18415], 0, 0, collection_id, function() {
                res.json({"success": true,
                    "collection_id": collection_id
                });
            }, function() {
                res.json({"success": false});
            });
            
            
        }, function() {
            res.json({"success": false});
        });
    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}

CollectionController.deleteCollection = function(req, res) {
    const getText = Language.getText(req);

    if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
        return res.json({"success": false});
    }
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {
        if (typeof req.body.collection_id === 'undefined') {
            res.json({'success': false});
        }

        return CollectionModel.deleteCollection(req.body.collection_id, function() {
            // Success
            res.json({'success': true});
        }, function() {
            // Fail
            res.json({'success': false});
        }); 

    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}

CollectionController.updateCollection = function(req, res) {
    const getText = Language.getText(req);

    if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
        return res.json({"success": false});
    }
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {
        if (typeof req.body.collection_id === 'undefined') {
            res.json({'success': false});
        }

        let collection_id = req.body.collection_id;
        CollectionModel.getCollectionInfo(collection_id, function(collectionInfo) {
            // Success
            // Does user own the collection?
            if (collectionInfo.user_id != userInfo['user_id']) {
                res.json({'success': false});
            }

            // Modify collection info
            if (typeof req.body.name != 'undefined') {
                collectionInfo.name = req.body.name;
            }
            if (typeof req.body.description != 'undefined') {
                collectionInfo.description = req.body.description;
            } 
            if (typeof req.body.photo != 'undefined') {
                collectionInfo.photo = req.body.photo;
            }
            if (typeof req.body.remember_score != 'undefined') {
                collectionInfo.remember_score = req.body.remember_score;
            } 
            

            // Update in database
            let result = CollectionModel.updateCollection(collectionInfo, function() {
                // Success
                res.json({'success': true});
            }, function() {
                // Fail
                res.json({'success': false});
            });

        }, function(){
            // Fail
            res.json({'success': false});
        });

    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}

CollectionController.getAllCollection = function(req, res) {
    const getText = Language.getText(req);

    if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
        return res.json({"success": false});
    }
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {

        // Get data
        CollectionModel.getAllCollection(userInfo["user_id"], function(data) {
            res.json({'success': true,
                "data": data
            });
        }, function(){
            // Fail
            res.json({'success': false});
        });

    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}


CollectionController.getCollection = function(req, res) {
    const getText = Language.getText(req);

    if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
        return res.json({"success": false});
    }
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {
        if (typeof req.body.collection_id === 'undefined') {
            return res.json({'success': false});
        }

        let collection_id = req.body.collection_id;
        return CollectionModel.getCollectionInfo(collection_id, function(collectionInfo) {
            // Success
            
            if (collectionInfo.photo == "") {
                collectionInfo.photo = "assets/images/placeholder333x333.png";
            }
           
            return res.json({'success': true,
                'data': collectionInfo
            });
        }, function() {
            // Fail
            return res.json({'success': false});
        });


    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}

module.exports = CollectionController;