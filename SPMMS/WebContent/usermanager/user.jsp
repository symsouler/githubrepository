<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<html>
<head>


</head>
<body style="overflow: scroll; overflow-y: hidden">
		<div>


			<div id="main">
				<!--main_top-->
				<div class="user_manager">
					<p>
						用户管理
						<span style="float: right;margin-right: 20px">
						<input type="button" value="添加" onclick="document.location='./userAdd.html'" style="width: 54px; height: 20px; margin-left: 15px;">
						<input type="button" value="修改" onclick="document.location='./userEdit.html'"  style="width: 54px; height: 20px; margin-left: 15px;">
						<input type="button" value="删除" onclick="confirm('确定要删除吗')" style="width: 54px; height: 20px; margin-left: 15px;">
						</span>
					</p>
				</div>
				<!--main_bottom-->

				<div class="main_bottom">
					
					<table width="100%" class="table" cellspacing="1"
						style="background: #BAC2CF; font-size: 14px;">
						<tr
							style="background: #F6F6F6; border: 0; text-align: center; line-height: 25px;">
							<td width="6%" height="26" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"><input type="checkbox" name="checkbox62" value="checkbox" /></div></td>
							
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								编号
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								用户代码
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								用户名称
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								邮箱
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								电话
							</td>
							<td
								style="color: #2B67D7; font-size: 14px; text-align: center; font-weight: bold;">
								时间
							</td>


						</tr>


			
						<tr
							style="background: #FFFFFF; border: 0; text-align: center; line-height: 25px;">
							<td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
              <input name="checkbox" type="checkbox" class="STYLE2" value="checkbox" />
            </div></td>
							
							<td style="color: #666666; font-size: 14px;">
								1
							</td>
							<td style="color: #666666; font-size: 14px; text-align: center;">
								zhansan
							</td>
							<td style="color: #666666; font-size: 14px; text-align: center;">
								张三
							</td>
							<td style="color: #666666; font-size: 14px; text-align: center;">
								zhangsan@163.com
							</td>
							<td style="color: #666666; font-size: 14px; text-align: center;">
								18610241888
							</td>
							<td style="color: #666666; font-size: 14px; text-align: center;">

								2016-06-16
							</td>
						</tr>

</body>
</html>