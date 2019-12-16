<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改</title>
</head>
<body>
<form action="modifyEmp">
    <h3>修改信息</h3></div>
    <label>EMPNO</label>
    <input type="text" name="empno" placeholder="EMPNO"
           value="${emp.empno}" readonly="readonly">
    <label>ENAME</label>
    <input type="text" name="ename" placeholder="ENAME"
           value="${emp.ename}">
    <label>HIREDATE</label>
    <input type="date" name="hiredate" placeholder="HIREDATE"
           value="${emp.hiredate}">
    <button type=" submit=">保存</button>
</form>
</body>
</html>
