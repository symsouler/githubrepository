<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/table.css" rel="stylesheet" type="text/css">
		<link href="jquery/ui/css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" type="text/css" />
		<link href="jquery/pagination/pagination.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="jquery/ui/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="jquery/ui/js/jquery.ui.datepicker-zh-CN.js"></script>
		<script type="text/javascript" src="jquery/ui/js/jquery-ui-timepicker-addon.js"></script>
		<script type="text/javascript" src="jquery/pagination/jquery.pagination.js"></script>
		<script type="text/javascript">
			$.fx.speeds._default = 1000;
			$(function(){
				//显示日历
				$(".time").datetimepicker({
					changeMonth: true,
					changeYear: true,
					showButtonPanel: true,
					showSecond: true,
					timeFormat: 'hh:mm:ss'
				});
				
				displayData(0);
				$("#pageNo").keydown(function(event){
					if(event.keyCode==13){
						displayData(this.value-1);
					}
				});
				
				$( "#dialog" ).dialog({
					autoOpen: false,
					show: "blind",
					hide: "explode"
				});

				$( "#opener" ).click(function() {
					$( "#dialog" ).dialog( "open" );
					return false;
				});
			});
			function displayData(pageNo){
				var pageSize = $("#pageSize").val();
				$.ajax({
					url:"${pageContext.request.contextPath}/buyCargo/getByPage.action",
					type:"get",
					cache:false,//也可以解决浏览器缓存问题   另一种方式是加时间戳
					data:{
						"paginationVO.pageNo":pageNo+1,
						"paginationVO.pageSize":pageSize,
						"cargoName":$("#cargoName").val(),
						"model":$("#model").val(),
						"startTime":$("#startTime").val(),
						"endTime":$("#endTime").val()
					},
					beforeSend:function(){
						$("#message").text("正在进行分页查询请稍后...");
						return true;
					},
					success:function(jsonObject){
						//清空tbody
						$("#buyCargoInfoTBody").empty();
						//id	操作时间 	操作员 	IP地址 	操作模块 	操作节点 	操作类型
						//{"total":100,"dataList":[{"id":"","time":"","operatorName":"","ip":"","module":"","node":"","type":""},{},{}]}
						if(jsonObject.total==0){
							$("#message").text("没有符合提交的记录");
						}else{
							$("#message").text("查询结果如下");
							//拼接html字符串
							var htmlString ="";
							$.each(jsonObject.dataList,function(i,n){
								htmlString+="<tr class='odd'>";
								htmlString+="<td><span class='box_table_even'><input type='checkbox' value='"+n.id+"' name='id'/> </span></td>";
								htmlString+="<td>"+n.name+"</td>";
								htmlString+="<td>"+n.barcode+"</td>";
								htmlString+="<td>"+n.buyprice+"</td>";
								htmlString+="<td>"+n.buytime+"</td>";
								htmlString+="<td>"+n.count+"</td>";
								
								htmlString+="</tr>";
							});
							
							//将上面拼接的html追加到tbody中
							$("#buyCargoInfoTBody").append(htmlString);
						}
						//翻页展示
						 $("#pagination").pagination(jsonObject.total, {// 10总记录条数
				             callback: displayData,
				             items_per_page:pageSize, //每页显示多少条数据
				             current_page:pageNo,//当前页码
				             link_to:'javascript:void(0)',//保留超链接的样式，执行js代码，但是不跳转任何资源
				             num_display_entries:5,//默认显示几个页码
				             next_text:"下一页",
				             prev_text:"上一页",
				             next_show_always:false,//如果没有下一页，按钮是否显示
				             prev_show_always:false,//如果没有上一页，按钮是否显示
				             num_edge_entries:2,//页码多的时候，用...省略
				             ellipse_text:"..."
				         });
						
						//显示总记录条数
						$("#total").text(jsonObject.total);
						//总页数
						var pageCount = jsonObject.total%pageSize==0 ? jsonObject.total/pageSize : parseInt(jsonObject.total/pageSize)+1;
						$("#pageCount").text(pageCount);
					}
				});
			}
		</script>

</head>

<body>
<div class="demo">
<div id="dialog" title="Basic dialog">
	<table border="1" width="100%">
		<tr align="center">
			<td>选择</td>
			<td>商品名</td>
			<td>标识码</td>
			<td>数量</td>
		</tr>
		<tr>
			<td><input type="radio"></td>
			<td>小当家</td>
			<td>21101</td>
			<td>500</td>
		</tr>
	</table>
</div>
</div>
<table border="0" cellpadding="0" cellspacing="0" class="table_border">
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="table_right">
			<tr>
				<td width="25" height="26" align="right"><img
					src="images/311.gif" width="16" height="15" /></td>
				<td><font style="font-size: 14px;"><strong>位置：</strong>首页&gt;超市信息管理&gt;库存管理&gt;库存信息</font></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellspacing="0" class="table_padding">
			<tr>
				<td align="center">
				<table border="0" cellpadding="0" cellspacing="0"
					class="table_border">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="table_right">
							<tr>
								<td height="26" class="font_left"></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td align="center">
						<table border="0" cellpadding="0" cellspacing="0"
							class="box_table" id="box_table">
							<thead>
								<tr>
									<td width="105" class="box_table_even">商品名称 ：</td>
									<td width="658" class="box_table_odd">
										<span class="in">
											<input type="text" id="cargoName"/> 
											<img src="images/a.gif" style="cursor: pointer;" id="opener" alt="select Operator" title="select Operator" width="16" height="16" class="refButtonClass" />
										</span>
									</td>
									<td width="268" class="box_table_even">规格类型 ：</td>
									<td width="212" class="box_table_odd">
										<select id="model">
											<option value="">--请选择--</option>
											<option value="1">小号</option>
											<option value="2">中号</option>
											<option value="3">大号</option>
										</select>
									</td>
									<td width="105" class="box_table_odd">&nbsp;</td>
								</tr>
								<tr>
				
									<td class="box_table_odd">
										<span class="font_middle">
											<input type="button" value="查询" onclick="displayData(0)"/> 
										</span>
									</td>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						</td>
					</tr>
				</table>
				<table border="0" cellpadding="0" cellspacing="0"
					class="table_border">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="table_right">
							<tr>
								<td height="26" align="right">
									<span class="font_right">
										<span id="message" style="color: red;font-size: 12px"></span>
										<!-- <input type="button" value="新增" onclick="window.location.href='buyCargo/add.action';" />  -->
										<input type="button" value="导出库存信息" onclick="window.location.href='operationLog/exportExcel.action';"/>
										<!-- <input type="button" value="删除" onclick="window.location.href='operationLog/exportCSV.action';"/> 
										<input type="button" value="修改" onclick="window.location.href='operationLog/exportCSV.action';"/>  -->
									</span>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td align="center">
								<table border="0" cellpadding="0" cellspacing="0"
									id="box_num_table2" class="box_num_table">
									<thead>
										<tr>
											
											<td><input type="checkbox" id="mainCheckbox"/></td>
											<td>商品名称</td>
											<td>条形码</td>
											<td>购入价格</td>
											<td>购入时间</td>
											<td>购入数量</td>
											
										</tr>
									</thead>
									<tbody id="buyCargoInfoTBody">
									</tbody>
								</table>
								</td>
							</tr>
							<tr>
								<td>
									<%@ include file="/WEB-INF/jsp/common/pageFoot.jsp" %>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
