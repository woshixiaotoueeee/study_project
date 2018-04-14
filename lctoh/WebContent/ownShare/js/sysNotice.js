

window.onload=function(){
	var type=GetQueryString("type");
	if(type==null){
		 layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		 return;
	}else{
		getsetNotice(type)
	}
}

function getsetNotice(type){
	$.ajax({
		   type: "post",
		   data:{"stateId":type},
		   url:Common.findNoticeByStateId,
		   dataType: "json",
		   success:function(data){
			   if(data.state==1){
				   setNoticeToHtml(data.responseInfo);
			   }
			   else{
				   layer.msg(data.responseInfo, {time:2500});
			   }
		   },
		   error:function(errordate){
			   layer.msg('未知错误请刷新页面或联系管理员', {time:2500});
		   }
		})
}
function setNoticeToHtml(noticeList){
	var noticeInfor=$(".notice_infor");
	noticeInfor.html('');
	var html='';
	var datestr;
	var date;
	for(var i=0;i<noticeList.length;i++){
		date=new Date(noticeList[i].noticeUpdateTime);
		datastr=date.getFullYear()+'.'+date.getMonth()+'.'+date.getDate(); 
		html+="<div class='notice_one'><label>"+noticeList[i].noticeTitle+"</label>" +
				"<p>"+noticeList[i].notiveDetails+"</p><span>"+datastr+"</span></div>";
	}
	noticeInfor.html(html);
}
