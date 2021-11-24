<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width", initial-scale="1">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/custom.css">
    <title>Beach Clean Up</title>
    <style>.carousel-inner > .item > img { width:100%; height:600px; } </style>
</head>
<body>
	<%
		String userID = null;
		if (session.getAttribute("userID") != null) {
			userID = (String) session.getAttribute("userID");
		}
	
	%>
    <nav class="navbar navbar-default">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapsed" data-target="#bs-example-navbar-collapse-1"
                aria-expanded="false">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main.jsp">Beach Clean Up in Korea</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
           <ul class="nav navbar-nav">
            <li class="active"><a href="main.jsp">Main</a></li>
            <li><a href="bbs.jsp">Board</a></li>
            </ul>
            <%
            	if(userID == null) {
            %>
            <ul class="nav navbar-nav navbar-right">
	         <li class="dropdown">
	           <a href="#" class="dropdown-toggle" 
	            data-toggle="dropdown" role="button" aria-haspopup="true" 
	            aria-expanded="false">Sing in<span class="caret"></span></a>
	        <ul class="dropdown-menu">
	              <li><a href="login.jsp">로그인</a></li>
	              <li><a href="join.jsp">회원가입</a></li>
	            </ul>    
	         </li>
	       </ul>
            <%
            	} else {
            %>
           <ul class="nav navbar-nav navbar-right">
	         <li class="dropdown">
	           <a href="#" class="dropdown-toggle" 
	            data-toggle="dropdown" role="button" aria-haspopup="true" 
	            aria-expanded="false">회원관리<span class="caret"></span></a>
	        <ul class="dropdown-menu">
	              <li><a href="logoutAction.jsp">로그아웃</a></li>
	            </ul>    
	         </li>
	       </ul>
            <%
        		}
            %>
	         
    	</div>
    </nav>
    <div class="container">
    	<div class="jumbotron">
    		<div class="container">
	    		<h2>Beach CleanUp 게시판 소개</h2>
	    		<p> 간단한 로직과 부트스트랩으로 구현한 JSP 웹 게시판으로 Beach CleanUp 관련 참가자들이 게시판 글작성 할 수 있는 공간으로 구성하였습니다. </p>
	    		<p><a class
	    		="btn btn-primary btn-pull" href="https://www.wavetribe.com/blogs/eco/why-beach-cleanups-are-important" role="butoon">Beach CleanUp에 대해 알아보기 Click</a></p>
	    		<p> 관련 영상 보기 <br>
	    		<iframe width="560" height="315" src="https://www.youtube.com/embed/WkP2kqN1st0" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></p>
    		</div>
    	</div>
    </div>
    <div class="container">
    	<div id="myCarousel" class="carousel slide" data-ride="carousel">
    		<ol class="carousel-indicators">
    			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    			<li data-target="#myCarousel" data-slide-to="1"></li>
    			<li data-target="#myCarousel" data-slide-to="2"></li>
    		</ol>
    		<div class="carousel-inner">
    			<div class="item active">
    				<img src="images/1.jpg">
    			</div>
    			<div class="item">
    				<img src="images/2.jpg">
    			</div>
    			<div class="item">
    				<img src="images/3.jpg">
    			</div>
    		</div>
    			<a class="left carousel-control" href="#myCarousel" data-slide="prev">
    				<span class="glyphicon glyphicon-chevron-left"></span>
    			</a>
    			<a class="right carousel-control" href="#myCarousel" data-slide="next">
    				<span class="glyphicon glyphicon-chevron-right"></span>
    			</a>
    		</div>
  		</div>
  		
  	<footer class="text-center">
      <div class="container">
        <p>@ 2021, Beach Clean Up Board by Carol</p>
      </div>
    </footer>
  		
   
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.js"></script>

</body>
</html>