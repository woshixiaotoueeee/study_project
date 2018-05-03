//商家菜肴管理页面
var restaurant;
var dishcategoryList;
$(function(){
	getLogininfo();
	
    
})
function init_dishView(){ //初始化商家菜肴信息
   initdishcategory();
	
	add_dishType();//添加菜肴类别函数
    edit_dishType();//编辑菜肴类别函数     当菜肴类别名为空时就是删除这个菜肴类别
    
    add_dishes();//添加菜肴产品
    edit_dishes();//编辑菜肴产品
}
function initdish(dcid){
	var dishdiv=$('.product_list');
	var str='';
	var item;
	for(var i=0;i<dishcategoryList.length;i++){
		if(dcid==dishcategoryList[i].dishCategoryId){
			var dishlist=dishcategoryList[i].dishList;
			$('#dishes_num').html(dishlist.length);
			for(var j=0;j<dishlist.length;j++){
				item=dishlist[j];
				str+="<div class='product_one'><div class='img_dish' ><img src='../restaurant"+item.dishImage+"'></div>" +
					"<div class='dish_name'><span class='name'>"+item.dishName+"</span><span>"+item.dishPrice.toFixed(2)+"￥</span>" +
					"</div><div class='dish_scroe'><p>"+item.dishIntro+"</p></div><div class='dish_sold'><span>已卖"+item.salesVolume+"份</span>" +
					"<span class='edit_dishes' id='"+item.dishId+"'>编辑菜品</span></div></div>";
               
			}
		}
	}
	
	str+="<div class='product_one last_add' ><div class='img_dish'><img src='./images/pic.png'></div>" +
			"<div class='dish_name'><span class='name'>菜品名称</span><span>单价</span></div><div class='dish_scroe'>" +
			"<p class='add_p'>菜品描述....</p></div><div class='dish_sold dish'><span>已卖份数统计</span>" +
			"<span class='add_dishes' id='add_dishes'>添加菜品</span></div></div>"
	dishdiv.html(str);
	add_dishes();//添加菜肴产品
    edit_dishes();//编辑菜肴产品
}

function initdishcategory(){
	var dishcategorydic=$('.dish_list');
	var str='';
	var item;
	var dishes_num=0;
	for(var i=0;i<dishcategoryList.length;i++){
		if(dishcategoryList[i].dishList!=null){
			dishes_num+=dishcategoryList[i].dishList.length;
		}
		item=dishcategoryList[i];
		if(i==0)initdish(item.dishCategoryId);
		
		str+="<div class='dish_one' id='"+item.dishCategoryId+"'><span>"+item.dishCategoryName+"</span>" +
			"<div class='img_edit' dcname='"+item.dishCategoryName+"' dcid='"+item.dishCategoryId+"'><img src='./images/write_1.png' alt=''></div></div>";
	}
	
	
	$(".dishes_num span").html(dishes_num);
	dishcategorydic.html(str);
	$(".dish_one").click(function(){
		initdish($(this)[0].id);
	})
}


