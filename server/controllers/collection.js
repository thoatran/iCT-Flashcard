// Load required packages
var CollectionModel = require('../models/collection');
var UserModel = require('../models/user');

var CollectionController = {};

// Load language file
var getText = require('../error_msg_vi.json');


CollectionController.createCollection = function(req, res) {
    if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
        return res.json({"success": false});
    }
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {
        let name = req.body.name || '';
        let description = req.body.description || '';
        let photo = req.body.photo || '';
        CollectionModel.createCollection(name, description, photo, userInfo['user_id'], function(collection_id) {
            res.json({"success": true,
                "collection_id": collection_id
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
    if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
        return res.json({"success": false});
    }
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {
        if (typeof req.body.collection_id === 'undefined') {
            res.json({'success': false});
        }

        CollectionModel.deleteCollection(req.body.collection_id, function() {
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
            collectionInfo.flashcards = CollectionModel.getFlashcards(collection_id);
           
            if (collectionInfo.photo == "") {
                collectionInfo.photo = "assets/images/placeholder333x333.png";
            }
           
            res.json({'success': true,
                'data': collectionInfo
            });
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

module.exports = CollectionController;