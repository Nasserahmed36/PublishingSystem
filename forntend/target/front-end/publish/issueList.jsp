<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <c:set var="context" value="${pageContext.request.contextPath}"/>
    <c:set var="sources" value="${pageContext.request.contextPath}/publish"/>
    <title>TITLE</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">


    <!-- Font -->

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">


    <!-- Stylesheets -->

    <link href="${sources}/common-css/bootstrap.css" rel="stylesheet">

    <link href="${sources}/common-css/ionicons.css" rel="stylesheet">

    <link href="${sources}/common-css/layerslider.css" rel="stylesheet">


    <link href="${sources}/01-homepage/css/styles.css" rel="stylesheet">

    <link href="${sources}/01-homepage/css/responsive.css" rel="stylesheet">

    <style type="text/css">
        #identityTableBody td {
            text-align: left;
            padding-left: 50px;
        }

        .table {

        }

        * {
        }

        p {
            color: black;
        }

        th {
            font-weight: bold;
        }

        .table-hover tbody tr:hover td, .table-hover tbody tr:hover th {
            background-color: #FFAD4D;
            cursor: pointer;
        }
    </style>

</head>
<body>

<header>

    <div class="top-menu">

        <ul class="left-area welcome-area">
            <li class="hello-blog">Hello nice people, welcome to my blog</li>
            <li><a href="mailto:contact@juliblog.com">contact@juliblog.com</a></li>
        </ul><!-- left-area -->


        <div class="right-area">

            <div class="src-area">
                <form action="post">
                    <input class="src-input" type="text" placeholder="Search">
                    <button class="src-btn" type="submit"><i class="ion-ios-search-strong"></i></button>
                </form>
            </div><!-- src-area -->


        </div><!-- right-area -->

    </div><!-- top-menu -->

    <div class="middle-menu center-text"><a href="#" class="logo"><img src="${sources}/images/atypon.jpg"
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

                    <div style="text-align: center;" class="single-post">
                        <div class="content">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card  table-wrapper-scroll-y">
                                        <div class="header">
                                            <h4 style="text-align: left;" class="title">Browse</h4>
                                            <p style="text-align: left;"><b>Browse a list of issues by selecting the
                                                Volume.</b></p>
                                        </div>
                                        <br/>
                                        <div style="text-align: left;padding-left: 0" class="form-group col-md-4">
                                            <label for="disciplineState">Volume</label>
                                            <select id="disciplineState" name="volume" class="form-control"
                                                    onchange="changeVolume(this)">
                                                <option selected value="none">Select a Volume</option>
                                                <c:forEach var="i" begin="1" end="${requestScope.lastVolume}">
                                                    <option
                                                            <c:if test="${param.volume eq i}">selected</c:if>
                                                            value=${i}>Volume ${i}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <table style="text-align: left;" class="table table-hover table-striped">
                                            <thead>
                                            <tr>
                                                <th>Issue Number</th>
                                                <th>Date</th>
                                                <th>Volume</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${requestScope.issues}" var="issue">
                                                <tr>
                                                    <td>${issue.number}</td>
                                                    <td>${issue.year}</td>
                                                    <td>${issue.volume}</td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>
                        </div>
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

                    <div class="sidebar-section newsletter-area">
                        <h5 class="title"><b>Subscribe to our newsletter</b></h5>
                        <form action="post">
                            <input class="email-input" type="text" placeholder="Your email here">
                            <button class="btn btn-2" type="submit">SUBSCRIBE</button>
                        </form>
                    </div><!-- sidebar-section newsletter-area -->

                    <div class="sidebar-section latest-post-area">
                        <h4 class="title"><b class="light-color">Latest Articles</b></h4>

                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="${sources}/images/recent-post-1-150x200.jpg"
                                                           alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">One more night in the clubs</b></a></h5>
                                <h6 class="date"><em>Monday, October 13, 2017</em></h6>
                            </div>
                        </div>

                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="${sources}/images/recent-post-2-150x200.jpg"
                                                           alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">Travel lights in winter</b></a></h5>
                                <h6 class="date"><em>Monday, October 13, 2017</em></h6>
                            </div>
                        </div>
                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="${sources}/images/recent-post-3-150x200.jpg"
                                                           alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">How to travel with no money</b></a></h5>
                                <h6 class="date"><em>Monday, October 13, 2017</em></h6>
                            </div>
                        </div>

                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="${sources}/images/recent-post-4-150x200.jpg"
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

<script src="${sources}/common-js/jquery-3.1.1.min.js"></script>

<script src="${sources}/common-js/tether.min.js"></script>

<script src="${sources}/common-js/bootstrap.js"></script>

<script src="${sources}/common-js/layerslider.js"></script>

<script src="${sources}/common-js/scripts.js"></script>
<script>
    <%--function getParameter(name) {--%>
    <%--if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))--%>
    <%--return decodeURIComponent(name[1]);--%>
    <%--}--%>

    <%--$(function () {--%>
    <%--$('#disciplineState').val(getParameter("discipline") || "#" );--%>
    <%--});--%>

    function changeVolume(select) {
        if (select.value === 'none') {
            window.location = window.location.href.split("?")[0];
            return;
        }
        insertParam("volume", select.value);

    }

    function insertParam(key, value) {
        key = encodeURI(key);
        value = encodeURI(value);

        var kvp = document.location.search.substr(1).split('&');

        var i = kvp.length;
        var x;
        while (i--) {
            x = kvp[i].split('=');

            if (x[0] === key) {
                x[1] = value;
                kvp[i] = x.join('=');
                break;
            }
        }

        if (i < 0) {
            kvp[kvp.length] = [key, value].join('=');
        }

        //this will reload the page, it's likely better to store this until finished
        document.location.search = kvp.join('&');
    }
</script>
</body>
</html>
