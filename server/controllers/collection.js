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
        let result = CollectionModel.createCollection(name, description, photo, userInfo['user_id']); 
        if (result) {
            res.json({'success': true});
        } else {
            res.json({'success': false});
        }
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

        let result = CollectionModel.deleteCollection(req.body.collection_id); 
        if (result) {
            res.json({'success': true});
        } else {
            res.json({'success': false});
        }
    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}

CollectionController.editCollection = function(req, res) {

    if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
        return res.json({"success": false});
    }
    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {
        if (typeof req.body.collection_id === 'undefined') {
            res.json({'success': false});
        }

        let collection_id = req.body.collection_id;
        let collectionInfo = CollectionModel.getCollectionInfo(collection_id);

        if (!collectionInfo) {
            res.json({'success': false});
        }

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
        let result = CollectionModel.updateCollection(collectionInfo);

        if (result) {
            res.json({'success': true});
        } else {
            res.json({'success': false});
        }
    }, function(err) {
        if (err)
            console.log(err);
        res.json({"success": false});
    });
}

module.exports = CollectionController;