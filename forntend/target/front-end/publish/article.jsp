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

        div.articleInfo {
            border-bottom: #ccc 1px solid;
        }

        span.author span.name {
            font-weight: bold;
        }

        div.article span.author span.name {
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

        div.article {
            margin-top: 15px;
            font-size: 16px;
            color: #333;
        }

        div.article h4 {
            font-family: SansSerif;
            font-weight: bold;
        }


        h2 {
            font-size: 2em;
        }

        h3 {
            font-size: 1.5em;
        }

        p {
            margin-top: 15px;
            margin-bottom: 15px;
        }

        .section .heading {
            padding-bottom: 0;
        }

        li.ref {
            list-style: initial;
            display: list-item;
            border-bottom: black 1px solid;
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

<jsp:include page="header.jsp"/>
<section style="margin-top: 230px;" class="section blog-area">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-12">
                <div class="blog-posts">
                    <div class="single-post">
                        <div class="row">
                            <div class="col-lg-12 col-md-12">
                                <div class="articleInfo">
                                    <h4><a href="#">${requestScope.article.title}</a></h4>
                                    <div class="authors">
                                        <c:forEach items="${requestScope.article.authors}" var="author"
                                                   varStatus="status">
                                            <span class="author">
                                                <span class="name">
                                                    ${author.givenName} ${author.surName}
                                                </span>
                                                <span class="degress">
                                                        ${author.degrees}
                                                        <c:if test="${not status.last}">,</c:if>

                                                </span>
                                            </span>
                                        </c:forEach>
                                    </div>
                                    <div class=" date">
                                        <em>First Published <easy:DateFormat month="${requestScope.article.month}"/>
                                            16, ${requestScope.article.year};
                                            pp. ${requestScope.article.firstPage}-${requestScope.article.lastPage}</em>
                                    </div>
                                </div><!-- single-post -->
                                <div class="articleContent">
                                    <jsp:include page="${requestScope.articleView}"/>
                                </div>
                            </div><!-- col-sm-6 -->
                        </div><!-- row -->
                    </div><!-- single-post -->
                </div><!-- blog-posts -->
            </div><!-- col-lg-4 -->
            <div class="col-lg-4 col-md-12">
                <div class="sidebar-area">

                    <div class="issueCard card">
                        <a href="${context}/journal/${requestScope.issue.journalPrintIssn}?volume=${requestScope.issue.volume}">
                            <img class="card-img-top" src="${resources}/${requestScope.issueCover}"
                                 alt="Card image cap">
                            <div class="card-body">
                                <h6 class="title card-text">Volume ${requestScope.issue.volume}
                                    Issue ${requestScope.issue.number},
                                    <easy:DateFormat
                                            month="${requestScope.issue.month}"/> ${requestScope.issue.year}</h6>
                            </div>
                        </a>
                    </div>

                    <div class="sidebar-section latest-post-area">
                        <h4 class="title"><b class="light-color">Latest Articles</b></h4>

                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="${resources}/images/recent-post-1-150x200.jpg"
                                                           alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">Letter from the Editor-in-Chief</b></a></h5>
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
