<%@page import="org.springframework.web.context.request.RequestScope"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <s:url value="/" var="ctp"></s:url> --%>

<%
	//可避免页面出现jsessionid=FA337775A57D2654A834806EDCB9C718引起的资源第一次加载失败
	String contextPath = request.getContextPath();
	request.setAttribute("ctp", contextPath+"/");


%>