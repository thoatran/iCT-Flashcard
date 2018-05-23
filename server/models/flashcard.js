// Load language file
var getText = require('../error_msg_vi.json');

var FlashcardModel = {};
FlashcardModel.createFlashcard = function(word, pronunciation, meaning, image, order, remember_score, collection_id) {
    connection.query(`INSERT INTO cards (collection_id, word, pronunciation, meaning, order, remember_score, image) 
    VALUES (?, ?, ?, ?, ?, ?, ?)
    `, [collection_id, word, pronunciation, meaning, order, remember_score, collection_id], function (error) {

      if (error) {
          return false;
      }
  
      return true;
  
    });
};

FlashcardModel.havePermission = function(checking_username, flashcard_id) {
    connection.query(`SELECT collection_id
    FROM cards
    WHERE id = ?
    `, [flashcard_id], function (error, results) {

        if (error) {
            return false;
        }

        if (results < 1) {
            return false;
        }
    
        let collection_id = results[0].collection_id;

        connection.query(`SELECT user_id
        FROM collections
        WHERE id = ?
        `, [collection_id], function (error, results) {
    
            if (error) {
                return false;
            }
    
            if (results < 1) {
                return false;
            }

            let user_id = results[0].user_id;

            connection.query(`SELECT username
            FROM users
            WHERE id = ?
            `, [user_id], function (error, results) {
        
                if (error) {
                    return false;
                }
        
                if (results < 1) {
                    return false;
                }

                let username = results[0].username;

                if (checking_username == username)
                    return true;
                else return false;

            });

        });
    
    });
};

FlashcardModel.deleteFlashcard = function(flashcard_id) {
    connection.query(`DELETE FROM cards WHERE id=?
    `, [flashcard_id], function (error) {

      if (error) {
          return false;
      }
      return true;
  
    });
};

FlashcardModel.getFlashcardInfo = function(flashcard_id) {
    connection.query(`SELECT *
    FROM cards
    WHERE id = ?
    `, [flashcard_id], function (error, results) {

        if (error) {
            return false;
        }

        if (results < 1) {
            return false;
        }
    
        return results[0];
    
    });
}


FlashcardModel.updateFlashcard = function(flashcardInfo) {
    connection.query(`UPDATE cards
    SET image = ?, word = ?, pronunciation = ?, meaning = ?, order = ?, remember_score = ?
    WHERE id = ?
    `, [flashcardInfo.image, flashcardInfo.word, flashcardInfo.pronunciation, flashcardInfo.meaning, flashcardInfo.order, flashcardInfo.remember_score, flashcardInfo.id], function (error) {

        if (error) {
            return false;
        }
        return true;
    
    });
}

module.exports = FlashcardModel;