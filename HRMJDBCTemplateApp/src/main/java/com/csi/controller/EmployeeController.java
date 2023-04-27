package com.csi.controller;

import com.csi.model.Employee;
import com.csi.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee) {
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("Data Saved..");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Employee> getDataById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/getdatabyname/{empName}")
    public ResponseEntity<List<Employee>> getDataByName(@PathVariable String empName) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp-> emp.getEmpName().equals(empName)).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabysalary")
    public ResponseEntity<List<Employee>> sortDataBySalary(@PathVariable double empSalary) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName(@PathVariable String empName) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));
    }

    @GetMapping("/filterbysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterBySalary(@PathVariable double empSalary) {

        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp-> emp.getEmpSalary()>=empSalary).collect(Collectors.toList()));

    }

    @GetMapping("/loaneligibility/{empId}")
    public ResponseEntity<String> loanEligibility(@PathVariable int empId) {
        String msg = null;

        for(Employee employee:employeeServiceImpl.getAllData()) {

            if(employee.getEmpId()==empId && employee.getEmpSalary()>= 50000) {
                msg = "Yes! Eligible for Loan ";
                break;
            } else {
                msg = "Not Eligible for Loan ";
            }
        }
        return ResponseEntity.ok(msg);
    }

    @PostMapping("/savebulkofdata")
    public ResponseEntity<String> saveBulkOfData(@RequestBody List<Employee> employeeList) {
        employeeServiceImpl.saveBulkOfData(employeeList);
        return ResponseEntity.ok("All data saved..");
    }

    @GetMapping("/getdatabyusinganyinput/{input}")
    public ResponseEntity<List<Employee>> getDataByUsingAnyInput(@PathVariable String input) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByUsingAnyInput(input));
    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<String> updateData(@PathVariable int empId, @RequestBody Employee employee) {
        employeeServiceImpl.updateData(empId, employee);
        return ResponseEntity.ok("Emp data get update");
    }

    @PatchMapping("/partialupdate/{empId}")
    public ResponseEntity<String> partialUpdate(@PathVariable int empId, @RequestBody Employee employee) {
        employeeServiceImpl.partialUpdate(empId, employee);
        return ResponseEntity.ok("Partial update done");
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId) {
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Emp data get delete");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData(){
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("All data get delete");
    }

}
