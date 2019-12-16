package cn.edu.hcnu.anlzou.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import cn.edu.hcnu.anlzou.dao.IEmpDao;
import cn.edu.hcnu.anlzou.dao.impl.EmpDaoImpl;
import cn.edu.hcnu.anlzou.model.Emp;


@WebServlet(name = "EmpServlet")
public class EmpServlet extends HttpServlet {
    IEmpDao empDao = new EmpDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void viewEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List emplist = empDao.viewEmp();
        request.setAttribute("emplist", emplist);//查询后的员工信息
        request.getRequestDispatcher("viewEmp.jsp").forward(request, response);
}

    public void addEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empno = request.getParameter("empno");
        String ename = request.getParameter("ename");
        String hiredate = request.getParameter("hiredate");
        Emp emp = new Emp();
        emp.setEmpno(Integer.parseInt(empno));
        emp.setEname(ename);
        emp.setHiredate(java.sql.Date.valueOf(hiredate));
        empDao.addEmp(emp);
        request.getRequestDispatcher("viewEmp").forward(request, response);
    }

    public void deleteEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empno = request.getParameter("empno");
        empDao.deleteEmp(empno);
        response.sendRedirect("viewEmp");
    }

    public void updateEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empno = request.getParameter("empno");
        Emp emp = empDao.updateEmp(empno);
        request.setAttribute("emp", emp);
        request.getRequestDispatcher("updateEmp.jsp").forward(request, response);
    }

    public void modifyEmp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String empno = request.getParameter("empno");
        String ename = request.getParameter("ename");
        String hiredate = request.getParameter("hiredate");
        Emp emp = new Emp();
        emp.setEmpno(Integer.parseInt(empno));
        emp.setEname(ename);
        emp.setHiredate(java.sql.Date.valueOf(hiredate));
        empDao.modifyEmp(emp);

        request.getRequestDispatcher("viewEmp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        uri = uri.substring(1);
        if ("viewEmp".equals(uri)) {
            viewEmp(request, response);
        } else if ("addEmp".equals(uri)) {
            addEmp(request, response);
        } else if ("deleteEmp".equals(uri)) {
            deleteEmp(request, response);
        } else if ("modifyEmp".equals(uri)) {
            modifyEmp(request, response);
        } else if ("updateEmp".equals(uri)) {
            updateEmp(request, response);
        }
    }
}
