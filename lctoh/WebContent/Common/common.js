//项目名（根目录）
//var projectDirectory="/lctoh";
var projectDirectory="/lctoh";
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
//订单过程线统计接口
Common.orderStatistics=projectDirectory+'/OrderStatisticsController/orderStatistics';
//添加收藏店家
Common.addCollectRestaurant=projectDirectory+'/RestaurantController/addCollectRestaurant';
//查看收藏店家
Common.getCollectRestaurant=projectDirectory+'/RestaurantController/findCollectRestaurant';

//获取地址栏参数
function GetQueryString(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
}




//格式化日期
function getDate(dateTime){
	var date=new Date(dateTime);
	
	return date.getFullYear()+'/'+(date.getMonth()+1)+'/'+date.getDate();
}
function getTime(dateTime){
	var date=new Date(dateTime);
	return date.getHours()+':'+date.getMinutes();
}
//格式化时间：2011-12-31 00:00:00
function getDateTime(dateTime){
	
	return date.getFullYear()+'/'+date.getMonth()+'/'+date.getDate();
}



function dateFtt(fmt,date)   
{ //author: meizz   
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "h+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
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
			 //  layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		   }
		})
	
}
