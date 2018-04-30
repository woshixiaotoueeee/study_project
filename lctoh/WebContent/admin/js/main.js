var project="/takeawayplatform";
var restaurant;
window.onload=function (){
	$.post(project+"/RestaurantController/getRestaurant",{},function(data){
		if(data==null){
			if(top.location.href!=self.location.href){
				top.location.href=project+"/res/login.html";
			}
			top.location.href=project+"/res/login.html";
		}else{
			restaurant=data;
			loadinfo()
		}
		
		
		
	},"JSON");
}


//加载信息到页面
function loadinfo(){
	var elementList=$("div.wrap dl dd ul li span");
	var daifukuang=getExecutedOrder(2);
	elementList[1].innerHTML=daifukuang;
	var yifahuo=getExecutedOrder(3);
	elementList[3].innerHTML=yifahuo;
	var yiwancheng=getExecutedOrder(4);
	elementList[5].innerHTML=yiwancheng;
	
	elementList[7].innerHTML=restaurant.rtname;
	elementList[9].innerHTML=restaurant.rtphone;
	
	if(restaurant.rtstate==1)elementList[11].innerHTML="营业中";
	if(restaurant.rtstate==0)elementList[11].innerHTML="休息中";
	elementList[13].innerHTML=restaurant.region.rname;
	elementList[15].innerHTML=restaurant.restaurantCategory.rcname;
	elementList[17].innerHTML=restaurant.rtdistribution;
	
	
	var countDishCategory=getCountDishCategoryByIdString();
	elementList[19].innerHTML=countDishCategory;
	var countOfRestaDish=getCountOfRestaDishAllById();
	elementList[21].innerHTML=countOfRestaDish;
	var SumOfOtotalPrice=getSumOfOtotalPrice();
	elementList[23].innerHTML=SumOfOtotalPrice;
	
	elementList[25].innerHTML=restaurant.rtlng;
	elementList[27].innerHTML=restaurant.rtlat;
	
	//留言点评
	//elementList[29].innerHTML=restaurant.rtlat;
	
	
	elementList[29].innerHTML=restaurant.rtnotice;
}








//得到指定参数订单数
function getExecutedOrder(ostate){
	var i=0;
	 $.ajax({
		 url:project+"/Orders/getExecutedOrder?rtid="+restaurant.rtid+"&ostate="+ostate,
		 async: false,
		 success : function(data){
			 i=data; 
			// alert(ostate+":"+data);
		 }
	});
	 return i;
}
//得到分类数
function getCountDishCategoryByIdString(){
	var i=0;
	 $.ajax({
		 url:project+"/Dish/getCountDishCategoryByIdString?rtid="+restaurant.rtid,
		 async: false,
		 success : function(data){
			 i=data; 
			// alert("getCountDishCategoryByIdString:"+data);
		 }
	});
	 return i;
}
//得到菜肴数
function getCountOfRestaDishAllById(){
	var i=0;
	 $.ajax({
		 url:project+"/Dish/getCountOfRestaDishAllById?rtid="+restaurant.rtid,
		 async: false,
		 success : function(data){
			 i=data; 
			// alert("getCountOfRestaDishAllById:"+data);
		 }
	});
	 return i;
}





//得到菜肴数
function getSumOfOtotalPrice(){
	var i=0;
	 $.ajax({
		 url:project+"/Orders/getSumOfOtotalPrice?rtid="+restaurant.rtid,
		 async: false,
		 success : function(data){
			 i=data; 
			// alert("getCountOfRestaDishAllById:"+data);
		 }
	});
	 return i;
}




