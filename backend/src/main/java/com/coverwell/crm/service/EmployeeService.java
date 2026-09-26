package com.coverwell.crm.service;

import com.coverwell.crm.entity.Employee;
import com.coverwell.crm.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    // Get all employees
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }


    // Get employee by ID
    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Employee not found with id: " + id
                    )
                );
    }


    // Create employee
    public Employee createEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }


    // Update employee
    public Employee updateEmployee(
            Long id,
            Employee employeeDetails) {

        Employee employee =
                getEmployeeById(id);

        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setRole(employeeDetails.getRole());
        employee.setStatus(employeeDetails.getStatus());

        return employeeRepository.save(employee);
    }


    // Delete employee
    public void deleteEmployee(Long id) {

        Employee employee =
                getEmployeeById(id);

        employeeRepository.delete(employee);
    }
}