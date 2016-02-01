<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="${pageContext.request.scheme }://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/">
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/table.css" rel="stylesheet" type="text/css"/>
		<link href="jquery/ui/css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="jquery/form/jquery.form.js"></script>
		<script type="text/javascript" src="jquery/ui/js/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="jquery/ui/js/jquery.ui.datepicker-zh-CN.js"></script>
		<script type="text/javascript" src="jquery/ui/js/jquery-ui-timepicker-addon.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#buyCargoForm").ajaxForm({
					beforeSubmit:function(){
						$("#message").text("正在保存请稍后...");
						
						return true;
					},
					success:function(jsonObject){
						alert(jsonObject);
						if(jsonObject.success){
							$("#message").text("保存成功");	
						}else{
							$("#message").text("保存失败");
						}
					}
				});
			
				$(".time").datetimepicker({
					changeMonth: true,
					changeYear: true,
					showButtonPanel: true,
					showSecond: true,
					timeFormat: 'hh:mm:ss'
				});
			});
		</script>
	</head>

<body>
<form action="buyCargo/save.action" method="post" id="buyCargoForm">
<table  border="0" cellpadding="0" cellspacing="0" class="table_border">
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_right">
      <tr>
        <td width="25" height="26" align="right"><img src="images/311.gif" width="16" height="15" /></td>
        <td><font style="font-size: 12px"><strong>位置：</strong>首页&gt;进货管理&gt;新增</font></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" class="table_padding">
      <tr>
        <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td align="center"><table border="0" cellpadding="0" cellspacing="0" class="box_table" id="box_table2">
              <thead>
                <tr>
                  <td class="box_table_even">商品名称：</td>
	                  <td class="box_table_odd">
		                  <span class="in">
		                    <input type="text" name="repository.name"/>
		                  </span>
		              </td>
                  </tr>
                <tr>
                  
                  </tr>
                <tr>
                  
                </tr>
                <tr>
                  <td class="box_table_even">条形码：</td>
	                  <td class="box_table_odd">
		                  <span class="in">
		                    <input type="text" name="buyCargo.barcode"/>
		                  </span>
		              </td>
                  </tr><tr>
                  <td class="box_table_even">购入价格：</td>
	                  <td class="box_table_odd">
		                  <span class="in">
		                    <input type="text" name="buyCargo.buyprice"/>
		                  </span>
		              </td>
                  </tr>
                  <tr>
                  <td class="box_table_even">零售价：</td>
	                  <td class="box_table_odd">
		                  <span class="in">
		                    <input type="text" name="repository.sellprice"/>
		                  </span>
		              </td>
                  </tr>
                  <tr>
                  <td class="box_table_even">数量：</td>
	                  <td class="box_table_odd">
		                  <span class="in">
		                    <input type="text" name="buyCargo.count"/>
		                  </span>
		              </td>
                  </tr>
                  <tr>
                  <td class="box_table_even">生产厂商：</td>
	                  <td class="box_table_odd">
		                  <span class="in">
		                    <input type="text" name="repository.factoryname"/>
		                  </span>
		              </td>
                  </tr>
                  <tr>
                  <td class="box_table_even">规格：</td>
	                  <td class="box_table_odd">
		                  <span class="in">
		                    <input type="text" name="repository.model"/>
		                  </span>
		              </td>
                  </tr>
           			<tr>
                  <td class="box_table_even">采购日期：</td>
	                  <td class="box_table_odd">
		                  <span class="in">
		                    <input type="text" name="buyCargo.buytime" class='time'/>
		                  </span>
		              </td>
                  </tr>
                  
              </thead>
              <tbody>
              </tbody>
            </table></td>
          </tr>
          <tr><td><table width="100%" height="30" border="0" cellpadding="0" cellspacing="0" class="page_table">
              <tr>
                <td align="center" class="font_right">
                  <span id="message" style="color: red;font-size: 12px"></span>	
                  <input type="submit" value="创建" />
                  <input type="button" value="返回" onclick="javascript: window.history.back();" /></td>
                </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
  </form>
</body>
</html>
