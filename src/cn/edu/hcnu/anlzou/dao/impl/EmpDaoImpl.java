package cn.edu.hcnu.anlzou.dao.impl;

import cn.edu.hcnu.anlzou.dao.Dao;
import cn.edu.hcnu.anlzou.dao.IEmpDao;
import cn.edu.hcnu.anlzou.model.Emp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.*;
import java.util.List;

public class EmpDaoImpl implements IEmpDao {
    @Override
    public List viewEmp() {
        Dao dao = new Dao();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet reset = null;

        String sql_emp = "select * from emp";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(dao.url, dao.username, dao.password);
            psmt = conn.prepareStatement(sql_emp);

            QueryRunner queryRunner = new QueryRunner();
            List empList = queryRunner.query(conn, sql_emp, new BeanListHandler<>(Emp.class));

            //查询总页数
            sql_emp = "select count (*) from EMP";
            psmt = conn.prepareStatement(sql_emp);
            reset = psmt.executeQuery();
            reset.next();
            return empList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                reset.close();
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void addEmp(Emp emp) {
        Dao Dao = new Dao();
        Connection conn = null;
        PreparedStatement psmt = null;
        String sql_insert = "insert into emp(empno,ename,hiredate) values(?,?,?)";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(Dao.url, Dao.username, Dao.password);
            psmt = conn.prepareStatement(sql_insert);
            psmt.setInt(1, emp.getEmpno());
            psmt.setString(2, emp.getEname());
            psmt.setDate(3, emp.getHiredate());
            psmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteEmp(String empno) {
        Dao dao = new Dao();
        Connection conn = null;
        PreparedStatement psmt = null;
        String sql_delete_emp = "delete from emp where empno = ?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(dao.url, dao.username, dao.password);
            conn.setAutoCommit(false);//自动提交：否
            psmt = conn.prepareStatement(sql_delete_emp);
            psmt.setInt(1, Integer.parseInt(empno));
            psmt.executeUpdate();//执行（删除）
            conn.commit();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Emp updateEmp(String empno) {
        Dao Dao = new Dao();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet reset = null;
        String sql_select = "select * from emp where empno=?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(Dao.url, Dao.username, Dao.password);
            psmt = conn.prepareStatement(sql_select);
            psmt.setInt(1, Integer.parseInt(empno));
            reset = psmt.executeQuery();
            Emp emp = new Emp();
            while (reset.next()) {
                emp.setEmpno(reset.getInt("EMPNO"));
                emp.setHiredate(reset.getDate("HIREDATE"));
                emp.setEname(reset.getString("ENAME"));
            }
            return emp;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                reset.close();
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void modifyEmp(Emp emp) {
        Dao dao = new Dao();
        Connection conn = null;
        PreparedStatement psmt = null;
        String sql_updata = "update emp set ename=?,hiredate=? where empno=?";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(dao.url, dao.username, dao.password);
            conn.setAutoCommit(false);//自动提交：否
            psmt = conn.prepareStatement(sql_updata);
            psmt.setString(1, emp.getEname());
            psmt.setDate(2, emp.getHiredate());
            psmt.setInt(3, emp.getEmpno());
            psmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                psmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
