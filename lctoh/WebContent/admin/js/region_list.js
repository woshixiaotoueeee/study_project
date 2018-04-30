var project="/takeawayplatform";//项目名
var restaurant;

var _RegionList;
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
			getRegionAll();
		}
	},"JSON");
	
}
//得到区域信息
function getRegionAll(){
	$.post(project+"/RestaurantController/getRegionAll",{},function(data){
		putRegion(data);
	},"JSON");
}

//将区域加载到页面
function putRegion(regionlist){
	var regionbody= $("div.wrap table.list-style tbody tr");
	for(var i=0;i<regionlist.length;i++){
		var item=$('<tr class="trbgcolor"></tr>');
		var msg="<td>"+regionlist[i].rid+"</td><td>"+regionlist[i].rname+"</td>";
		if(restaurant.region.rid!=regionlist[i].rid){
			msg+="<td class='center'><a href='javascript:' onclick='edit(this)' title='转移到该区域'><img src='images/icon_edit.gif'/></a></td>";
		}else{
			msg+="<td class='center'>这是您所在的区域</td>"
		}
		item.html(msg);	
		regionbody.eq(0).after(item);
	}
}


















function edit(abtn){
	var row=abtn.parentElement.parentNode;
	var rid=row.cells[0].innerHTML;
	$.post(project+"/RestaurantController/updateresRegion",{"rid":rid},function(data){
		if(data==0){alert("操作失败")}
		else if(data==1){
			alert("操作成功")
			 shuaxin();
		}
	},"JSON");
}







function shuaxin(){
	
	setTimeout(function(){self.location=project+"/restaurant_admin/region_list.html";},1000);
}
