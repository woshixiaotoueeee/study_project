window.onload = function () {
	// 百度地图API功能
	var map = new BMap.Map("allmap");
	var point = new BMap.Point(116.331398,39.897445);
	map.centerAndZoom(point,11);
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	var cityname;
	var lng ;
	var lat ;
	//自动定位
	function dingwei(){
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var mk = new BMap.Marker(r.point);
				map.addOverlay(mk);
				map.panTo(r.point);
				cityname=r.address.city;
				
				lng =r.point.lng;
				lat =r.point.lat;
				
				if(confirm('你的位置为：'+r.address.province + ", " + r.address.city + ", " + r.address.district + ", " + r.address.street + ", " +r.address.street_number +'是否确认?')){
					Loction();
				}
				
				//document.getE22lementById("body").innerHTML+=r.point.lng+','+r.point.lat;
				
				//跳转页面
				//self.location='index.jsp'; 
			}
			else {
				alert('failed'+this.getStatus());
			}        
		},{enableHighAccuracy: true})
	}
	
	//鼠标定位
	var geoc = new BMap.Geocoder();
	map.addEventListener("click", function(e){        
		var pt = e.point;
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			
			cityname=addComp.city;
			
			lng =pt.lng;
			lat =pt.lat;
			
			
			if(confirm('你的位置为：'+addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber+'是否确认?')){
				Loction();
			}
			
			//alert(pt.lng+",,"+pt.lat);
			//document.getElementById("body").innerHTML+=pt.lng+','+pt.lat;
			
		});        
	});
	
	//自动定位城市
	function myFun(result){
		var cityName = result.name;
		map.setCenter(cityName);
		//alert("当前定位城市:"+cityName);
	}
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);
	
	
};

//将定位信息发送到后台
function Loction(){
	$.post(projectDirectory+"/LocationController/setLocation",{"info":cityname,"longitude":lng,"latitude":lat},function(data){
		alert(data);
	});
}	

