var project="/takeawayplatform";//项目名
var restaurant;
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
			
			getDishCategory(data.rtid);
		}
	},"JSON");
	
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
}


function addDish() {  
    var formData = new FormData($("#adddish")[0]);  
    $.ajax({  
         url: project+'/Merchant/addDish' ,  
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



