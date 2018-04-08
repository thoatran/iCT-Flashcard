# API DEFINE

## NOTE: 
- Các xác thực người dùng sẽ dựa trên token (đoạn mã ngẫu nhiên sinh ra cho mỗi phiên đăng nhập). 1 user có thể có nhiều token tại 1 thời điểm và mỗi token có một hạn sử dụng nhất định.

## Quản lí user

### Đăng nhập:
+ Method: POST
+ Path: /api/user/login
{
    username: "xxxxx",
    password: "xxxxx"
}
+ Return:
-> Đăng nhập thành công:
{
    success: true,
    token: "ABCDXYZ..."
}
-> Đăng nhập thất bại:
{
    success: false,
    message: "Sai mật khẩu? ... etc"
}
### Lấy thông tin người dùng:
+ Method: POST
+ Path: /api/user/get_info
{
    username: "xxxxx",
    token: "ABCDXYZ..."
}
+ Return:
-> Thành công:
{
    success: true,
    profile_photo: "https://......png",
    username: "",
    fullname: "",
    bio: "", 
    email: ""
}
bio: giới thiệu ngắn về bản thân
-> Thất bại
{
    success: false,
    message: "Lỗi..."
}
### Cập nhật thông tin người dùng:
+ Method: POST
+ Path: /api/user/update_info
{
    username: "xxxxx",
    token: "ABCDXYZ..."
    profile_photo: "https://......png",
    username: "",
    fullname: "",
    bio: "", 
    email: ""
}
- Các trường profile_photo, username, fullname, bio, email có thể có hoặc không. Cần trường nào update trường đó.
+ Return:
-> Thành công:
{
    success: true 
}
-> Thất bại
{
    success: false,
    message: "Lỗi..."
}
### Cập nhật mật khẩu:
+ Method: POST
+ Path: /api/user/update_password
{
    username: "xxxxx",
    old_password: "",
    new_password: ""
}
+ Return:
-> Thành công:
{
    success: true 
}
-> Thất bại
{
    success: false,
    message: "Lỗi..."
}