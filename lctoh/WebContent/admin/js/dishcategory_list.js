var project="/takeawayplatform";//项目名
var restaurant;

var _dishCategoryList;
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
			getDishCategoryByRtid(data.rtid);
		}
	},"JSON");
	
}


//得到分类信息
function getDishCategoryByRtid(rtid){
	$.post(project+"/Dish/index2",{"rtid":rtid},function(data){
		_dishCategoryList=data;
		putDishCategory(data);
	},"JSON");
}


//加载分类信息
function putDishCategory(dishCategoryList){
	
	var dishCategorybody= $("div.wrap table.list-style tbody tr");
	
	for(var i=0;i<dishCategoryList.length;i++){
		var item=$('<tr class="trbgcolor"></tr>');
		item.html("<td>"+dishCategoryList[i].dcid+"</td><td>"+dishCategoryList[i].dcname+
				"</td><td class='center'><a href='javascript:' onclick='edit(this)' title='编辑'>"+
				"<img src='images/icon_edit.gif'/></a><a id='"+dishCategoryList[i].dcid+
				"' href='javascript:' onclick='delitecategory(this)' title='删除'><img src='images/icon_drop.gif'/></a></td>");
		dishCategorybody.eq(0).after(item);
	}
	
	
	
}

function edit(abtn){
	var row=abtn.parentElement.parentNode;
	var dcname=row.cells[1].innerHTML;
	row.cells[1].innerHTML="<input type='text' size=80  value='"+dcname+"'/>";
	row.cells[2].innerHTML="<input type='button' value='确定' onclick='queding(this)'/><button value='1' onclick='shuaxin()'>取消</button>";	
}

//读取信息
function queding(btn){
	var row=btn.parentElement.parentNode;
	var dcid=row.cells[0].innerHTML;
	var dcname=row.cells[1].firstChild.value;

	update(dcname,1,dcid);
}





//删除分类信息
function delitecategory(btn){
	
	for(var i=0;i<_dishCategoryList.length;i++){
		if(_dishCategoryList[i].dcid==btn.id){
			
			update(_dishCategoryList[i].dcname,0,_dishCategoryList[i].dcid);
		}
	}
	
	
}


//更改分类信息
function update(dcname,dcstate,dcid){
	$.post(project+"/Merchant/updateCategory",{"dcname":dcname,"dcstate":dcstate,"dcid":dcid},function(data){
	
		if(data==0){alert("操作失败")}
		else if(data==1){
			alert("操作成功")
			 shuaxin();
			
		}
	},"JSON");
	
}


function shuaxin(){
	
	setTimeout(function(){self.location=project+"/restaurant_admin/dishcategory_list.html";},1000);
}





//添加分类
function addCategory(){
	var dcname=prompt("请输入分类名称")
	if(dcname==null||dcname.length==0)return;
	
	$.post(project+"/Merchant/addCategory",{"dcname":dcname,"rtid":restaurant.rtid},function(data){
		
		if(data==0){alert("操作失败")}
		else if(data==1){
			alert("操作成功")
			 shuaxin();
		}
	},"JSON");
	
	
	
}


