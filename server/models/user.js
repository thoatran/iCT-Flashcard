// Load required packages
var bcrypt = require('bcrypt');
var crypto = require('crypto');
const saltRounds = 10;

var connection = require('../models/database');

var UserModel = {};
UserModel.login = function(user, pass, callback) {
	// console.log("USER", user, pass);

	// Query password
	connection.query(`SELECT * FROM users WHERE username = ?;`, [user], function (error, results, fields) {

		if (error) {
			console.log(error);
			return callback({"success": false});
		}
	
		if (results.length == 0) {
			return callback({"success": false});
		}

		// Load hash from your password DB.
		bcrypt.compare(pass, results[0].password, function(err, resp) {
			if (err || resp == false) {
				return callback({"success": false});
			} else { // Check login info: ok

				// => gen new token
				crypto.randomBytes(20, function(err, buffer) {
					let token = buffer.toString('hex');

					// Update token to database
					let query = connection.query(`INSERT INTO tokens (user_id, token, token_exp) VALUES
					(?, ?, DATE_ADD(NOW(), INTERVAL 1 DAY));`,
					[results[0].id,
					token], function(err, resp){

						if (err) {
							return callback({"success": false});
						}

						// Send token to client
						return callback({"success": true, "token": token});
					});

				});

			}					

		});
	});
};


UserModel.logout = function(user, token, callback) {

	connection.query(`SELECT id FROM users
		WHERE users.username = ?
	`, [user, token], function (error, results) {

			if (error) {
				console.log(error);
				return callback({"success": false});
			}
			
			if (results.length == 0) {
				return callback({"success": false});
			}
			

			let user_id = parseInt(results[0].id);
			connection.query(`DELETE FROM tokens
			WHERE user_id = ? AND token = ?
			`, [user_id, token], function (error) {
				
				if (error) {
					console.log(error);
					return callback({"success": false});
				}

				return callback({"success": true});
			
			});
			
	});
};

UserModel.checkValidLogin = function(user, token, callback) {
	
	// Convert username to lowercase
	user = String(String(user).toLowerCase());
	token = String(token);
	
	// Query password
	connection.query(`SELECT users.id, users.username, users.fullname FROM users
		INNER JOIN tokens ON users.id = tokens.user_id
		WHERE users.username = ?
		AND tokens.token = ?
		AND tokens.token_exp > NOW()
		`, [user, token], function (error, results) {

			if (error) {
				console.log(error);
				return callback({"success": false});
			}
		
			if (results.length == 0) {
				return callback(
					{
						"success": true,
						"isLoggedIn": false
					}
				);
			} else {

				let displayName = results[0].fullname;
				if (displayName == null
				|| displayName == ''
				) {
					displayName = results[0].username;
				}

				return callback(
					{
						"success": true,
						"isLoggedIn": true,
						"displayName": displayName,
						"user_id": results[0].id
					}
				);
			}
		
	});
};


UserModel.isAvailableUsername = function(username, callback) {
	connection.query(`SELECT * FROM users
		WHERE username = ?
		`, [username], function (error, results) {

			if (error) {
				console.log(error);
				return callback(false);
			}
		
			if (results.length == 0) {
				return callback(true);
			} else {
				return callback(false);
			}
		
	});
};


UserModel.register = function(registerInfo, callback) {
	bcrypt.hash(registerInfo.password, saltRounds, function(err, hashedPassword) {

		if (err) {
			console.log(err);
			return callback({"success": false});
		}

		connection.query(`INSERT INTO users (fullname, username, password, email) 
		VALUES (?, ?, ?, ?)
		`, [registerInfo.fullname, registerInfo.username, hashedPassword, registerInfo.email], function (error, results) {

			if (error) {
				console.log(error);
				return callback({"success": false});
			}
		
			return callback({"success": true});
		
		});
	});
};


UserModel.getUserInfo = function(username, callback) {
	connection.query(`SELECT username, email, fullname, bio, profile_photo
	FROM users
	WHERE username = ?
	`, [username], function (error, results) {

		if (error) {
			console.log(error);
			return callback({"success": false});
		}

		return callback({"success": true,
			"data": results[0]
		});
	
	});
}

UserModel.updateUserInfo = function(username, newUserInfo, callback) {

	let updateQueryArr = [];
	let newValuesArr = [];

	if (newUserInfo.hasOwnProperty("email")) {
		updateQueryArr.push(" email = ? ");
		newValuesArr.push(newUserInfo["email"]);
	}

	if (newUserInfo.hasOwnProperty("fullname")) {
		updateQueryArr.push(" fullname = ? ");
		newValuesArr.push(newUserInfo["fullname"]);
	}

	if (newUserInfo.hasOwnProperty("bio")) {
		updateQueryArr.push(" bio = ? ");
		newValuesArr.push(newUserInfo["bio"]);
	}

	if (newUserInfo.hasOwnProperty("newPassword")) {
		let hashPass = bcrypt.hashSync(newUserInfo['newPassword'], saltRounds);
		updateQueryArr.push(" password = ? ");
		newValuesArr.push(hashPass);
	}

	if (newValuesArr.length == 0) {
		return callback({"success": true});
	}

	// Push username to use in query
	newValuesArr.push(username);

	// Update info
	connection.query(`UPDATE users
		SET ` + updateQueryArr.join(' , ') + `
		WHERE username = ?
		`, newValuesArr, function (error) {
		if (error) {
			return callback({"success": false});
		} else {
			return callback({"success": true});
		}
	});

}

module.exports = UserModel;