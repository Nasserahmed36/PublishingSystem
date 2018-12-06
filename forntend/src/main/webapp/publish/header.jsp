<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="easy" tagdir="/WEB-INF/tags" %>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<c:set var="resources" value="${pageContext.request.contextPath}/publish"/>


<header>

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

    <div class="middle-menu center-text"><a href="${context}/home/main" class="logo"><img src="${resources}/images/atypon.jpg"
                                                                       alt="Logo Image"></a>
    </div>

    <div class="bottom-area">

        <div class="menu-nav-icon" data-nav-menu="#main-menu"><i class="ion-navicon"></i></div>

        <ul class="main-menu visible-on-click" id="main-menu">
            <li class="drop-down"><a href="homePage.html">HOME<i class="ion-ios-arrow-down"></i></a>

                <ul class="drop-down-menu">
                    <li><a href="#">FEATURED</a></li>
                    <li><a href="#">ABOUT</a></li>
                    <li class="drop-down"><a href="#!">CATEGORIES<i class="ion-ios-arrow-right"></i></a>
                        <ul class="drop-down-menu drop-down-inner">
                            <li><a href="#">FEATURED</a></li>
                            <li><a href="#">ABOUT</a></li>
                            <li><a href="#">CATEGORIES</a></li>
                        </ul>
                    </li>
                </ul>

            </li>
            <li><a href="#">FEATURED</a></li>
            <li><a href="#" class="">ABOUT</a></li>
            <li><a href="#">CATEGORIES</a></li>
            <li><a href="04-Contact.html">CONTACT</a></li>
        </ul><!-- main-menu -->

    </div><!-- conatiner -->
</header>