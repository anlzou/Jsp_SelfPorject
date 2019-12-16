package cn.edu.hcnu.anlzou.dao;

import cn.edu.hcnu.anlzou.model.Emp;

import java.util.List;

public interface IEmpDao {
    List viewEmp();

    void addEmp(Emp emp);

    void deleteEmp(String empno);

    Emp updateEmp(String empno);

    void modifyEmp(Emp emp);
}
