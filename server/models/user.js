// Load required packages
var bcrypt = require('bcrypt');
var crypto = require('crypto');
const saltRounds = 10;

var connection = require('../models/database');

var UserModel = {};


UserModel.usernameStandardlize = function(username) {
	if (typeof username != "string") {
		return "error##";
	} else {
		return username.toLowerCase();
	}
}

UserModel.login = function(user, pass, cbSuccess, cbFail) {

	// Query password
	connection.query(`SELECT * FROM users WHERE username = ?;`, [UserModel.usernameStandardlize(user)], function (error, results, fields) {

		if (error) {
			return cbFail(error);
		}
	
		if (results.length == 0) {
			return cbFail("Result length = 0.");
		}

		// Load hash from your password DB.
		bcrypt.compare(pass, results[0].password, function(err, resp) {
			if (err || resp == false) {
				return cbFail();
			} else { // Check login info: ok

				// => gen new token
				crypto.randomBytes(20, function(err, buffer) {
					let token = buffer.toString('hex');

					// Update token to database
					let query = connection.query(`INSERT INTO tokens (user_id, token, token_exp) VALUES
					(?, ?, DATE_ADD(NOW(), INTERVAL 1 DAY));`,
					[results[0].id,
					token], function(err){

						if (err) {
							return cbFail(err);
						}

						// Send token to client
						return cbSuccess(token);
					});

				});

			}					

		});
	});
};


UserModel.logout = function(user, token,  cbSuccess, cbFail) {

	connection.query(`SELECT id FROM users
		WHERE users.username = ?
	`, [this.usernameStandardlize(user), token], function (error, results) {

			if (error) {
				return cbFail(error);
			}
			
			if (results.length == 0) {
				return cbFail();
			}
			

			let user_id = parseInt(results[0].id);
			connection.query(`DELETE FROM tokens
			WHERE user_id = ? AND token = ?
			`, [user_id, token], function (error) {
				
				if (error) {
					return cbFail(error);
				}

				return cbSuccess();
			
			});
			
	});
};

UserModel.checkValidLogin = function(user, token, cbSuccess, cbFail) {
	
	// Convert username to lowercase
	user = this.usernameStandardlize(user);
	token = String(token);
	
	// Query password
	connection.query(`SELECT users.id as id, users.username as username, users.fullname as fullname
		FROM users
		INNER JOIN tokens ON users.id = tokens.user_id
		WHERE users.username = ?
		AND tokens.token = ?
		AND tokens.token_exp > NOW()
		`, [user, token], function (error, results) {

			if (error) {
				return cbFail(error);
			}
		
			if (results.length == 0) {
				return cbFail();
			} else {

				if (typeof results[0].username == "undefined") {
					cbFail();
				}

				let displayName = results[0].fullname;
				if (displayName == null
				|| displayName == ''
				) {
					displayName = results[0].username || '';
				}

				return cbSuccess(
					{
						"displayName": displayName,
						"user_id": results[0].id
					}
				);
			}
		
	});
};


UserModel.isAvailableUsername = function(username, cbSuccess, cbFail) {
	connection.query(`SELECT * FROM users
		WHERE username = ?
		`, [this.usernameStandardlize(username)], function (error, results) {

			if (error) {
				return cbFail(err);
			}
		
			if (results.length == 0) {
				return cbSuccess();
			} else {
				return cbFail('Result length = 0.');
			}
		
	});
};


UserModel.register = function(registerInfo, cbSuccess, cbFail) {
	bcrypt.hash(registerInfo.password, saltRounds, function(err, hashedPassword) {

		if (err) {
			cbFail(err);
		}

		connection.query(`INSERT INTO users (fullname, username, password, email) 
		VALUES (?, ?, ?, ?)
		`, [registerInfo.fullname, registerInfo.username, hashedPassword, registerInfo.email.toLowerCase()], function (error, results) {

			if (error) {
				cbFail(error);
			}
		
			return cbSuccess();
		
		});
	});
};


UserModel.getUserInfo = function(username, cbSuccess, cbFail) {
	connection.query(`SELECT username, email, fullname, bio, profile_photo
	FROM users
	WHERE username = ?
	`, [this.usernameStandardlize(username)], function (error, results) {

		if (error) {
			cbFail(error);
		}

		return cbSuccess(results[0]);
	
	});
}

UserModel.updateUserInfo = function(username, newUserInfo, cbSuccess, cbFail) {

	let updateQueryArr = [];
	let newValuesArr = [];

	if (newUserInfo.hasOwnProperty("email")) {
		updateQueryArr.push(" email = ? ");
		newValuesArr.push(newUserInfo["email"].toLowerCase());
	}

	if (newUserInfo.hasOwnProperty("fullname")) {
		updateQueryArr.push(" fullname = ? ");
		newValuesArr.push(newUserInfo["fullname"]);
	}

	if (newUserInfo.hasOwnProperty("bio")) {
		updateQueryArr.push(" bio = ? ");
		newValuesArr.push(newUserInfo["bio"]);
	}

	if (newUserInfo.hasOwnProperty("profile_photo")) {
		updateQueryArr.push(" profile_photo = ? ");
		newValuesArr.push(newUserInfo["profile_photo"]);
	}

	if (newUserInfo.hasOwnProperty("newPassword")) {
		let hashPass = bcrypt.hashSync(newUserInfo['newPassword'], saltRounds);
		updateQueryArr.push(" password = ? ");
		newValuesArr.push(hashPass);
	}

	if (newValuesArr.length == 0) {
		return cbFail();
	}

	// Push username to use in query
	newValuesArr.push(this.usernameStandardlize(username));

	// Update info
	connection.query(`UPDATE users
		SET ` + updateQueryArr.join(' , ') + `
		WHERE username = ?
		`, newValuesArr, function (error) {
		if (error) {
			return cbFail(error);
		} else {
			return cbSuccess();
		}
	});

}


UserModel.verifyEmail = function(email, cbSuccess, cbFail) {
	connection.query(`SELECT username
	FROM users
	WHERE email = ?
	`, [email.toLowerCase()], function (error, results) {

		if (error) {
			cbFail(error);
		} else if (results.length < 1) {
			cbFail(error);
		} else return cbSuccess(results[0]);
		
	});
}

module.exports = UserModel;