<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <title>TITLE</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="UTF-8">


    <!-- Font -->

    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">


    <!-- Stylesheets -->

    <link href="common-css/bootstrap.css" rel="stylesheet">

    <link href="common-css/ionicons.css" rel="stylesheet">

    <link href="common-css/layerslider.css" rel="stylesheet">


    <link href="01-homepage/css/styles.css" rel="stylesheet">

    <link href="01-homepage/css/responsive.css" rel="stylesheet">

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

    <div class="middle-menu center-text"><a href="#" class="logo"><img src="images/atypon.jpg" alt="Logo Image"></a>
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
                                            <p style="text-align: left;"><b>Browse a list of journals by selecting a
                                                discipline.</b></p>
                                        </div>
                                        <br/>
                                        <div style="text-align: left;padding-left: 0" class="form-group col-md-4">
                                            <label for="disciplineState">Discipline</label>
                                            <select id="disciplineState" name="discipline" class="form-control">
                                                <option value="">Select a discipline</option>
                                                <option value="Health Sciences">Health Sciences</option>
                                                <option value="Life &amp; Biomedical Sciences">Life &amp; Biomedical
                                                    Sciences
                                                </option>
                                                <option value="Materials Science &amp; Engineering">Materials Science
                                                    &amp; Engineering
                                                </option>
                                                <option value="Social Sciences &amp; Humanities">Social Sciences &amp;
                                                    Humanities
                                                </option>
                                            </select>
                                        </div>
                                        <table style="text-align: left;" class="table table-hover table-striped">

                                            <tbody>
                                            <c:forEach items="${requestScope.journals}" var="journal">
                                                <tr>
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
                            <div class="l-post-image"><img src="images/recent-post-1-150x200.jpg" alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">One more night in the clubs</b></a></h5>
                                <h6 class="date"><em>Monday, October 13, 2017</em></h6>
                            </div>
                        </div>

                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="images/recent-post-2-150x200.jpg" alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">Travel lights in winter</b></a></h5>
                                <h6 class="date"><em>Monday, October 13, 2017</em></h6>
                            </div>
                        </div>
                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="images/recent-post-3-150x200.jpg" alt="Category Image">
                            </div>
                            <div class="post-info">
                                <a class="btn category-btn" href="#">TRAVEL</a>
                                <h5><a href="#"><b class="light-color">How to travel with no money</b></a></h5>
                                <h6 class="date"><em>Monday, October 13, 2017</em></h6>
                            </div>
                        </div>

                        <div class="latest-post" href="#">
                            <div class="l-post-image"><img src="images/recent-post-4-150x200.jpg" alt="Category Image">
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

<script src="common-js/jquery-3.1.1.min.js"></script>

<script src="common-js/tether.min.js"></script>

<script src="common-js/bootstrap.js"></script>

<script src="common-js/layerslider.js"></script>

<script src="common-js/scripts.js"></script>

</body>
</html>
