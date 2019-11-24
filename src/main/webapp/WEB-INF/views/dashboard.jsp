<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>

    <title>Dashboard</title>
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>
    <link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom.js"></script>

</head>
<body>
<header class="header">
    <h3>Dashboard</h3>
    <h5 class="logout-text">
        <a href="/logout">Logout</a>
    </h5>
</header>

<div class="parent-panel">
    <div class="left-panel">
        <ul>
            <li>
                Dashboard
            </li>
            <li>
                Company Master
            </li>
        </ul>
    </div>
    <div class="right-panel">
        <div id="details">
            <form action="/company/refresh-form" method="get">
                <button id="add-btn" type="submit" onclick="onNewClick();">New</button>
            </form>
            <table  border="2" width="70%" cellpadding="2">
                <tr>
                    <th>Company Code</th>
                    <th>Code HRIS</th>
                    <th>Name</th>
                    <th>Status</th>
                    <th>Active Date</th>
                    <th>Created By</th>

                </tr>
                <c:forEach var="company" items="${data.content}">
                    <tr>
                        <td>${company.compCode}</td>
                        <td>${company.compCodeHRIS}</td>
                        <td>${company.compName}</td>
                        <td>${company.active}</td>
                        <td>${company.compActiveDate}</td>
                        <td>${company.createdBy}</td>
                    </tr>
                </c:forEach>
            </table>
            <br/>
            <c:forEach var="i" begin="0" end="${data.totalPages}">
                <a  href="/company/fetch/${i}/5">${i+1}</a>
            </c:forEach>
        </div>

        <div id="form-container">
            <form action="/company/back-refresh" method="get">
                <button id="back-btn" type="submit" onclick="onBackClick();">Back</button>
            </form>

            <div id="edit-form">
                <springForm:form id="company" method="POST" modelAttribute="companyMasterVO" action="/company/add"
                                 class="form-inline">
                    <c:if test="${exception != ''}">
                        <p class="error">${exception}</p>
                    </c:if>
                    <springForm:errors path="*" cssClass="errorblock" id="dashboardErrors" element="div"/>
                    <div class="form-inline">
                        <label for="compCode">Company Code *</label>
                        <div class="field">
                            <springForm:input placeholder="Enter Company Code" path="compCode"/>
                            <span><springForm:errors path="compCode" cssClass="error"/></span>
                        </div>
                        <label for="compCodeHRIS">Company Code HRIS*</label>
                        <div class="field">
                            <springForm:input path="compCodeHRIS"
                                              placeholder="Enter Company Code HRIS"/>
                            <span><springForm:errors path="compCodeHRIS" cssClass="error"/></span>
                        </div>
                    </div>

                    <div class="form-inline">
                        <label for="compName">Company Name*</label>
                        <div class="field">
                            <springForm:input path="compName" type="text"
                                              placeholder="Enter company name"/>
                            <span><springForm:errors path="compName" cssClass="error"/></span>
                        </div>

                        <label for="compAbbrName">Company Abbr. Name*</label>
                        <div class="field">
                            <springForm:input path="compAbbrName" type="text"
                                              placeholder="Enter Company Abbr. Name"/>
                            <span><springForm:errors path="compAbbrName" cssClass="error"/></span>
                        </div>
                    </div>

                    <div class="form-inline">
                        <label for="compRegNo">Company Reg. No</label>
                        <div class="field">
                            <springForm:input path="compRegNo" type="text"
                                              placeholder="Enter Company Reg#"/>
                            <span><springForm:errors path="compRegNo" cssClass="error"/></span>
                        </div>
                        <label for="compLogo">Company Logo:</label>
                        <div class="field">
                            <springForm:input path="compLogo" type="text"
                                              placeholder="Enter Logo Text"/>
                            <span><springForm:errors path="compLogo" cssClass="error"/></span>
                        </div>
                    </div>


                    <div class="form-inline">
                        <label for="compActiveDate">Company Active Date*</label>
                        <div class="field">
                            <springForm:input path="compActiveDate" type="date"
                                              placeholder="Enter Company Active Date"/>

                            <span><springForm:errors path="compActiveDate" cssClass="error"/></span>
                        </div>
                    </div>


                    <div class="form-inline other-details">
                        <label for="active">Status:</label>
                        <div class="field">
                            <springForm:select path="active">
                                <springForm:option value="true">Active</springForm:option>
                                <springForm:option value="false">De Active</springForm:option>
                            </springForm:select>
                            <span><springForm:errors path="active" cssClass="error"/></span>
                        </div>
                    </div><%----%>

                    <div class="form-inline">
                        <label for="createdBy">Created By</label>
                        <div class="field">
                            <springForm:input path="createdBy" disabled="true" type="text"/>
                        </div>

                        <label for="createdOn">Created On</label>
                        <div class="field">
                            <springForm:input disabled="true" path="createdOn" type="date" placeholder="Created On"/>
                        </div>
                    </div>


                    <div class="form-inline">
                        <label for="lastModifiedBy">Updated By</label>
                        <div class="field">
                            <springForm:input disabled="true" path="lastModifiedBy" type="text"/>
                        </div>
                        <label for="lastModifiedOn">Updated On</label>
                        <div class="field">
                            <springForm:input disabled="true" path="lastModifiedOn" type="date"/>
                        </div>
                    </div>

                    <div class="form-inline">
                        <label for="deactivatedBy">Deactivated By</label>
                        <div class="field">
                            <springForm:input path="deactivatedBy" disable="true" type="text"/>
                        </div>
                        <label for="deactivatedOn">Deactivated On</label>
                        <div class="field">
                            <springForm:input disabled="true" type="date" path="deactivatedOn"/>
                        </div>
                    </div>

                    <div class="form-inline">
                        <label for="reactivatedBy">Reactivated By</label>
                        <div class="field">
                            <springForm:input path="reactivatedBy" disabled="true" type="text"/>
                        </div>
                        <label for="reactivatedOn">Reactivated On:</label>
                        <div class="field">
                            <springForm:input disabled="true" type="date" path="reactivatedOn"/>
                        </div>
                    </div><%----%>


                    <div class="form-btns">
                        <springForm:button type="reset" class="save-btn" value="Reset"/>
                        <button type="submit" class="save-btn">Submit</button>
                    </div>

                </springForm:form>
            </div>

        </div>

    </div>
</div>

<script>
    var error =
    <c:out value="${error}"/>
    if (error == true) {
        document.getElementById('details').style.display = "none";
        document.getElementById('form-container').style.display = "block";
    }
</script>
</body>
</html>

