module.exports = (app, passport) => {
	var LocalStrategy   = require('passport-local').Strategy;
	var path = require("path");
	app.route("/")
	.get((req, res) => {
		res.sendFile(path.join(__dirname + '/../prototype/login.html'));
	})
	.post(passport.authenticate("local", 
	{
		failureRedirect: '/',
		successRedirect: '/navi'
	}))

	passport.use(new LocalStrategy(
		(username, password, done) => {
			fs.readFile('', (err, data) => {
				const db = JSON.parse(data);
				const userRecord = db.find(user => user.usr == username)
				if(userRecord && userRecord.pwd == password)
					return done(null, userRecord);
				else
					return done(null, false);
			})
		})
	)

	passport.serializeUser((user, done) => {
		done(null, user.usr);
	});

	passport.deserializeUser((user, done) => {
		fs.readFile('', (err, data) => {
			const db = JSON.parse(data);
			const userRecord = db.find(user => user.usr == username)
			if(userRecord)
				return done(null, userRecord);
			else
				return done(null, false);
		})
	})

	app.get("/signup", (req, res) => {
		res.sendFile(path.join(__dirname + '/../prototype/register.html'));


		app.post("/signup", passport.authenticate('local-signup', {
			successRedirect: '/login',
			failureRedirect: '/signup',
			failureFlash: true
		}))
	})

	app.use("*",(req,res) => {
	  res.sendFile(path.join(__dirname + "/../prototype/404.html"));
	});

	app.get("/navi", isLoggedIn, (req, res) => {
		res.sendFile(path.join(__dirname + '/../prototype/dashboard.html'));
	});

	//logout
	app.get("/logout", (req, res) => {
		req.logout();
		res.redirect('/');
	})
}

function isLoggedIn(req, res, next) {

	// if user is authenticated in the session, carry on
	if (req.isAuthenticated())
		return next();

	// if they aren't redirect them to the home page
	res.redirect('/');
}