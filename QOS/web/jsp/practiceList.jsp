
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practice List</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js" ></script>
        <link rel="stylesheet" href="${contextPath}/css/questionList.css">
        <script src="${contextPath}/js/questionList.js"></script>
    </head>
    <body>
        <div class="wrap">
            <%-- Include header page --%>
            <jsp:include page="header.jsp"/>

            <div class="row" style="margin-top: 3rem">
                <div class="col-md-1"></div>
                <div class="col-md-10" >
                    <div class="col-md-12">
                        <h1>Practice List</h1>
                        <form action="" method="get">
                            <select class="form-control" name="subjectId" style="width: 30%;float: left;">                                
                                <option value="0" selected="">Choose...</option>                         
                            </select>
                            <input type="hidden" name="service" value="filterPracticeListInformation">
                            <button type="submit" id="submit" class="btn btn-success" style="float: left;">Search</button>
                        </form>
                        <a href=""><button class="btn btn-success" style="float: right;margin: 10px;">New Practice</button></a>
                        <a href=""><button class="btn btn-success" style="float: right;margin: 10px;">Simulation Exam</button></a>
                    </div>
                    <div class="col-md-12" style="clear: both;">
                        <div class="container">
                            <%-- Table Container --%>
                            <div class="form-group">
                                <h5>Select Number of Rows</h5>
                                <%-- Select number of Rows show on table --%>
                                <select class  ="form-control" name="state" id="maxRows" style="width: 150px;">
                                    <option value="5000">Show All</option>
                                    <option value="5">5</option>
                                    <option value="10">10</option>
                                    <option value="20">20</option>
                                    <option value="50">50</option>
                                    <option value="70">70</option>
                                    <option value="100">100</option>
                                </select>
                            </div>
                            <%-- Table of QuestionList--%>
                            <table id="table-id" class="table table-bordered table-striped">
                                <thead>
                                    <%-- Headers of Table--%>
                                    <tr style="background-color: #F0D8D5;">
                                        <th>Brief Information</th>
                                        <th>Test type</th>
                                        <th>Date taken</th>
                                        <th>Duration</th>
                                        <th>Score</th>
                                        <th>Detail</th>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            Subject name: Elementary Japanese 101
                                            <br>
                                            Exam name: Practice Quiz
                                        </td>
                                        <td>Practice</td>
                                        <td>28/05/2024</td>
                                        <td>6793:40</td>
                                        <td>0</td>
                                        <td><a href=""> Detail </a></td>
                                    </tr>
                                </tbody>
                            </table>
                            <%--Start Pagination --%>
                            <div class='pagination-container'>
                                <nav>
                                    <ul class="pagination" style="justify-content: center">
                                        <li data-page="prev" >
                                            <span>  <button class="btn btn-info">Prev</button></span>
                                        </li>
                                        <%--Here the JS Function Will Add the Rows --%>
                                        <li data-page="next" id="prev">
                                            <span> <button class="btn btn-info">Next</button><span class=""></span></span>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-1"></div>
            </div>
            <div class="space" style="min-height: 50vh;"></div>
            <%-- Include footer page --%>
            <jsp:include page="footer.jsp"/>
        </div>
    </body>
</html>

