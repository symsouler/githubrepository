//复选框只能选择一个
/*
 *以下属于面向过程的开发方式 
 function selectSingleCheckbox(path,Object){
	var checkedElts = $(":checkbox[name='id']:checked");
	if(checkedElts.length==0){
		$("#message").text("请选择记录");
	}else if(checkedElts.length>1){
		$("#message").text("一次只能选择一条记录");
	}else{
		window.location.href= path+"?"+Object+".id="+checkedElts.val();
	}
}*/
//面向对象  在js中定义类
//JsUtil = function(){}
function JsUtil(){}

//给上面定义的类扩展方法
JsUtil.prototype.selectSingleCheckbox = function(path,Object){
	var checkedElts = $(":checkbox[name='id']:checked");
	if(checkedElts.length==0){
		$("#message").text("请选择记录");
	}else if(checkedElts.length>1){
		$("#message").text("一次只能选择一条记录");
	}else{
		window.location.href= path+"?"+Object+".id="+checkedElts.val();
	}
};
//在该js文件被加载的时候，实例化对象出来
$_ = jsUtil = new JsUtil();






