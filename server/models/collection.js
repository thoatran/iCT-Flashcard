// Load language file
var getText = require('../error_msg_vi.json');

var CollectionModel = {};
CollectionModel.createCollection = function(name, description, photo, user_id) {
  connection.query(`INSERT INTO collections (user_id, name, description, photo) 
  VALUES (?, ?, ?, ?)
  `, [user_id, name, description, photo], function (error) {

      if (error) {
          return false;
      }
  
      return true;
  
  });
};

CollectionModel.deleteCollection = function(collection_id) {
  connection.query(`DELETE FROM collections 
    WHERE id = ?
  `, [collection_id], function (error) {

      if (error) {
          return false;
      }
  
      return true;
  
  });
};

CollectionModel.getCollectionInfo = function(collection_id) {
  connection.query(`SELECT id, user_id, name, description, photo
  FROM collections
  WHERE id = ?
  `, [collection_id], function (error, results) {

      if (error) {
          return false;
      }

      if (results < 1) {
          return false;
      }
  
      return results[0];
  
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


CollectionModel.updateCollection = function(collectionInfo) {
  connection.query(`UPDATE collections
  SET name = ?, description = ?, photo = ?
  WHERE id = ?
  `, [collectionInfo.name, collectionInfo.description, collectionInfo.photo, collectionInfo.id], function (error) {

      if (error) {
          return false;
      }
      return true;
  
  });
}

module.exports = CollectionModel;