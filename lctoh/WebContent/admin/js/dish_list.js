var project="/takeawayplatform";//项目名
var restaurant;
var _dishList;
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


//得到分类和菜肴信息
function getDishCategoryByRtid(rtid){

	$.post(project+"/Dish/index4",{"rtid":rtid},function(data){
		_dishList=data;
		putDishList(data)
	},"JSON");
}

//加载菜肴信息到页面
function putDishList(dishList){
	
	
	var dishtable=$("div.wrap table.list-style tbody tr");

	for(var i=0;i<dishList.length;i++){
		var dishitem=$('<tr class="trbgcolor"></tr>');
		dishitem.html("<td>"+dishList[i].did+"</td>"+
				"<td class='center pic-area' ><img src='"+project+"/information/"+dishList[i].dimage+"' class='thumbnail'></td>"+
				"<td class='ellipsis td-name block'>"+dishList[i].dintro+"</td>"+
				"<td class='center'><span><i>￥</i><em>"+dishList[i].dprice+"</em></span></td>"+
				"<td class='center'>"+dishList[i].dname+"</td>"+
				"<td class='center'><a href='edit_dish.html?did="+dishList[i].did+"' title='编辑'><img src='images/icon_edit.gif'/></a><a href='javascript:' onclick='deleteDish(this)' title='删除'><img src='images/icon_drop.gif'/></a></td>"
				);
		
		dishtable.eq(0).after(dishitem);
	}
	
	
	
}



//删除菜肴
function deleteDish(btn){
	var row=btn.parentElement.parentNode;
	updateDishState(row.cells[0].innerHTML,0);
	
}
//改变菜肴状态
function updateDishState(did,dstate){
	
	
	$.post(project+"/Merchant/updateDishDstate",{"did":did,"dstate":dstate},function(data){
		
		if(data==null||data==0){alert("操作失败")}
		else if(data==1){
			alert("操作成功")
			 shuaxin();
		}
	},"JSON");
}







function shuaxin(){
	
	setTimeout(function(){self.location=project+"/restaurant_admin/dish_list.html";},1000);
}



