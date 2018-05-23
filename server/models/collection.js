// Load language file
var getText = require('../error_msg_vi.json');
var connection = require('../models/database');


var CollectionModel = {};
CollectionModel.createCollection = function(name, description, photo, user_id, cbSuccess, cbFail) {
  connection.query(`INSERT INTO collections (user_id, name, description, photo) 
  VALUES (?, ?, ?, ?)
  `, [user_id, name, description, photo], function (error, result) {

      if (error) {
          return cbFail();
      }
  
      return cbSuccess(result.insertId);
  
  });
};

CollectionModel.deleteCollection = function(collection_id, cbSuccess, cbFail) {
  connection.query(`DELETE FROM collections 
    WHERE id = ?
  `, [collection_id], function (error) {

      if (error) {
          return cbFail();
      }
  
      return cbSuccess();
  
  });
};

CollectionModel.getCollectionInfo = function(collection_id, cbSuccess, cbFail) {
  connection.query(`SELECT id, user_id, name, description, photo
  FROM collections
  WHERE id = ?
  `, [collection_id], function (error, results) {

      if (error) {
        cbFail();
      }

      if (results < 1) {
        cbFail();
      }
  
      cbSuccess(results[0]);
  
  });
}

CollectionModel.getAllCollection = function(user_id, cbSuccess, cbFail) {
    connection.query(`SELECT id, user_id, name, description, photo
    FROM collections
    WHERE user_id = ?
    `, [user_id], function (error, results) {
  
        if (error) {
          cbFail();
        }
  
        if (results < 1) {
          cbFail();
        }
    
        cbSuccess(results);
    
    });
  }

CollectionModel.getFlashcards = function(collection_id) {
    let flashcards = [];
    connection.query(`SELECT id
    FROM cards
    WHERE collection_id = ?
    `, [collection_id], function (error, results) {
  
        if (error) {
            return flashcards;
        }
  
        if (results < 1) {
            return flashcards;
        }
    
        for (let i = 0; i < results.length; i++) {
            flashcards.push(results[i].id);
        }

        return flashcards;
    });
  }


CollectionModel.updateCollection = function(collectionInfo, cbSuccess, cbFail) {
  connection.query(`UPDATE collections
  SET name = ?, description = ?, photo = ?
  WHERE id = ?
  `, [collectionInfo.name, collectionInfo.description, collectionInfo.photo, collectionInfo.id], function (error) {

      if (error) {
            return cbFail();
      }
      return cbSuccess();
  
  });
}

module.exports = CollectionModel;