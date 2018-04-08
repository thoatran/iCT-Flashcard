var express = require("express");
var app = express();
var server = require("http").Server(app);
var io = require("socket.io")(server);
var mysql = require("mysql");
var session = require("express-session");
app.use(express.static("public"));
app.set("view engine", "ejs");
app.set("views", "./prototype");
var path = require("path")
var passport = require("passport");
var nodemailer = require("nodemailer");
var bodyParser = require("body-parser");
var cookieParser = require("cookie-parser");
var LocalStrategy = require('passport-local').Strategy;
var morgan = require('morgan');
var flash = require("connect-flash");

//connect to database
require('./config/passport');
app.use(morgan('dev')); // log every request to the console
app.use(cookieParser()); //needed for auth
app.use(session({
		
	secret: "mysecret",
	resave: true,
	saveUninitialized: true,
	cookie: {
		masAge: 1000 * 60 * 15
	}
}))
app.use(bodyParser.urlencoded({extended: true}))
app.use(passport.initialize());
app.use(passport.session());
app.use(flash());

//load route and pass in our app and fully configured passport
require('./app/route.js')(app, passport);

//launch app
var port = process.env.PORT || 3000;
server.listen(port, () => {
	console.log("server connected at port: " + port);
});