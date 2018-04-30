var project="/takeawayplatform/";//项目名
var restaurant;

var _RegionList;
//加载初始数据
window.onload=function(){ 
	$.post(project+"RestaurantController/getRestaurant",{},function(data){
		if(data==null){
			if(top.location.href!=self.location.href){
				top.location.href=project+"res/login.html";
			}
			top.location.href=project+"res/login.html";
		}else{
			restaurant=data;
			putimage();
		}
	},"JSON");
	
}


//加载图片
function putimage(){
	var image= $("div.wrap form  table.list-style tbody tr td img");
	image.attr('src',project+restaurant.rtimage); 
}







function doUpload() {  
    var formData = new FormData($("#uploadForm")[0]);  
    $.ajax({  
         url: project+'RestaurantController/upload' ,  
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
	
	setTimeout(function(){self.location=project+"restaurant_admin/edit_res_image.html";},1000);
}
