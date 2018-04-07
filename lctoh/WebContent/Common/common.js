//项目名（根目录）
//var projectDirectory="/lctoh";
var projectDirectory="http://127.0.0.1/lctoh";
//var projectDirectory="http://139.199.57.53/lctoh";
var Common={};
//店家分类获取店家  全部店家
Common.getRestaurant=projectDirectory+'/RestaurantController/getRestaurantByCityName';
//获取店家分类
Common.getRestaurantCategory=projectDirectory+'/RestaurantController/findAllRestaurantCategory';
//店家分类获取店家  全部店家
Common.getRestaurantByCityNameAndrcid=projectDirectory+'/RestaurantController/getRestaurantByCityNameAndrcid';
//获取客户登录信息
Common.getLoginInfo=projectDirectory+'/CustomerController/getLoginInfo';
//更新客户头像信息
Common.updateCustomerPortrait=projectDirectory+'/CustomerController/updateCustomerPortrait';
//修改密码根据用户ID更新密码
Common.updatePasswordByUserId=projectDirectory+'/UserController/updatePasswordByUserId';
//更新客户信息
Common.updateCustomer=projectDirectory+'/CustomerController/updateCustomer';
//获取地址信息
Common.findAddressByCustomerId=projectDirectory+'/AddressController/findAddressByCustomerId';



