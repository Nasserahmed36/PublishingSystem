<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="easy" tagdir="/WEB-INF/tags" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <c:set var="resources" value="${pageContext.request.contextPath}/publish"/>
    <title>TITLE</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">


    <!-- Font -->

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">


    <!-- Stylesheets -->

    <link href="${resources}/common-css/bootstrap.css" rel="stylesheet">

    <link href="${resources}/common-css/ionicons.css" rel="stylesheet">

    <link href="${resources}/common-css/layerslider.css" rel="stylesheet">


    <link href="${resources}/01-homepage/css/styles.css" rel="stylesheet">

    <link href="${resources}/01-homepage/css/responsive.css" rel="stylesheet">
    <style type="text/css">

        .btn a {
            color: black;
        }

        div.subject > h4.title {
            border-bottom: #ccc 1px solid;
        }

        div.article > h4.title {
            color: #006acc;
            font-weight: bold;
        }

        div.article span.author span.name {
            font-weight: bold;
        }

        div.article a.btn {
            padding: 5px 10px;
            width: 60px;
            height: 28px;
        }

        div.issueCard {
            width: 16rem;
            margin-bottom: 50px;
        }

        div.issueCard {
            cursor: pointer;
            transition: all .2s;
        }

        div.issueCard:hover {
            transform: scale(1.1);
        }

        div.issueCard div.card-body {
            border: 1px solid rgba(0, 0, 0, .125);
            padding: 20px;
        }
    </style>

</head>
<form method="post" action="${context}/log/in">
    <div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Sign in</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body mx-3">
                    <div class="md-form mb-5">
                        <i class="fa fa-envelope prefix grey-text"></i>
                        <input type="text" id="defaultForm-email" class="form-control validate" name="username">
                        <label data-error="wrong" data-success="right" for="defaultForm-email">Your email</label>
                    </div>

                    <div class="md-form mb-4">
                        <i class="fa fa-lock prefix grey-text"></i>
                        <input type="password" id="defaultForm-pass" class="form-control validate" name="password">
                        <label data-error="wrong" data-success="right" for="defaultForm-pass">Your password</label>
                    </div>

                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <button type="submit" class="btn btn-default">Login</button>
                </div>
            </div>
        </div>
    </div>
</form>
<body>

<header>

    <jsp:include page="topMenue.jsp"/>

    <div class="middle-menu center-text"><a href="#" class="logo"><img src="${resources}/images/atypon.jpg"
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
<section style="margin-top: 230px;" class="section blog-area">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-12">
                <div class="blog-posts">
                    <div class="single-post">
                        <div class="row">
                            <c:forEach items="${requestScope.subjectsMap}" var="entry">
                                <div class="subject col-lg-12 col-md-12">

                                    <h4 class="title">
                                        <b class="light-color">${entry.key}
                                        </b>
                                    </h4>
                                    <c:forEach var="article" items="${entry.value}">
                                        <div class="single-post article">
                                            <h4 class="title"><a
                                                    href="${context}/article/view/${article.doi}">${article.title}</a>
                                            </h4>
                                            <div class="authors">
                                                <c:forEach var="author" varStatus="status" items="${article.authors}">
                                                    <span class="author">
                                                    <span class="name">
                                                        ${author.givenName} ${author.surName}
                                                    </span>
                                                    <span class="degrees">
                                                            ${author.degrees}
                                                            <c:if test="${not status.last}">,</c:if>
                                                    </span>
                                                </span>
                                                </c:forEach>
                                            </div>
                                            <div class=" date"><em>First Published <easy:DateFormat
                                                    month="${article.month}"/> 16, ${article.year};
                                                pp. ${article.firstPage}-${article.lastPage}</em>
                                            </div>
                                            <div class="options title">
                                                <a class="btn category-btn"
                                                   href="${context}/article/view/${article.doi}">VIEW </a>
                                                <a class="btn category-btn" href="#">PDF</a>
                                            </div>
                                        </div>
                                        <!-- single-post -->
                                    </c:forEach>
                                </div>
                                <!-- col-sm-6 -->

                            </c:forEach>
                        </div><!-- row -->
                    </div><!-- single-post -->
                </div><!-- blog-posts -->
            </div><!-- col-lg-4 -->
            <div class="col-lg-4 col-md-12">
                <div class="sidebar-area">
                    <div class="sidebar-section src-area">

                        <form action="post">
                            <input class="src-input" type="text" placeholder="Search">
                            <button class="src-btn" type="submit"><i class="ion-ios-search-strong"></i></button>
                        </form>

                    </div><!-- sidebar-section src-area -->

                    <div class="issueCard card">
                        <img class="card-img-top" src="${resources}/images/cover.png" alt="Card image cap">
                        <div class="card-body">
                            <h6 class="title card-text">Volume 6 Issue 6, November 2018</h6>
                        </div>
                    </div>

                    <div class="sidebar-section latest-post-area">
                        <h4 class="title"><b class="light-color">Latest Articles</b></h4>

                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="${resources}/images/recent-post-1-150x200.jpg"
                                                           alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">One more night in the clubs</b></a></h5>
                                <h6 class="date"><em>Monday, October 13, 2017</em></h6>
                            </div>
                        </div>

                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="${resources}/images/recent-post-2-150x200.jpg"
                                                           alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">Travel lights in winter</b></a></h5>
                                <h6 class="date"><em>Monday, October 13, 2017</em></h6>
                            </div>
                        </div>
                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="${resources}/images/recent-post-3-150x200.jpg"
                                                           alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">How to travel with no money</b></a></h5>
                                <h6 class="date"><em>Monday, October 13, 2017</em></h6>
                            </div>
                        </div>

                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="${resources}/images/recent-post-4-150x200.jpg"
                                                           alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">Smile 10 times a day</b></a></h5>
                                <h6 class="date"><em>Monday, October 13, 2017</em></h6>
                            </div>
                        </div>

                    </div><!-- sidebar-section latest-post-area -->


                </div><!-- about-author -->
            </div><!-- col-lg-4 -->
        </div><!-- row -->
    </div><!-- container -->
</section><!-- section -->
<footer>
    <div class="container">
        <div class="row">

            <div class="col-sm-6">
                <div class="footer-section">
                    <p class="copyright">Atypon &copy; 2018. All rights reserved | Made with <i class="ion-heart"
                                                                                                aria-hidden="true"></i>
                        by
                        <a href="#" target="_blank">Nasser Alkhateeb</a></p>
                </div><!-- footer-section -->
            </div><!-- col-lg-4 col-md-6 -->
        </div><!-- col-lg-4 col-md-6 -->

    </div><!-- row -->
    </div><!-- container -->
</footer>


<!-- SCIPTS -->

<script src="${resources}/common-js/jquery-3.1.1.min.js"></script>

<script src="${resources}/common-js/tether.min.js"></script>

<script src="${resources}/common-js/bootstrap.js"></script>

<script src="${resources}/common-js/layerslider.js"></script>

<script src="${resources}/common-js/scripts.js"></script>

</body>
</html>
