var project="/takeawayplatform";//项目名
var restaurant;
var orderListData;
var did;
var _dish;
//加载初始数据
window.onload=function(){ 
	$.post(project+"/RestaurantController/getRestaurant",{},function(data){
		if(data==null){
			if(top.location.href!=self.location.href){
				top.location.href=project+"/res/login.html";
			}
			top.location.href=project+"/res/login.html";
		}else{
			restaurant=data;
			did=GetQueryString("did");
			if(did==null||did.length==0)return ;
			
		//	$("div.wrap div.page-title span.fl em").html("订单编号："+did);
			loadDishByDid(did);//根据用户加载订单
		}
	},"JSON");
}
//得到菜肴信息
function loadDishByDid(did){
	$.post(project+"/Dish/getDishById",{"did":did},function(data){
		_dish=data;
		putDish(data)
	},"JSON");
	
}

//加载菜肴信息
function putDish(dish){
	//alert(dish.dishcategory)
	getDishCategory(restaurant.rtid);
	dishtable=$("div.wrap form table.list-style tbody tr td input");
	
	dishtable.eq(0).val(_dish.dname);
	dishtable.eq(1).val(_dish.did);
	dishtable.eq(2).val(_dish.dprice);
	
	var image= $("div.wrap form  table.list-style tbody tr td img");
	image.attr('src',project+"/information/"+_dish.dimage); 
	$("textarea").eq(0).val(_dish.dintro);
	
	//alert(dishtable.length)
}

//得到分类信息
function getDishCategory(rtid){
	
	$.post(project+"/Dish/index2",{"rtid":rtid},function(data){
		putDishCategory(data);
	},"JSON");
}
//加载下拉菜单至页面
function putDishCategory(dishCategoryLish){
	var dishtable=$("div.wrap form table.list-style tbody tr select");
	//alert(dishCategoryLish[0].dcid);
	for(var i=0;i<dishCategoryLish.length;i++){
		var opt=$("<option value='"+dishCategoryLish[i].dcid+"'>"+dishCategoryLish[i].dcname+"</option>");
		dishtable.append(opt);
	}
	var op_msg=$("div.wrap form table.list-style tbody tr select option");
	for (var i=0;i<op_msg.length;i++) {
		if(op_msg[i].value==_dish.dishcategory.dcid)
			op_msg[i].selected=true;
	}
}




function updatDishdata() {  
    var formData = new FormData($("#updatedish")[0]);  
    $.ajax({  
         url: project+'/Merchant/updateDish' ,  
         type: 'POST',  
         data: formData,  
         async: false,  
         cache: false,  
         contentType: false,  
         processData: false,  
         success: function (returndata) {  
        	 if(returndata==1){
        		 alert("成功");  
        		 shuaxin();
        	 }else if(returndata==2){
        		 alert("数据输入有误");   
        	 }else{
        		 alert("文件格式不对或其他原因");   
        	 }
         },  
         error: function (returndata) {  
             alert("文件过大");  
         }  
    });  
}




function shuaxin(){
	
	setTimeout(function(){self.location=project+"/restaurant_admin/dish_list.html";},1000);
}
















//获取地址栏参数

/*
 * 这是用户获取首页跳转进来时传进来的店家ID方法
 * 
 * */

function GetQueryString(name)
{
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r!=null)return  unescape(r[2]); return null;
}
