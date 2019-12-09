package com.jiayunpeng.crud.service;

import com.jiayunpeng.crud.bean.Employee;
import com.jiayunpeng.crud.bean.EmployeeExample;
import com.jiayunpeng.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }

    /**
     * 保存员工
     *
     * @param employee
     */
    public void save(Employee employee) {
        employeeMapper.insertSelective(employee);
    }


    /**
     * 根据id查找员工
     *
     * @param id
     * @return
     */
    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新员工
     *
     * @param employee
     */
    public void updateEmp(Employee employee) {
        // TODO Auto-generated method stub
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    /**
     * 根据id删除单个员工
     *
     * @param id
     */
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }


    public boolean checkUser(String name) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andNameEqualTo(name);
        long count =employeeMapper.countByExample(employeeExample);
        employeeMapper.countByExample(employeeExample);
        return count==0;
    }
}
