//商家菜肴管理页面
$(function(){
     init_dishView();//初始化  渲染html
     add_dishType();//添加菜肴类别函数
     edit_dishType();//编辑菜肴类别函数     当菜肴类别名为空时就是删除这个菜肴类别
     
     add_dishes();//添加菜肴产品
     edit_dishes();//编辑菜肴产品
})
function init_dishView(){ //初始化商家菜肴信息
   

}
function add_dishType(){//添加菜肴类别函数
	//点击添加按钮  跳出添加菜肴类别添加页面
	$('#add_dishType').click(function(){
		
		var addDishType=`<div class='edit_dish_type'>
	    <input type='text' value=''/>
	    <input class='btn_dish_type' type='button' value='确认'/>
		</div>`;
		var layerIndex=layer.open({
			  title: ['添加菜肴类别', 'font-size:16px;'],
			  type: 1,
			  area: ['244px', '160px'], //宽高
			  content: addDishType
		});	
	})
}
function edit_dishType(){//编辑菜肴类别函数  包含删除菜肴类别
	$('.cont_left .dish_list').find('.img_edit').click(function(){
		
		var editDishType=`<div class='edit_dish_type'>
		    <input type='text' value='盖浇饭'/>
		    <input class='btn_dish_type' type='button' value='确认'/>
		</div>`;
		var layerIndex=layer.open({
			  title: ['编辑菜肴类别', 'font-size:16px;'],
			  type: 1,
			  area: ['244px', '160px'], //宽高
			  content: editDishType
		});	
	})
	
}
function add_dishes(){//添加菜肴产品
	var addDishes=`<div class='edit_dishes'>
	    <div class='lay_infor'>
		   <span>菜品名称</span>
		   <input id='dishes_name' type='text' placeholder="请输入菜品名称" value=''/>	
	    </div>	
	    <div class='lay_infor sex_infor'>
	       <span>菜品价格</span>
		   <input id='dishes_price' type='text' placeholder="请输入菜品价格" value=''/>
	    </div>
	    <div class='lay_infor'>
		   <span>菜品缩略图</span>
		   <div class='input_flie'><div class='img_dishes'><img /></div><input type='file' /></div>  	
	    </div>
	    <div class='lay_infor lay_brief'>
		   <span>菜品简介</span>
		   <div class='dishes_brief'><textarea id='dishes_brief'></textarea></div>    
	    </div>
	    <div class='lay_infor save_cancel'>
		    <input type='button' class='add_save' value='添加菜品'/>
		    <input type='button' class='add_cancel' value='取消'/>
	    </div>			   
	</div>`;
	$('#add_dishes').click(function(){
		var layerIndex=layer.open({
			  title: ['添加菜肴菜品', 'font-size:16px;'],
			  type: 1,
			  area: ['544px', '460px'], //宽高
			  content: addDishes
		});	
	})
	
	
}
function edit_dishes(){//编辑菜肴产品
	var editDishes=`<div class='edit_dishes'>
    <div class='lay_infor'>
		   <span>菜品名称</span>
		   <input id='dishes_name' type='text'  value='蛋炒饭'/>	
	    </div>	
	    <div class='lay_infor sex_infor'>
	       <span>菜品价格</span>
		   <input id='dishes_price' type='text' value='23￥'/>
	    </div>
	    <div class='lay_infor'>
		   <span>菜品缩略图</span>
		   <div class='input_flie'><div class='img_dishes'><img src='./images/dish/dish_one_1.png'/></div><input type='file' /></div>  	
	    </div>
	    <div class='lay_infor lay_brief'>
		   <span>菜品简介</span>
		   <div class='dishes_brief'><textarea id='dishes_brief'>菜品由最新鲜的材料及最美味的调料料理而成</textarea></div>    
	    </div>
	    <div class='lay_infor save_cancel'>
		    <input type='button' class='add_save' value='保存'/>
		    <input type='button' class='add_cancel' value='取消'/>
	    </div>			   
	</div>`;
	$('.product_list .edit_dishes').click(function(){
		var layerIndex=layer.open({
			  title: ['编辑菜肴菜品', 'font-size:16px;'],
			  type: 1,
			  area: ['544px', '460px'], //宽高
			  content: editDishes
		});	
	})
	
}
