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


    <link href="${resources}/common-css/bootstrap.css" rel="stylesheet">

    <link href="${resources}/common-css/ionicons.css" rel="stylesheet">

    <link href="${resources}/common-css/layerslider.css" rel="stylesheet">


    <link href="${resources}/01-homepage/css/styles.css" rel="stylesheet">

    <link href="${resources}/01-homepage/css/responsive.css" rel="stylesheet">
    <style type="text/css">
        .discipline.btn {
            width: 350px;
            font-weight: bold;
            font-size: 1em;
        }

        .btn a {
            color: black;
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

                    <div style="text-align: center;" class="single-post">
                        <h3 style="text-align: center;" class="title"><b class="light-color">Browse Journals
                            By Discipline</b></h3>
                        <a class="discipline btn read-more-btn"
                           href="${context}/journal?discipline=Health and Sciences"><b>Health Sciences</b></a>
                        <a class="discipline btn read-more-btn"
                           href="${context}/journal?discipline=Social Sciences and Humanities"><b>Social Sciences &
                            Humanities</b></a>
                        <br/>
                        <a class="discipline btn read-more-btn"
                           href="${context}/journal?discipline=Materials Science and Engineering"><b>Materials Sciences
                            & Engineering</b></a>
                        <a class="discipline btn read-more-btn"
                           href="${context}/journal?discipline=Life and Biomedical Sciences"><b>Life & Biomedical
                            Sciences</b></a>
                        <br/>
                        <a class="discipline btn read-more-btn" href="${context}/journal"><b>All Journals</b></a>
                    </div><!-- single-post -->

                    <div class="single-post">
                        <div class="row">
                            <h3 class="title"><b class="light-color">Articles most read on Atypon Journals in the last
                                year</b></h3>
                            <div class="col-lg-6 col-md-12">
                                <div class="single-post">
                                    <h4 class="title"><a href="#"><b class="light-color">How to paint the wall and
                                        street</b></a></h4>
                                    <p>Sed ut perspiciatis unde omnis iste natus error sit doloremque
                                        laudantium, totam rem aperiam, eaque ipsa quae ab illo veritatis et quasi
                                        dolore magnam aliquam quaerat voluptatem.</p>
                                    <a class="btn read-more-btn" href="#"><b>READ MORE</b></a>
                                </div><!-- single-post -->
                            </div><!-- col-sm-6 -->
                            <div class="col-lg-6 col-md-12">
                                <div class="single-post">

                                    <h4 class="title"><a href="#"><b class="light-color">Letter from the Editor-in-Chief</b></a></h4>
                                    <p>Sed ut perspiciatis unde omnis iste natus error sit doloremque
                                        laudantium, totam rem aperiam, eaque ipsa quae ab illo veritatis et quasi
                                        dolore magnam aliquam quaerat voluptatem.</p>
                                    <a class="btn read-more-btn" href="#"><b>READ MORE</b></a>
                                </div><!-- single-post -->
                            </div><!-- col-sm-6 -->
                        </div><!-- row -->

                    </div><!-- single-post -->
                    <div class="single-post">
                        <div class="row">
                            <h3 class="title"><b class="light-color">Articles most cited on Atypon Journals in the last
                                year</b></h3>
                            <div class="col-lg-6 col-md-12">
                                <div class="single-post">
                                    <h4 class="title"><a href="#"><b class="light-color">How to paint the wall and
                                        street</b></a></h4>
                                    <p>Sed ut perspiciatis unde omnis iste natus error sit doloremque
                                        laudantium, totam rem aperiam, eaque ipsa quae ab illo veritatis et quasi
                                        dolore magnam aliquam quaerat voluptatem.</p>
                                    <a class="btn read-more-btn" href="#"><b>READ MORE</b></a>
                                </div><!-- single-post -->
                            </div><!-- col-sm-6 -->
                            <div class="col-lg-6 col-md-12">
                                <div class="single-post">

                                    <h4 class="title"><a href="#"><b class="light-color">Letter from the Editor-in-Chief</b></a></h4>
                                    <p>Sed ut perspiciatis unde omnis iste natus error sit doloremque
                                        laudantium, totam rem aperiam, eaque ipsa quae ab illo veritatis et quasi
                                        dolore magnam aliquam quaerat voluptatem.</p>
                                    <a class="btn read-more-btn" href="#"><b>READ MORE</b></a>
                                </div><!-- single-post -->
                            </div><!-- col-sm-6 -->
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


<!--  Notifications Plugin    -->
<script src="${resources}/assets/js/bootstrap-notify.js"></script>


<script>
    function notify(message) {
        $.notify({
            message: message,
            icon: 'pe-7s-speaker',

        }, {
            placement: {
                from: "top",
                align: "center"
            },
            type: 'info',
            timer: 4000,
        });
    }

</script>
<c:if test="${not empty param.result}">
    <script>
        notify("${param.result}");
    </script>
</c:if>
</body>
</html>
