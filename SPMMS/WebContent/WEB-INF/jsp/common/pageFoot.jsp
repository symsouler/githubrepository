<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<table width="100%" height="30" border="0" cellpadding="0"
	cellspacing="0" class="page_table">
	<tr>
		<td width="8%" class="font_left">
			数据:<span id="total"></span>条
		</td>
		<td width="12" class="font_left">
			第
		</td>
		<td width="375" class="font_left">
			<input name="textfield2" id="pageNo" type="text" size="2" maxlength="4" />
			/<span id="pageCount"></span>页
			<input type="image" border="0"  src="images/go.gif" onclick="displayData($('#pageNo').val()-1)" />
			 <select id="pageSize" onchange="displayData(0)">
			 	<c:forTokens items="${initParam.pageSizeString}" delims="," var="pageSize">
			 		<option value="${pageSize }">每页${pageSize }条</option>
			 	</c:forTokens>
			 </select>
		</td>
		<td width="478" class="font_right">
			<div id="pagination"></div> 
			&nbsp;
			<input type="image" class="font_right"
				onclick="displayData(0)"
				src="images/botton_page_refresh.png"
				 border="0" />
		</td>
	</tr>
</table>