<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        #identityTableBody td {
            text-align: left;
            padding-left: 50px;
        }

        * {
        }

        p {
            color: black;
        }

        .table-hover tbody tr:hover td, .table-hover tbody tr:hover th {
            background-color: #FFAD4D;
            cursor: pointer;
        }

        th {
            font-weight: bold;
        }
    </style>

</head>
<body>
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

                    <div style="text-align: center;" class="single-post">
                        <div class="content">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card  table-wrapper-scroll-y">
                                        <div class="header">
                                            <h4 style="text-align: left;" class="title">Browse</h4>
                                            <p style="text-align: left;"><b>Browse a list of journals by selecting a
                                                discipline.</b></p>
                                        </div>
                                        <br/>
                                        <div style="text-align: left;padding-left: 0" class="form-group col-md-4">
                                            <label for="disciplineState">Discipline</label>
                                            <select id="disciplineState" name="discipline" class="form-control"
                                                    onchange="a(this)">
                                                <option
                                                        <c:if test="${param.discipline eq '#'}">selected</c:if>
                                                        value="#">Select a discipline
                                                </option>
                                                <option
                                                        <c:if test="${param.discipline eq 'Health and Sciences'}">selected</c:if>
                                                        value="Health and Sciences">Health Sciences
                                                </option>
                                                <option
                                                        <c:if test="${param.discipline eq 'Life and Biomedical Sciences'}">selected</c:if>
                                                        value="Life and Biomedical Sciences">
                                                    Life &amp; Biomedical Sciences
                                                </option>
                                                <option
                                                        <c:if test="${param.discipline eq 'Materials Science and Engineering'}">selected</c:if>
                                                        value="Materials Science and Engineering">Materials Science
                                                    &amp; Engineering
                                                </option>
                                                <option
                                                        <c:if test="${param.discipline eq 'Social Sciences and Humanities'}">selected</c:if>
                                                        value="Social Sciences and Humanities">Social Sciences &amp;
                                                    Humanities
                                                </option>
                                            </select>
                                        </div>

                                        <table style="text-align: left;" class="table table-hover table-striped">
                                            <thead>
                                            <tr>
                                                <th>Title</th>
                                                <th>Print ISSN</th>
                                                <th>Publisher Name</th>
                                                <th>discipline</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items="${requestScope.journals}" var="journal">
                                                <tr class="clickable-row"
                                                    data-href="${requestScope['javax.servlet.forward.request_uri']}/${journal.printIssn}">
                                                    <td>${journal.title}</td>
                                                    <td>${journal.printIssn}</td>
                                                    <td>${journal.publisherName}</td>
                                                    <td>${journal.discipline}</td>
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
<script>
    function getParameter(name) {
        if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
            return decodeURIComponent(name[1]);
    }

    $(function () {
        $('#disciplineState').val(getParameter("discipline") || "#");
    });

    function a(s) {
        var selected = s.value;
        var aa = "${context}/journal";
        if (selected !== "#") {
            aa = '${context}/journal?discipline=' + selected;
        }
        location = aa;
    }

    $(document).ready(function ($) {
        $(".clickable-row").click(function () {
            window.location = $(this).data("href");
        });
    });
</script>
</body>
</html>
