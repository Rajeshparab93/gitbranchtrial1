package com.csi.dao;

import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    String signup_SQL = "insert into employee (empid, empname, empaddress, empcontactnumber, empsalary, empdob, empemailid, emppassword) values(?,?,?,?,?,?,?,?)";

    String select_by_id_SQL = "select * from employee where empid=?";

    String select_all_SQL = "select * from employee";

    String update_data_SQL= "update employee set empname=?, empaddress=?, empcontactnumber=?, empsalary=?, empdob=?, empemailid=?, emppassword=? where empid=?";

    String partial_update_SQL= "update employee set address=?, empcontactnumber=? where empid=?";

    String delete_by_id_SQL= "delete from employee where empid=?";

    String delete_all_data_SQL= "truncate table employee";

    private Employee employee(ResultSet resultSet, int numRow) throws SQLException {
        return Employee.builder().empId(resultSet.getInt(1)).empName(resultSet.getString(2)).empAddress(resultSet.getString(3)).empContactNumber(resultSet.getLong(4)).empSalary(resultSet.getDouble(5)).empDOB(resultSet.getDate(6)).empEmailId(resultSet.getString(7)).empPassword(resultSet.getString(8)).build();
    }
    @Override
    public void signUp(Employee employee) {

        jdbcTemplate.update(signup_SQL, employee.getEmpId(), employee.getEmpName(), employee.getEmpAddress(), employee.getEmpContactNumber(), employee.getEmpSalary(), employee.getEmpDOB(), employee.getEmpEmailId(), employee.getEmpPassword());

    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean flag = false;

        for(Employee employee: getAllData()) {
            if(employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public Employee getDataById(int empId) {
        return jdbcTemplate.query(select_by_id_SQL, this::employee, empId).get(0);
    }

    @Override
    public List<Employee> getAllData() {
        return jdbcTemplate.query(select_all_SQL,this::employee);
    }

    @Override
    public void saveBulkOfData(List<Employee> employees) {

        for(Employee employee:employees) {
            jdbcTemplate.update(signup_SQL,employee.getEmpId(), employee.getEmpName(), employee.getEmpAddress(), employee.getEmpContactNumber(), employee.getEmpSalary(), employee.getEmpDOB(), employee.getEmpEmailId(), employee.getEmpPassword());
        }

    }

    @Override
    public List<Employee> getDataByUsingAnyInput(String input) {

        List<Employee> employeeList = new ArrayList<>();

        for(Employee employee:getAllData()) {
            if(String.valueOf(employee.getEmpId()).equals(input) || employee.getEmpName().equals(input) || String.valueOf(employee.getEmpContactNumber()).equals(input) || employee.getEmpEmailId().equals(input)) {

                employeeList.add(employee);
            }
        }
        return employeeList;
    }

    @Override
    public void updateData(int empId, Employee employee) {

        jdbcTemplate.update(update_data_SQL, employee.getEmpName(), employee.getEmpAddress(), employee.getEmpContactNumber(), employee.getEmpSalary(), employee.getEmpDOB(), employee.getEmpEmailId(), employee.getEmpPassword(), empId);

    }

    @Override
    public void partialUpdate(int empId, Employee employee) {
        jdbcTemplate.update(partial_update_SQL, employee.getEmpAddress(), employee.getEmpContactNumber(), empId);

    }

    @Override
    public void deleteDataById(int empId) {
        jdbcTemplate.update(delete_by_id_SQL, empId);

    }

    @Override
    public void deleteAllData() {
        jdbcTemplate.update(delete_all_data_SQL);

    }
}
