const cloudinary = require('cloudinary')
      ,config     = require('../config/image.js').cloudinary;
const path = require("path");

// Load required packages
var UserModel = require('../models/user');
var connection = require('../models/database');

// Load language file
var getText = require('../error_msg_vi.json');

/*configure our cloudinary*/
cloudinary.config({
    cloud_name: config.cloud_name, 
    api_key: config.api_key, 
    api_secret: config.api_secret 
});

/* configure our cloudinary*/
var ImageController = {};

ImageController.deleteImage = function (publicId,resourceType,callback){ 
    console.log(resourceType);//image,video,raw

    cloudinary.api.delete_resources(publicId, function(result) {
        console.log(result);
         if(result.hasOwnProperty("error")){
             callback(result);
             return;
         }else{
              callback(result);

         }  
    },{all:true,resource_type:resourceType});   
}


ImageController.uploadImage = function(req, res) {

    UserModel.checkValidLogin(req.body.username, req.body.token, function(isLoggedIn) {

        if (isLoggedIn['isLoggedIn'] != true) {
            return res.json({
                success: false,
                message: getText['18403']
            });
        }

        cloudinary.v2.uploader.upload(req.body.imageURI,function (err, result) {
            if(err) {
                console.log(err);
                return res.json({
                    success: false,
                    message: getText['18401']
                });
            }
    
            if(result) {

                // Save image in image list of user
                connection.query(`INSERT INTO images
                (user_id, image) VALUES (?, ?);
                `, [isLoggedIn['user_id'], result.secure_url], function (error) {

                    if (error) {
                        return res.json({
                            success: false,
                            message: getText['18402']
                        });
                    }

                    return res.json({
                        success: true,
                        message: getText['10401'],
                        imageUrl: result.secure_url
                    });

                });

            } else {
                return res.json({
                    success: false,
                    message: getText['18401']
                });
            }   
        });



    });

}

module.exports = ImageController;

