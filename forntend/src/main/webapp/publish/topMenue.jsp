<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="easy" tagdir="/WEB-INF/tags" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="resources" value="${pageContext.request.contextPath}/publish"/>
<div class="top-menu">


    <ul class="left-area welcome-area">
        <li class="hello-blog"><h5>
            <c:if test="${empty sessionScope.user}">
                <a style="color: blue" href="#" data-toggle="modal" data-target="#modalLoginForm">Sign In</a>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <a style="color: blue" href="${context}/log/out">Sign Out</a>
            </c:if>
        </h5></li>
    </ul><!-- left-area -->


    <div class="right-area">

        <div class="src-area">
            <form action="post">
                <input class="src-input" type="text" placeholder="Search">
                <button class="src-btn" type="submit"><i class="ion-ios-search-strong"></i></button>
            </form>
        </div><!-- src-area -->


    </div><!-- right-area -->

</div>
<!-- top-menu -->
