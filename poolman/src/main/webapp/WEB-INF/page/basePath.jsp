
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" +request.getServerName() + ":" + request.getServerPort() + path + "/";
	   String resourcePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/resource";
    String guldanPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/resource/Guldan";
%>
<base href="<%=basePath%>"/>
<base href="<%=resourcePath%>"/>
<base href="<%=guldanPath%>"/>

