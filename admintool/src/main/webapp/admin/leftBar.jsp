<div class="sidebar" data-color="purple" data-image="${pageContext.request.contextPath}/assets/img/sidebar-5.jpg">

    <!--

        Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
        Tip 2: you can also add an image using data-image tag

    -->

    <div class="sidebar-wrapper">
        <div class="logo">
            <a href="http://www.creative-tim.com" class="simple-text">
                Admin Tool
            </a>
        </div>

        <ul class="nav">
            <li class="a">
                <a href="${pageContext.request.contextPath}/identities/all">
                    <i class="pe-7s-user"></i>
                    <p>Identities</p>
                </a>
            </li>
            <li class="b">
                <a href="${pageContext.request.contextPath}/journal/all">
                    <i class="pe-7s-notebook"></i>
                    <p>Content</p>
                </a>
            </li>
            <li class="c">
                <a href="${pageContext.request.contextPath}/licence/contentLicence">
                    <i class="pe-7s-news-paper"></i>
                    <p>Licences</p>
                </a>
            </li>
            <li class="d">
                <a href="${pageContext.request.contextPath}/backstage/form">
                    <i class="pe-7s-server"></i>
                    <p>Backstage</p>
                </a>
            </li>
            <li class="e">
                <a href="${pageContext.request.contextPath}/login/loginPage">
                    <i class="pe-7s-door-lock"></i>
                    <p>Logout</p>
                </a>
            </li>
        </ul>
    </div>
</div>
