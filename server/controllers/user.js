// Load required packages
var UserModel = require('../models/user');

var UserController = {};

// Load language file
var getText = require('../error_msg_vi.json');

var MailService = require('../services/mailer');


// ### User Login
UserController.login = function(req, res) {

  if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("password")) {
    return res.json({"success": false});
  }

  return UserModel.login(req.body.username, req.body.password, function(token) {
    res.json({"success": true, "token": token});
  }, function( err ) {
    if (err)
      console.log(err);
    res.json({"success": false});
  });

}

// ### User logout
UserController.logout = function(req, res) {

  if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
    return;
  }

  UserModel.logout(req.body.username, req.body.token, function(){
    return res.json({"success": true});
  }, function( err ){
    if (err)
      console.log(err);
    res.json({"success": false});
  });

}

// ### User Register
UserController.register = function(req, res) {

  if (!req.body.hasOwnProperty("username")
    || !req.body.hasOwnProperty("password")
    || !req.body.hasOwnProperty("email")) {
    return res.json({"success": false,
      "message": getText['18406']
    });
  }

  // convert username to lowercase
  req.body.username = String(req.body.username).toLowerCase();

  // Check valid for password
  if (req.body.password.length == 0
    ||  /\s/g.test(req.body.password))
  {
    return res.json({"success": false,
      "message": getText['18407']
    });
  }

  // Check valid for username
  let lettersRegex = /^[0-9a-zA-Z]+$/;
  if(!req.body.password.match(lettersRegex)) {
    return res.json({"success": false,
      "message": getText['18408']
    });
  } else {
    UserModel.isAvailableUsername(req.body.username, function(){
      UserModel.register(req.body, function(resp) {
        res.json({"success": true});
      });
    }, function() {
      return res.json({"success": false,
        "message": getText['18409']
        });
    });
  }

}


// ### Check login state
UserController.checkValidLogin = function(req, res) {

  if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
    return res.json({"success": false});
  }

  UserModel.checkValidLogin(req.body.username, req.body.token, function(resp) {
    resp.success = true;
    resp["isLoggedIn"] = true;
    res.json(resp);
  }, function(err) {
    if (err)
      console.log(err);
    res.json({"success": false});
  });

}

// ### Get user info
UserController.getUserInfo = function(req, res) {

  if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")
  ) {
    return res.json({"success": false});
  }

  return UserModel.checkValidLogin(req.body.username, req.body.token, function(resp) {
      UserModel.getUserInfo(req.body["username"], function(data) {
        let resp = {}
        resp.success = true;
        resp.data = data;
        res.json(resp);
      }, function(err) {
        if (err)
          console.log(err);
        res.json({"success": false});
      });
  }, function(err) {
    if (err)
      console.log(err);
    res.json({"success": false});
  });

}

// ### Update user info
UserController.updateUserInfo = function(req, res) {

  if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")
    || !req.body.hasOwnProperty("newUserInfo")
  ) {
    return res.json({"success": false,
      "message": getText['18410']
    });
  }

  UserModel.checkValidLogin(req.body.username, req.body.token, function() {
      UserModel.updateUserInfo(req.body["username"], req.body["newUserInfo"], function() {
        res.json({"success": true});
      }, function() {
        if (err)
          console.log(err);
        res.json({"success": false});
      });
  }, function(err) {
    if (err)
      console.log(err);
    res.json({"success": false});
  });

}


// ### Forgot password
UserController.forgotPassword = function(req, res) {

	if (!req.body.hasOwnProperty("email")) {
		return res.json({"success": false,
		"message": getText['18410']
		});
	}

	let userEmail = req.body.email;
	// Verify email
	UserModel.verifyEmail(userEmail, function(userInfo) {
    
    
    // Random a new password
		let newUserInfo = {};
		newUserInfo.newPassword = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
    

    // Update in database
		UserModel.updateUserInfo(userInfo.username, newUserInfo,
		function(){
      res.json({"success": true}); // Return true because of security reason
      console.log(newUserInfo.newPassword);

      MailService.sendMail(userEmail, getText['18419'], 
      getText['18420'] + newUserInfo.newPassword
      );

		}, function(){
			res.json({"success": false});
		});

		
	}, function(err) {
	  if (err)
		console.log(err);
	  res.json({"success": true}); // Return true because of security reason
	});

}

module.exports = UserController;