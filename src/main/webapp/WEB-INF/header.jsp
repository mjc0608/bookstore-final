<%--
  Created by IntelliJ IDEA.
  User: Jachin
  Date: 5/8/16
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="bookstore.entity.User" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%--<meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>

    <title>iBookStore</title>

    <!-- Fonts -->
    <%--<link href="/statics/css/font-awesome.css" rel="stylesheet" type="text/css">--%>
    <%--<link href="//fonts.lug.ustc.edu.cn/css?family=Lato:100,300,400,700" rel='stylesheet' type='text/css'>--%>

    <!-- Styles -->
    <link href="/statics/css/bootstrap.min.css" rel="stylesheet">
    <script src="/statics/js/jquery.min.js"></script>
    <script src="/statics/js/jquery.reveal.js"></script>
    <link href="/statics/css/reveal.css" rel="stylesheet">

    <style>
        /*body {*/
            /*font-family: 'Lato';*/
        /*}*/

        a:link {
            text-decoration: none;
            color: #000000;
        }

        a:hover {
            text-decoration: none;
        }

        .fa-btn {
            margin-right: 6px;
        }
    </style>
</head>
<body id="app-layout">
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="navbar-header">

            <!-- Collapsed Hamburger -->
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#app-navbar-collapse">
                <span class="sr-only">Toggle Navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <!-- Branding Image -->
            <a class="navbar-brand" href="/">
                iBookStore
            </a>
            <%--<div class="navbar-text navbar-left">{{ $bookstore.bookstore.entityy or "" }}</div>--%>
        </div>

        <script type="text/javascript">
            function search() {
                var base="/search?keyword=";
                var item=document.getElementById("s_input").value;
                window.location.href=base+item;
            }
        </script>
        <div>
            <form class="navbar-form col-md-4" method="GET" action="/search">
                <div class="input-group">
                    <input name="keyword" type="text" class="form-control" placeholder="Search Book">
              <span class="input-group-btn">
                <submit class="btn btn-default" type="submit">Search</submit>
              </span>
                </div>
            </form>
        </div>

        <div class="collapse navbar-collapse" id="app-navbar-collapse">
            <!-- Left Side Of Navbar -->

            <!-- Right Side Of Navbar -->
            <ul class="nav navbar-nav navbar-right">

                <%
                    User user=(User)session.getAttribute("loginUser");
                    if (user==null) {
                %>
                        <li><a href="/login">Login</a></li>
                        <li><a href="/register">Register</a></li>
                <%
                    } else if (user.getAdmin()) {
                %>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                <%= user.getUsername()%> <span class="caret"></span>
                            </a>

                            <ul class="dropdown-menu" role="menu">
                                <li role="presentation"><a href="/cart">Cart</a></li>
                                <li role="presentation"><a href="/myorders">My Orders</a></li>
                                <li role="presentation"><a href="/info">My Info</a></li>
                                <li role="presentation" class="divider"></li>
                                <li role="presentation"><a href="/books">Books</a></li>
                                <li role="presentation"><a href="/users">Users</a></li>
                                <li role="presentation"><a href="/orders">Orders</a></li>
                                <li role="presentation"><a href="/analysis">Analysis</a> </li>
                                <li role="presentation" class="divider"></li>
                                <li role="presentation"><a href="/logout">Logout</a></li>
                            </ul>
                        </li>
                <%
                    } else {
                %>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                <%= user.getUsername()%> <span class="caret"></span>
                            </a>

                            <ul class="dropdown-menu" role="menu">
                                <li><a href="/cart">Cart</a></li>
                                <li><a href="/myorders">My Orders</a></li>
                                <li><a href="/info">My Info</a></li>
                                <li role="presentation" class="divider"></li>
                                <li><a href="/logout">Logout</a></li>
                            </ul>
                        </li>
                <%
                    }
                %>

            </ul>
        </div>
    </div>
</nav>

