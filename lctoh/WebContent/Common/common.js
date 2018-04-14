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
//获取根据城市ID获取店家信息
Common.getRestaurantCityId=projectDirectory+'/RestaurantController/getRestaurantCityId';
//获取定位信息
Common.getLocation=projectDirectory+'/LocationController/getLocation';
//获取系统公告
Common.findNoticeByStateId=projectDirectory+'/NoticeController/findNoticeByStateId';
//获取店家信息
Common.getRestaurantByRestaurantId=projectDirectory+'/RestaurantController/getRestaurantByRestaurantId';
//添加菜肴至购物车
Common.addDishCart=projectDirectory+'/CartController/addDishCart';
//查询订单
Common.findOrder=projectDirectory+'/OrderController/findOrder';
//查询订单(id)
Common.findOrderByOrderId=projectDirectory+'/OrderController/findOrderByOrderId';
//获取地址栏参数
function GetQueryString(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
}


var timeid = window.setInterval(function(){ 
	testsql();
},100000);
function testsql(){
	$.ajax({
		   type: "post",
		   data:null,
		   url:projectDirectory+'/NoticeController/findAllNotice',
		   dataType: "json",
		   success:function(data){
			   
			   
		   },
		   error:function(errordate){
			   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		   }
		})
	
}
