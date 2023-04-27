package com.csi.dao;

import com.csi.model.Employee;

import java.util.List;

public interface EmployeeDao {

    public void signUp(Employee employee);

    public boolean signIn(String empEmailId, String empPassword);

    public Employee getDataById(int empId);

    public List<Employee> getAllData();

    public void saveBulkOfData(List<Employee> employees);

    public List<Employee> getDataByUsingAnyInput(String input);

    public void updateData(int empId, Employee employee);

    public void partialUpdate(int empId, Employee employee);

    public void deleteDataById(int empId);

    public void deleteAllData();




}

/*
1. signUp
        2. signIn
        3. getDataById
        4. getAllData
        5. getDataByName
        6. getDataByContactNumber
        7. getDataByEmailId
        8. sortById
        9. sortByAge
        10. sortBySalary
        11. sortByName
        12. getDataByFirstNameAndLastName
        13. filterBySalary
        14. checkLoanEligibility
        15. saveBulkOfData
        16. getDataBySingAnyInput
        17. getDataByDOB
        18. updateData
        19. Partial Updation- Update Contact Number and Address
        20. deleteById
        21. deleteAllData*/
