const cloudinary = require('cloudinary')
      ,config     = require('../config/image.js').cloudinary;
const path = require("path");

// Load required packages
var UserModel = require('../models/user');
var ImageModel = require('../models/image');
var connection = require('../models/database');

// Load language file
var getText = require('../languages/vi.json');

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

    if (!req.body.hasOwnProperty("username") || !req.body.hasOwnProperty("token")) {
        return res.json({"success": false});
    }

    return UserModel.checkValidLogin(req.body.username, req.body.token, function(userInfo) {

        cloudinary.v2.uploader.upload(req.body.imageURI,function (err, result) {
            if(err) {
                console.log(err);
                return res.json({
                    success: false,
                    message: getText['18401']
                });
            }
    
            if(result) {

                ImageModel.saveImage(userInfo['user_id'], result.secure_url, function() {
                    return res.json({
                        success: true,
                        message: getText['10401'],
                        imageUrl: result.secure_url
                    });
                }, function() {
                    return res.json({
                        success: false,
                        message: getText['18402']
                    });
                });

            } else {
                return res.json({
                    success: false,
                    message: getText['18401']
                });
            }   
        });


    }, function() {
        return res.json({
            success: false,
            message: getText['18403']
        });
    });

}

module.exports = ImageController;

