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