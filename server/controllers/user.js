// Load required packages
var UserModel = require('../models/user');

var UserController = {};

// Load language file
var getText = require('../error_msg_vi.json');

// ### User Login
UserController.login = function(req, res) {

  if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("password")) {
    return res.json({"success": false});
  }

  // convert username to lowercase
  req.body.username = String(req.body.username).toLowerCase();

  UserModel.login(req.body.username, req.body.password, function(resp) {
    res.json(resp);
  });

}

// ### User logout
UserController.logout = function(req, res) {

  if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
    return;
  }

  // convert username to lowercase
  req.body.username = String(req.body.username).toLowerCase();

  UserModel.logout(req.body.username, req.body.token, function(resp) {
    res.json(resp);
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
    UserModel.isAvailableUsername(req.body.username, function(isValidUsername){
      if (isValidUsername) {
        UserModel.register(req.body, function(resp) {
          res.json(resp);
        });
      } else {
        return res.json({"success": false,
        "message": getText['18409']
        });
      }
    });
  }

}


// ### Check login state
UserController.checkValidLogin = function(req, res) {

  if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
    return res.json({"success": false});
  }

  UserModel.checkValidLogin(req.body.username, req.body.token, function(resp) {
    res.json(resp);
  });

}

// ### Get user info
UserController.getUserInfo = function(req, res) {

  if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")
  ) {
    return res.json({"success": false});
  }

  // convert username to lowercase
  req.body.username = String(req.body.username).toLowerCase();

  UserModel.checkValidLogin(req.body.username, req.body.token, function(resp) {
    if (resp["success"] == false || resp["isLoggedIn"] == false ) {
      res.json({"success": false});
    } else {
      UserModel.getUserInfo(req.body["username"], function(resp) {
        res.json(resp);
      });
    }
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

  // convert username to lowercase
  req.body.username = String(req.body.username).toLowerCase();

  UserModel.checkValidLogin(req.body.username, req.body.token, function(resp) {
      if (resp["success"] == false || resp["isLoggedIn"] == false ) {
        res.json({"success": false});
      } else {
        UserModel.updateUserInfo(req.body["username"], req.body["newUserInfo"], function(resp) {
          res.json(resp);
        });
      }
  });

}

module.exports = UserController;