function getLogininfo(){
	$.ajax({
		   type: "post",
		   data:null,
		   url: Common.getRestaurantLoginInfo,
		   dataType: "json",
		   async: false,
		   success:function(data){
			   if(data.state==1){
				   restaurant=data.responseInfo;
				   dishcategoryList=restaurant.dishCategory;
				   init_dishView();
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){
			 //  layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		   }
		})
}
function add_dishType(){//添加菜肴类别函数
	//点击添加按钮  跳出添加菜肴类别添加页面
	$("#add_dishType").unbind("click"); 
	$('#add_dishType').click(function(){
		
		var addDishType=`<div class='edit_dish_type'>
	    <input class='dishCategoryName' type='text' value=''/>
	    <input class='btn_dish_type' type='button' value='确认'/>
		</div>`;
		var layerIndex=layer.open({
			  title: ['添加菜肴类别', 'font-size:16px;'],
			  type: 1,
			  area: ['244px', '160px'], //宽高
			  content: addDishType
		});	
		
		
		$(".btn_dish_type").click(function(){
			var dishCategoryName=$(".dishCategoryName").val();
			$.ajax({
				   type: "post",
				   data:{'dishCategoryName':dishCategoryName},
				   url: Common.addDishCategory,
				   dataType: "json",
				   success:function(data){
					   layer.msg(data.responseInfo, {time:2500});
					   layer.close(layerIndex);
					   if(data.state==1){
						   getLogininfo();
					   }
				   },
				   error:function(errordate){
					   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
					   layer.close(layerIndex);
				   }
				})
		})
	})
	
	
}
function edit_dishType(){//编辑菜肴类别函数  包含删除菜肴类别
	$('.cont_left .dish_list').find('.img_edit').unbind("click"); 
	$('.cont_left .dish_list').find('.img_edit').click(function(){
		var id=$(this).attr("dcid");
		var dcname=$(this).attr("dcname");
		
		var editDishType=`<div class='edit_dish_type'>
		    <input type='text' id='dcid' value='#dcname#'/>
		    <input class='btn_dish_type' type='button' value='确认'/>
		</div>`;
		
		editDishType=editDishType.replace("#dcname#",dcname);
		
		var layerIndex=layer.open({
			  title: ['编辑菜肴类别', 'font-size:16px;'],
			  type: 1,
			  area: ['244px', '160px'], //宽高
			  content: editDishType
		});	
		
		$(".btn_dish_type").click(function(){
			var dishCategoryName=$("#dcid").val();
			if(dishCategoryName!=null&&dishCategoryName!=''){
				$.ajax({
					   type: "post",
					   data:{'dishCategoryId':id,'dishCategoryName':dishCategoryName},
					   url: Common.updateDishCategory,
					   dataType: "json",
					   success:function(data){
						   layer.msg(data.responseInfo, {time:2500});
						   layer.close(layerIndex);
						   if(data.state==1){
							   getLogininfo();
						   }
					   },
					   error:function(errordate){
						   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
						   layer.close(layerIndex);
					   }
					})
			}else{
				$.ajax({
					   type: "post",
					   data:{'dishCategoryId':id},
					   url: Common.deleteDishCategory,
					   dataType: "json",
					   success:function(data){
						   layer.msg(data.responseInfo, {time:2500});
						   layer.close(layerIndex);
						   if(data.state==1){
							   getLogininfo();
						   }
					   },
					   error:function(errordate){
						   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
						   layer.close(layerIndex);
					   }
					})
			}
				
		})
	})
	
}
function add_dishes(){//添加菜肴产品
	var addDishes=`<div class='edit_dishes'>
	<form id='add_dish'>
	    <div class='lay_infor'>
		   <span>菜品名称</span>
		   <input id='dishes_name' name='dishName' type='text' placeholder="请输入菜品名称" value=''/>	
	    </div>	
	    <div class='lay_infor sex_infor'>
	       <span>菜品价格(￥)</span>
	       <input id='dishes_price' name='dishPrice' type='text' placeholder="请输入菜品价格" value=''/>
	    </div>
	    <div class='lay_infor'>
		   <span>菜品缩略图</span>
		   <div class='input_flie'><div class='img_dishes'><img /></div><input name='file' type='file' /></div>  	
	    </div>
	    <div class='lay_infor'>
		   <span>菜品分类</span>
		   <div class='select_list'>
		      <select id="dish_type">
                 <option>请选择菜肴类别</option>
              </select>
              <input id='_dishCategoryId'  name='dishCategoryId' type='hidden' />
           </div>  	
	    </div>
	    <div class='lay_infor lay_brief'>
		   <span>菜品简介</span>
		   <div class='dishes_brief'><textarea id='_dishes_brief'></textarea></div>    
		   <input id='_dishIntro'  name='dishIntro' type='hidden' />
		</div>
	    <div class='lay_infor save_cancel'>
		    <input type='button' class='add_save' value='添加菜品'/>
		    <input type='button' class='add_cancel' value='取消'/>
	    </div>	
	  </form>
	</div>`;
	$("#add_dishes").unbind("click"); 
	$('#add_dishes').click(function(){
		var layerIndex=layer.open({
			  title: ['添加菜肴菜品', 'font-size:16px;'],
			  type: 1,
			  area: ['544px', '500px'], //宽高
			  content: addDishes
		});	
		//获取菜肴类别列表数据
		/* var dishList = ['蛋炒饭','盖浇饭','饮料','烧烤'];
		 
		 for(i in dishList){
			 $('#dish_type ').append('<option>'+dishList[i]+'</option>'); 
		  }*/
		
		 for(var i=0;i<dishcategoryList.length;i++){
			 $('#dish_type ').append('<option value="'+dishcategoryList[i].dishCategoryId+'">'+dishcategoryList[i].dishCategoryName+'</option>'); 
		 }
		 
		 
		 $('.add_save').click(function(){
			 $("#_dishCategoryId").val($('#dish_type').val());
				$("#_dishIntro").val($('#_dishes_brief').val()); 
				
				
				var dishInfo = new FormData($("#add_dish")[0]);
				 
				$.ajax({  
			         url: Common.addDish,  
			         type: 'POST',  
			         data: dishInfo, 
			         dataType: "json",
			         cache: false,  
			         contentType: false,  
			         processData: false, 
			         success: function (returndata) {  
			        	 layer.msg(returndata.responseInfo, {time:2500});
			        	 layer.close(layerIndex);
			        	 getLogininfo();
			        	 
			         },  
			         error: function (returndata) {  
			        	 layer.msg('文件过大或文件格式不对', {time:2500}); 
			        	 layer.close(layerIndex);
			         }  
			    });
		 });
		 
		 $('.add_cancel').click(function (){
			 layer.close(layerIndex);
		 });
	})
}
function edit_dishes(){//编辑菜肴产品
	var editDishes=`<div class='edit_dishes'>
	<form id='update_dish'>
    <div class='lay_infor'>
    
    <input id='_dishId'  name='dishId' type='hidden' value='#dishId#'/>
    		<span>菜品名称</span>
		   <input id='dishes_name' name='dishName' type='text'  value='#dishName#'/>	
	    </div>	
	    <div class='lay_infor sex_infor'>
	       <span>菜品价格(￥)</span>
		   <input id='dishes_price' name='dishPrice' type='text' value='#dishPrice#'/>
	    </div>
	    <div class='lay_infor'>
		   <span>菜品缩略图</span>
		   <div class='input_flie'><div class='img_dishes'><img id='_dishImage' src='#dishImage#'/></div><input name='file' type='file' /></div>  	
	    </div>
	    <div class='lay_infor'>
		   <span>菜品分类</span>
		   <div class='select_list'>
		      <select id="dish_type">
                 <option>请选择菜肴类别</option>
              </select>
              <input id='_dishCategoryId'  name='dishCategoryId' type='hidden' />
           </div>  	
	    </div>
	    <div class='lay_infor lay_brief'>
		   <span>菜品简介</span>
		   <div class='dishes_brief'><textarea id='_dishes_brief'>菜品由最新鲜的材料及最美味的调料料理而成</textarea></div>    
		   
		   <input id='_dishIntro'  name='dishIntro' type='hidden'/>
		</div>
	    <div class='lay_infor save_cancel'>
		    <input type='button' class='add_save' value='保存'/>
		    <input type='button' class='add_cancel' value='取消'/>
	    </div>	
	    </form>
	</div>`;
	
	$(".product_list .edit_dishes").unbind("click"); 
	$('.product_list .edit_dishes').click(function(){
		var layerIndex=layer.open({
			  title: ['编辑菜肴菜品', 'font-size:16px;'],
			  type: 1,
			  area: ['544px', '500px'], //宽高
			  content: editDishes
		});	
		
		
		
		var id=$(this)[0].id;
		
		$('#_dishId').val(id);
		
		for(var i=0;i<dishcategoryList.length;i++){
			 $('#dish_type ').append('<option value="'+dishcategoryList[i].dishCategoryId+'">'+dishcategoryList[i].dishCategoryName+'</option>'); 
			 var dishlist=dishcategoryList[i].dishList;
			 for(var j=0;j<dishlist.length;j++){
				 if(id==dishlist[j].dishId){
					 $('#dishes_name').val(dishlist[j].dishName);
					 $('#dishes_price').val(dishlist[j].dishPrice);
					 
					 $('#_dishImage').attr('src','../restaurant'+dishlist[j].dishImage);
					 $('#_dishes_brief').val(dishlist[j].dishIntro);
				 }
			 }
		 }
		 
		 $('.add_save').click(function(){
			 $("#_dishCategoryId").val($('#dish_type').val());
				$("#_dishIntro").val($('#_dishes_brief').val()); 
				var dishInfo = new FormData($("#update_dish")[0]);
				
				$.ajax({  
			         url: Common.updateDish,  
			         type: 'POST',  
			         data: dishInfo, 
			         dataType: "json",
			         cache: false,  
			         contentType: false,  
			         processData: false, 
			         success: function (returndata) {  
			        	 layer.msg(returndata.responseInfo, {time:2500});
			        	 layer.close(layerIndex);
			        	 getLogininfo();
			        	 
			         },  
			         error: function (returndata) {  
			        	 layer.msg('文件过大或文件格式不对', {time:2500}); 
			        	 layer.close(layerIndex);
			         }  
			    });
		 });
		 
		 $('.add_cancel').click(function (){
			 layer.close(layerIndex);
		 });
		
	})
	
}
