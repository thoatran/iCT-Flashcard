// Load language file
var getText = require('../languages/vi.json');
var connection = require('../models/database');

var FlashcardModel = {};
FlashcardModel.createFlashcard = function(word, pronunciation, meaning, image, order, remember_score, collection_id, cbSuccess, cbFail) {
    connection.query(`INSERT INTO cards (collection_id, word, pronunciation, meaning, \`order\`, remember_score, image) 
    VALUES (?, ?, ?, ?, ?, ?, ?);
    `, [collection_id, word, pronunciation, meaning, order, remember_score, image], function (error, result) {

      if (error) {
          return cbFail();
      }
  
      return cbSuccess(result.insertId);
  
    });
};

FlashcardModel.havePermission = function(checking_username, flashcard_id, cbSuccess, cbFail) {
    connection.query(`SELECT collection_id
    FROM cards
    WHERE id = ?
    `, [flashcard_id], function (error, results) {

        if (error) {
            return cbFail();
        }

        if (results < 1) {
            return cbFail();
        }
    
        let collection_id = results[0].collection_id;

        connection.query(`SELECT user_id
        FROM collections
        WHERE id = ?
        `, [collection_id], function (error, results) {
    
            if (error) {
                return cbFail();
            }
    
            if (results < 1) {
                return cbFail();
            }

            let user_id = results[0].user_id;

            connection.query(`SELECT username
            FROM users
            WHERE id = ?
            `, [user_id], function (error, results) {
        
                if (error) {
                    return cbFail();
                }
        
                if (results < 1) {
                    return cbFail();
                }

                let username = results[0].username;

                if (checking_username == username)
                    return cbSuccess();
                else return cbFail();

            });

        });
    
    });
};

FlashcardModel.deleteFlashcard = function(flashcard_id, cbSuccess, cbFail) {
    connection.query(`DELETE FROM cards WHERE id=?
    `, [flashcard_id], function (error) {

      if (error) {
          return cbFail();
      }
      return cbSuccess();
  
    });
};

FlashcardModel.deleteAllFlashcardByCollectionId = function(collection_id, cbSuccess, cbFail) {
    connection.query(`DELETE FROM cards WHERE collection_id=?
    `, [collection_id], function (error) {

      if (error) {
          return cbFail();
      }
      return cbSuccess();
  
    });
};

FlashcardModel.getFlashcardInfo = function(flashcard_id, cbSuccess, cbFail) {
    connection.query(`SELECT *
    FROM cards
    WHERE id = ?
    `, [flashcard_id], function (error, results) {

        if (error) {
            return cbFail();
        }

        if (results < 1) {
            return cbFail();
        }
    
        return cbSuccess(results[0]);
    
    });
}


FlashcardModel.updateFlashcard = function(flashcardInfo, cbSuccess, cbFail) {
    connection.query(`UPDATE cards
    SET image = ?, word = ?, pronunciation = ?, meaning = ?, \`order\` = ?, remember_score = ?
    WHERE id = ?
    `, [flashcardInfo.image, flashcardInfo.word, flashcardInfo.pronunciation, flashcardInfo.meaning, flashcardInfo.order, flashcardInfo.remember_score, flashcardInfo.id], function (error) {

        if (error) {
            return cbFail();
        }
        return cbSuccess();
    
    });
}

module.exports = FlashcardModel;