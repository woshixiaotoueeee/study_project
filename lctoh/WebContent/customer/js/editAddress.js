//添加新地址页面
var map = new BMap.Map();//初始化地图
var address;
$(function(){
	getAddress(GetQueryString("addressId"));
	
	
	init_add_address();//初始化新添地址页面
	
	//保存地址函数
	save_address();
	//取消添加地址函数
	cancel_address();
})
function getAddress(addressId){
	$.ajax({
		   type: "post",
		   url:Common.findAddressByAddressId,
		   data:{'addressId':addressId}, 
		   dataType: "json",
		   async: false,
		   success:function(data){
			   if(data.state==1){
				   address=data.responseInfo;
				   initaddress(_address);
			   }else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){
			   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
			   parent.layer.closeAll();
		   }
		})
}
function initaddress(_address){
	
	$('#addressName').val(_address.addressName);
	$('#suggestId').val(_address.addressInfo);
	$('#addressPhone').val(_address.addressPhone);
}




function init_add_address(){
	//
	
	//加载地图数据选择地址列表
	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
			{"input" : "suggestId"
			,"location" : map
		});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
		var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		$("#searchResultPanel").html(str);
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
		var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		$("#searchResultPanel").html("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);
		
		
		
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			
			address.addressLatitude=pp.lat;
			address.addressLongitude=pp.lng;
			
			var geoc = new BMap.Geocoder();    
			geoc.getLocation(pp, function(rs){
				var addComp = rs.addressComponents;
				address.addressProvince=addComp.province;
				address.addressCity=addComp.city;
				
			}); 
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	});
}
function save_address(){ 
	//添加地址
	$('.edit_save').click(function(){
		address.addressName=$('#addressName').val();
		address.addressInfo=$('#suggestId').val();
		address.addressPhone=$('#addressPhone').val();
		
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			
			address.addressLatitude=pp.lat;
			address.addressLongitude=pp.lng;
			
			var geoc = new BMap.Geocoder();    
			geoc.getLocation(pp, function(rs){
				var addComp = rs.addressComponents;
				address.addressProvince=addComp.province;
				address.addressCity=addComp.city;
				
			}); 
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(address.addressInfo);
		$.ajax({
			   type: "post",
			   url:Common.updateAddress,
			   data:address, 
			   dataType: "json",
			   success:function(data){
					layer.msg(data.responseInfo, {time:2500});
					setTimeout(function(){
						parent.layer.closeAll();
					},2500);
			   },
			   error:function(errordate){
				   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
				   parent.layer.closeAll();
			   }
			})
	})
	
}
function cancel_address(){//取消添加地址
	$('.edit_cancel').click(function(){
		parent.layer.closeAll();
	})
}
