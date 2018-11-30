<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <link rel="icon" type="image/png" href="assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Licences</title>

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

        .modal-body th {
            text-align: center;
        }

        .modal-body .table-hover tbody tr:hover td, .modal-body .table-hover tbody tr:hover th {
            background-color: #1DC7EA;
            color: white;
            cursor: pointer;
        }

        #chooseLicence:hover, .glyphicon:hover , #showLicence:hover {
            background-color: #1DC7EA;
            color: white;
        }

        #chooseLicence {
            border-color: #1DC7EA;
            color: #1DC7EA;
        }

        #licencesModal .modal-dialog,#licenceModal .modal-dialog {
            -webkit-transform: translate(0, -50%);
            -o-transform: translate(0, -50%);
            transform: translate(0, -50%);
            top: 50%;
            margin: 0 auto;
        }
    </style>

</head>
<body>
<!-- Modal -->
<div class="modal fade" id="licencesModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Choose Licence</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%--Are you sure you want to delete the Licence?--%>
                <div class="content table-responsive table-full-width">
                    <table class="table table-hover table-striped" style="text-align: center">
                        <thead>
                        <tr>
                            <th>Content ID</th>
                            <th>Licence Name</th>
                            <th>Licence Body</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.contentsLicences}" var="contentLicence">
                            <tr onclick="chooseLicence(this)">
                                <td class="contentLicenceId" style="display: none">${contentLicence.id}</td>
                                <td>${contentLicence.contentId}</td>
                                <td>${contentLicence.licenceName}</td>
                                <td>${contentLicence.body}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="licenceModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Choose Licence</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%--Are you sure you want to delete the Licence?--%>
                <div class="content table-responsive table-full-width">
                    <table class="table table-hover table-striped" style="text-align: center">
                        <thead>
                        <tr>
                            <th>Content ID</th>
                            <th>Licence Name</th>
                            <th>Licence Body</th>
                        </tr>
                        </thead>
                        <tbody id="licenceTableBody">
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete Licence</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete the Licence?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <button onclick="confirmRemove()" type="button" class="btn btn-info btn-fill" data-dismiss="modal">
                    Delete
                </button>
            </div>
        </div>
    </div>
</div>
<div class="wrapper">
    <%@include file="leftBar.jsp" %>
    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${pageContext.request.contextPath}/licence/contentLicence">Content
                        Licence</a>
                    <a style="font-weight: bold" class="navbar-brand" href="#">User Licence</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="${pageContext.request.contextPath}/login/loginPage">
                                <p>Log out</p>
                            </a>
                        </li>
                        <li class="separator hidden-lg"></li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="content">
            <div class="row">
                <div class="col-md-8">
                    <div class="card">
                        <div class="header">
                            <h4 class="title">Create User Licence</h4>
                        </div>
                        <div class="content">
                            <form method="post"
                                  enctype="multipart/form-data">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label>Content Licence</label>
                                            <button onclick="showLicences()" type="button" id="chooseLicence"
                                                    class="form-control">Choose Content
                                                Licence
                                            </button>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <div class="form-group">
                                            <label for="chosenLicenceId">Chosen Content Licence</label>
                                            <input readonly id="chosenLicenceId" name="contentLicenceId" type="text"
                                                   class="form-control"
                                                   value="${requestScope.form.contentLicenceId}">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label>Username</label>
                                            <input name="username" type="text" class="form-control"
                                                   value="${requestScope.form.username}">
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="licenceBody">Licence Body</label>
                                            <textarea placeholder="Licence Body" id="licenceBody" name="licenceBody"
                                                      class="form-control"
                                                      rows="5">${requestScope.form.body}</textarea>
                                        </div>
                                    </div>
                                </div>

                                <button formaction="${pageContext.request.contextPath}/licence/createUserLicence"
                                        type="submit" style="margin-left: 10px;"
                                        class="btn btn-info btn-fill pull-right">Create
                                </button>
                                <div class="clearfix"></div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="card  table-wrapper-scroll-y">
                        <div class="header">
                            <h4 class="title">Contents Licences</h4>
                        </div>
                        <div class="content table-responsive table-full-width">
                            <table class="table table-hover table-striped">
                                <thead>
                                <th>Username</th>
                                <th>Start Date</th>
                                <th>Licence Body</th>
                                <th>Content Licence</th>
                                </thead>
                                <tbody id="identityTableBody">
                                <c:forEach items="${requestScope.usersLicences}" var="contentLicence">
                                    <tr>
                                        <td style="display: none">${contentLicence.id}</td>
                                        <td>${contentLicence.username}</td>
                                        <td>${contentLicence.startDate}</td>
                                        <td>${contentLicence.body}</td>
                                        <td>
                                            <button id="showLicence" class="btn btn-info"
                                                    onclick="showLicence('${contentLicence.contentLicenceId}')">
                                                Show Content Licence
                                            </button>
                                        </td>
                                        <td>
                                            <button class="glyphicon glyphicon-trash btn btn-info"
                                                    onclick="removeRow(this)">
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>

            </div>
        </div>

        <footer class="footer">
            <div class="container-fluid">
                <nav class="pull-left">
                    <ul>
                        <li>
                            <a href="#">
                                Home
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Company
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Portfolio
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Blog
                            </a>
                        </li>
                    </ul>
                </nav>
                <p class="copyright pull-right">
                    &copy;
                    <script>document.write(new Date().getFullYear())</script>
                    <a href="http://www.creative-tim.com">Nasser Alkhateeb</a>, made with love for a better Literatum
                </p>
            </div>
        </footer>

    </div>
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
            $(".c").addClass("active");
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

    function licenceNameChanged(select) {
        var selected = select.value;
        document.getElementById("licenceBody").placeholder = nameToDescription[selected];
    }

    function showLicences() {
        $('#licencesModal').modal('show');
    }

    function showLicence(contentLicenceId) {
        var tableRow = $("td.contentLicenceId").filter(function() {
            return $(this).text() == contentLicenceId;
        }).closest("tr").html();
        $("#licenceTableBody").html('');
        $("#licenceTableBody").append('<tr>'+tableRow+'</tr>');
        $('#licenceModal').modal('show');
    }
    function chooseLicence(row) {
        var contentLicenceId = $(row).find('td:first-child').text();
        $('#licencesModal').modal('hide');
        document.getElementById("chosenLicenceId").value = contentLicenceId;
    }


    function removeRow(button) {
        row = $(button).closest('tr');
        userContentLicenceId = row.find('td:first-child').text();
        $('#exampleModal').modal('show');
    }


    function confirmRemove() {

        $.ajax({
            type: "GET",
            url: "http://localhost:8080/AdminTool/licence/deleteUserLicence?id=" + userContentLicenceId,
            success: function (data, textStatus, xhr) {
                row.remove();
                notify("Licence Removed")
            },
            error: function (xhr, status, error) {
                notify(xhr.responseText);
            }
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