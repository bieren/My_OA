<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用培训中心管理系统</title>

<link rel="stylesheet" type="text/css" href="${ctp }css/login.css">
<script type="text/javascript" src="${ctp }script/jquery.min.js"></script>
<script type="text/javascript">
	/*
	 * 前端基于 JS 的验证.
	 * 
	 * ①: loginName 和 password 字段除去前后空格不能为空
	 * ②: loginName 和 password 字段除去前后空格, 不能少于 6 个字符
	 * ③: loginName 中不能包含特殊字符, 即以字母开头, 后边还可以包含数字和_ 
	 *
	 * 其中前两个验证都通过, 再验证 ③. 
	 */

	/* $(function() {
		$(".submit").click(function() {
			
			var flag=true;
			$("input:not(.submit)").each(function() {
				var val=this.value;
				//1、去除空格
				
				val=$.trim(val);
				this.value=val;
				
				//////////////////////
				
				//除去前后空格不能为空
				var label=$(this).prev("b").text();//$(this).prev("b")找到每个段落紧邻的前一个同辈元素中标签名为b的元素。


				if(val==""){
					alert(label+"不能为空");
					flag=false;
				}else {
					if(val.length<6){
					alert(label+"不能少于6个字符");
					flag=false;
					}
				}
			});
			if(flag){
				var reg=/^[a-zA-Z]\w+\w$/g;
				var loginName=$("input[name='loginName']").val();
				loginName=$.trim(loginName);
				if(!reg.test(loginName)){
					alert("输入的用户名不合法:用户名只能以字母开头, 后边可以包含数字和_");
				}
			}
			//取消默认行为
			return false;
		});
	}) 
	 */
</script>
</head>
<body>

	<div align="center">
		<h1>
			index!! 现在是:<%=new Date()%></h1>
		<s:form action="/emp-login" method="post">
			<div class="login_div" align="center">

				<font color="red"></font> 
				
				<b>用户名</b>
				<s:textfield name="loginName" />
				<font color="red"> 
					<s:fielderror fieldName="loginName" />
				</font> 
				
				<b>密码</b>
				<s:password name="password" />
				<font color="red"> 
				<s:fielderror fieldName="password" />
				</font> 
				<input type="submit" class="submit" value="登录" />
			</div>
		</s:form>
	</div>
</body>
</html>