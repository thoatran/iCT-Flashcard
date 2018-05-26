var nodemailer = require('nodemailer');
var emailConfig = require('../config/email');


var Mailer = {}
Mailer.transporter = nodemailer.createTransport(emailConfig);


Mailer.sendMail = function(destEmail, subject, content) {
    this.transporter.sendMail({  //email options
        from: "iCT Flashcard App <"+emailConfig.auth.user+">",
        to: destEmail, // receiver
        subject: subject, // subject
        html: content // body
    }, function(error, response) {  //callback
        if(error){
        console.log(error);
        } else {
        console.log("Email sent!");
        }
    });
}

module.exports = Mailer;