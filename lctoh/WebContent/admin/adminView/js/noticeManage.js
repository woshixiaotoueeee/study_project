//公告管理页面
var notice;

$(function(){ 
	//初始化公告数据
	init_notice();
    //操作公告事件
	operation_notice();
    
 })
 function init_notice(){ //初始化公告数据
	
}
function operation_notice(){//操作公告事件
	//修改
	$(".notice_detaile .notice_modify").unbind("click");
	$('.notice_detaile .notice_modify').click(function(){
		
		var strNotice=`<form id='noticeForm'><div class='edit_notice'>
		    <div class='lay_infor'>
			   <span>公告标题</span>
			   <input id='noticeNickname' type='text' placeholder="" value='联创公告1'/>	
		    </div>	
		    <div class='lay_infor'>
			   <span>公告类型</span>
			   <input  type='text' placeholder="" value='商家公告'/>	
		    </div>	    
		    <div class='lay_infor'>
			   <span>公告内容</span>
			   <div class='notice_cont'><textarea id='notice_cont'>最近推出新的优惠策略</textarea></div> 	
		    </div>
		    <div class='lay_infor save_cancel'>
			    <input type='button' class='edit_save' value='保存'/>
			    <input type='button' class='edit_cancel' value='取消'/>
		    </div>			   
		</div></form>`;	
		var layer_infor=layer.open({
		  title: ['修改公告信息', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '360px'], //宽高
		  content: strNotice
		});
		rider_save_cancel(layer_infor); //公告的保存取消函数
					
	})
	//删除
	$(".notice_detaile .notice_delete").unbind("click");
	$('.notice_detaile .notice_delete').click(function(){
		layer.confirm('是否确认删除？', {
			  btn: ['确认','取消'],//按钮
			  shade: 0.2,
				}, function(){
					
					layer.msg('删除成功', {
					    time: 1000, //1s后自动关闭
					});
				  
				}, function(){
				    
				});
	})
	//查看
	$(".notice_detaile .notice_check").unbind("click");
	$('.notice_detaile .notice_check').click(function(){
		layer.open({
			  type: 2,
			  title: ['公告详情','font-size:18px;'],
			  shadeClose: true,
			  shade: 0.2,
			  area: ['520px', '280px'],
			  content: './noticeCheck.html'  //iframe的url
			}); 
	})
	//添加公告
	$(".add_notice img").unbind("click");
	$('.add_notice img').click(function(){
	    add_notice();
	})
}
function add_notice(){//添加公告函数
		var addNotice=`<form id='noticeForm'><div class='edit_notice'>
		    <div class='lay_infor'>
			   <span>公告标题</span>
			   <input id='noticeNickname' type='text' placeholder="请输入您的公告标题" value=''/>	
		    </div>	
		    <div class='lay_infor'>
			   <span>公告类型</span>
			   <input  type='text' placeholder="请输入公告类型" value=''/>	
		    </div>	    
		    <div class='lay_infor'>
			   <span>公告内容</span>
			   <div class='notice_cont'><textarea id='notice_cont'></textarea></div> 	
		    </div>
		    <div class='lay_infor save_cancel'>
			    <input type='button' class='edit_save' value='保存'/>
			    <input type='button' class='edit_cancel' value='取消'/>
		    </div>			   
		</div></form>`;	
		var layer_infor=layer.open({
		  title: ['添加公告', 'font-size:18px;'],
		  type: 1,
		  area: ['544px', '360px'], //宽高
		  content: addNotice
		});
		$('.edit_save').click(function(){  
		       alert('添加成功');
		      layer.close(layer_infor);
		 })
		   //点击取消按钮事件
		$('.edit_cancel').click(function(){  
		   	   //清除表单
		       $('#noticeForm')[0].reset();
		 })
}
function rider_save_cancel(layer_infor){ //公告信息的保存取消函数
	   //点击保存按钮事件
	   $('.edit_save').click(function(){  
	     alert('保存信息');
	      layer.close(layer_infor);
	   })
	   //点击取消按钮事件
	   $('.edit_cancel').click(function(){  
	   	   //清除表单
	       $('#noticeForm')[0].reset();
	   })

	}