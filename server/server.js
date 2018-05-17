// Get the packages we need
var express = require('express');
var bodyParser = require('body-parser');
var cors = require('cors');
var fs = require('fs');
var path = require('path');


// Controllers
var UserController = require('./controllers/user');
var DictionaryController = require('./controllers/dictionary');
var ImageController = require('./controllers/image');

// Create our Express application
var app = express();

// Use the body-parser package in our application
app.use(bodyParser.urlencoded({limit: "50mb", extended: true, parameterLimit:500000000}));
app.use(bodyParser.json({limit: '50mb'}));

// Receive request from all source
app.use(function (req, res, next) {
  // Website you wish to allow to connect
  res.setHeader('Access-Control-Allow-Origin', '*');
  // Pass to next layer of middleware
  next();
});


// Serve static pages
app.use('/vi', express.static('public/vi'));
app.use('/en', express.static('public/en'));

// Vietnamese by default
app.get('/', function(req, res) {
  res.redirect('/vi/');
});


// Serve api def
app.get('/apis.txt', function(req, res) {
  res.sendFile('apis.txt', {root: __dirname })
});

// Create our Express router
var router = express.Router();

// Register all our routes with /api
app.use('/api/', router);


// ==================================================================
// USER APIs FOR DICTIONARY
/// ### E-V Dictionary
// Lookup: /api/dictionary/ev?lookup=<word>
router.route('/dictionary/ev')
  .get(DictionaryController.lookup);


// ==================================================================
// USER APIs FOR USER INFO

// ### User Register
// params: username, password, email, fullname
// success: return {'success': true}
// fail: return {'sucess': false, 'message': '<why fail?>'}
router.route('/user/register')
  .post(UserController.register);


// ### User Login
// params: {'username': '<username>', 'password': '<password>'}
// success: return {'success': true, 'token': '<token>'}
// fail: return {'success': false}
router.route('/user/login')
  .post(UserController.login);


// ### User logout
// params: {'username': '<username>', 'token', '<token>'}
// success: return {'success': true}
// fail: return {'success': false}
router.route('/user/logout')
.post(UserController.logout);


// ### Check login state
// params: {'username': '<username>', 'token', '<token>'}
// success: return {'success': true, 'isLoggedIn': true}
// fail: return {'success': false}
router.route('/user/islogin')
  .post(UserController.checkValidLogin);


// ### Get user info
// params: username, token
// success: return {'success': true, 'data':{<email, bio, fullname, username, phofile_photo>} }
router.route('/user/getinfo')
  .post(UserController.getUserInfo);

// ### Update user info
// params: {'username': '<username>', 'token', '<token>',
//          'oldPassword': '<oldPassword>'
//                'newUserInfo': {
//                      'email'        : '<email>',
//                      'bio'          : '<bio>',
//                      'fullname'     : '<fullname>',
//                      'newPassword'  : '<newPassword>',
//                 }
//          }
//    => 'oldPassword', 'newPassword' are used when user wants to change their password
//    => all properties of 'newUserInfo' are optional
// success: return {'success': true}
// fail: return {'success': false}
router.route('/user/updateinfo')
  .post(UserController.updateUserInfo);


// ==================================================================
// USER APIs FOR IMAGE MANAGEMENT
// ### Upload image
// params: {'user': '<username>', 'token', '<token>',
//      'image': <image>
//}
// success: return {'success': true, 'image': '<image-name>'}
// fail: return {'success': false}
// router.route('/image/upload')
//   .post(ImageController.upload);

router.route('/image/upload')
  .post(ImageController.uploadImage);



// ==================================================================
// USER APIs FOR COLLECTION MANAGEMENT


// ### Start the server
// Use environment defined port or 4000
var port = process.env.PORT || 4000;
app.listen(port);
console.log('Server is listening on port: ' + port);