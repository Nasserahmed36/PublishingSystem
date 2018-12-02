<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login V11</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/admin/assets/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/admin/assets/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/admin/assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/admin/assets/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/admin/assets/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/admin/assets/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/admin/assets/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/admin/assets/css/main.css">
    <!--===============================================================================================-->
    <style>
        body div.container-login100 {
            background: rgba(203, 203, 210, 0.15);
        }
    </style>
</head>
<body>

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100 p-l-50 p-r-50 p-t-77 p-b-30">
            <form class="login100-form validate-form" method="post">
					<span class="login100-form-title p-b-55">
						Admin Login
					</span>

                <div class="wrap-input100 validate-input m-b-16" data-validate="username is required">
                    <input class="input100" type="text" name="username" placeholder="Username">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<span class="lnr lnr-user"></span>
						</span>
                </div>

                <div class="wrap-input100 validate-input m-b-16" data-validate="Password is required">
                    <input class="input100" type="password" name="password" placeholder="Password">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
							<span class="lnr lnr-lock"></span>
						</span>
                </div>

                <div class="contact100-form-checkbox m-l-4">
                    <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                    <label class="label-checkbox100" for="ckb1">
                        Remember me
                    </label>
                </div>

                <div class="container-login100-form-btn p-t-25">
                    <button formaction="${pageContext.request.contextPath}/login/" class="login100-form-btn">
                        Login
                    </button>
                </div>

            </form>
        </div>
    </div>
</div>


<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/admin/assets/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/admin/assets/vendor/bootstrap/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/admin/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/admin/assets/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/admin/assets/js/main.js"></script>
<!--===============================================================================================-->
<script src="${pageContext.request.contextPath}/admin/assets/js/jquery.3.2.1.min.js" type="text/javascript"></script>
<!--===============================================================================================-->
<%--For Notifications--%>
<script src="${pageContext.request.contextPath}/admin/assets/js/bootstrap-notify.js"></script>

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
            type: 'danger',
            timer: 4000,
        });
    }

</script>
<c:if test="${not empty requestScope.result}">
    <script>
        notify("${requestScope.result}");
    </script>
</c:if>
<c:if test="${not empty param.result}">
    <script>
        notify("${param.result}");
    </script>
</c:if>
</body>
</html>