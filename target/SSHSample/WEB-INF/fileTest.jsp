<%--
  Created by IntelliJ IDEA.
  User: Jachin
  Date: 6/8/16
  Time: 11:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%

    String path = request.getContextPath();

    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>

    <base href="<%=basePath%>">



    <title>My JSP 'fileUpLoad.jsp' starting page</title>



    <meta http-equiv="pragma" content="no-cache">

    <meta http-equiv="cache-control" content="no-cache">

    <meta http-equiv="expires" content="0">

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

    <meta http-equiv="description" content="This is my page">

    <!--

    <link rel="stylesheet" type="text/css" href="styles.css" mce_href="styles.css">

    -->



</head>



<body>


    <s:form action ="fileUpload" method ="POST" enctype ="multipart/form-data" >

        <s:fielderror />

        <s:file name ="myFile" label ="Image File1"/>

        <s:file name ="myFile" label ="Image File2"/>

        <s:file name ="myFile" label ="Image File3"/>

        <s:textfield name ="caption" label ="Caption" />

        <s:submit/>

    </s:form>


</body>

</html>