<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>信息查询</title>
</head>
<body>
<h3>员工信息-后端管理系统</h3>
<table border="1">
    <tr>
        <th>EMPNO</th>
        <th>ENAME</th>
        <th>HIREDATE</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${requestScope.emplist}" var="emp">
        <tr>
            <td>${emp.empno}</td>
            <td>${emp.ename}</td>
            <td>${emp.hiredate}</td>
            <td>
                <a href="javascript:void(0)" onclick="add_data()">添加</a>&nbsp;
                <a href="javascript:void(0)" onclick="update_data(${emp.empno})">修改</a>&nbsp;
                <a href="javascript:void(0)" onclick="delete_data(${emp.empno})">删除</a>&nbsp;
            </td>
        </tr>
    </c:forEach>
</table>
</body>
<script>
    function add_data() {
        window.location.href = "addEmp.jsp";
    }

    function delete_data(empno) {
        window.location.href = "deleteEmp?empno=" + empno;
    }

    function update_data(empno) {
        window.location.href = "updateEmp?empno=" + empno;
    }
</script>
</html>
