// Load language file
var getText = require('../languages/vi.json');
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
  
  // Delete flashcard first
  connection.query(`DELETE FROM cards 
    WHERE collection_id = ?
  `, [collection_id], function (error) {

      if (error) {
          return cbFail();
      }
  
      return connection.query(`DELETE FROM collections 
      WHERE id = ?
      `, [collection_id], function (error) {
    
          if (error) {
              return cbFail();
          }
      
          return cbSuccess();
      
      });
  
  });
  
 
};

CollectionModel.getCollectionInfo = function(collection_id, cbSuccess, cbFail) {
  connection.query(`SELECT id, user_id, name, description, photo, remember_score
  FROM collections
  WHERE id = ?
  `, [collection_id], function (error, results) {

      if (error) {
        return cbFail();
      }

      if (results < 1) {
        return cbFail();
      }
  
      return cbSuccess(results[0]);
  
  });
}

CollectionModel.getAllCollection = function(user_id, cbSuccess, cbFail) {
    connection.query(`SELECT id, user_id, name, description, photo, remember_score
    FROM collections
    WHERE user_id = ?
    `, [user_id], function (error, results) {
  
        if (error) {
          return cbFail();
        }
    
        cbSuccess(results);
    
    });
  }

CollectionModel.getFlashcards = function(collection_id, cbSuccess, cbFail) {
    let flashcards = [];
    connection.query(`SELECT *
    FROM cards
    WHERE collection_id = ?
    `, [collection_id], function (error, results) {
  
        if (error) {
            return cbFail();
        }
    
        for (let i = 0; i < results.length; i++) {
            flashcards.push(results[i]);
        }

        return cbSuccess(flashcards);
    });
  }


CollectionModel.updateCollection = function(collectionInfo, cbSuccess, cbFail) {
  connection.query(`UPDATE collections
  SET name = ?, description = ?, photo = ?, remember_score = ?
  WHERE id = ?
  `, [collectionInfo.name, collectionInfo.description, collectionInfo.photo, collectionInfo.remember_score, collectionInfo.id], function (error) {

      if (error) {
            return cbFail();
      }
      return cbSuccess();
  
  });
}

module.exports = CollectionModel;