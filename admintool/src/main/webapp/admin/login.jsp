<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link rel="icon" type="image/png" href="assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Backstage</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- Bootstrap core CSS     -->
    <link href="${pageContext.request.contextPath}/admin/assets/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Animation library for notifications   -->
    <link href="${pageContext.request.contextPath}/admin/assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="${pageContext.request.contextPath}/admin/assets/css/light-bootstrap-dashboard.css?v=1.4.0"
          rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="${pageContext.request.contextPath}/admin/assets/css/demo.css" rel="stylesheet"/>


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/admin/assets/css/pe-icon-7-stroke.css" rel="stylesheet"/>

    <style>
        .table-wrapper-scroll-y {
            max-height: 700px;
            overflow-y: auto;
            overflow-x: hidden;
        }
    </style>

</head>
<body>

<div class="wrapper">
</div>

</body>

<!--   Core JS Files   -->
<script src="${pageContext.request.contextPath}/admin/assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/admin/assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Charts Plugin -->
<script src="${pageContext.request.contextPath}/admin/assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="${pageContext.request.contextPath}/admin/assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="${pageContext.request.contextPath}/admin/text/javascript"
        src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="${pageContext.request.contextPath}/admin/assets/js/light-bootstrap-dashboard.js?v=1.4.0"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="${pageContext.request.contextPath}/admin/assets/js/demo.js"></script>

<script type="text/javascript">

    $(document).ready(function () {
        $(function () {
            $(".d").addClass("active");
        });
    });

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
<c:if test="${not empty requestScope.result}">
    <script>notify("${requestScope.result}")</script>
</c:if>
<c:forEach items="${requestScope.errors}" var="error">
    <script>
        notify("${error}");
    </script>
</c:forEach>

</html>