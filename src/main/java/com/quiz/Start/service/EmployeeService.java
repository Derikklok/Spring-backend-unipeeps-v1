package com.quiz.Start.service;

import com.quiz.Start.model.Employee;
import com.quiz.Start.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        if(employeeDetails.getFirstName() != null) employee.setFirstName(employeeDetails.getFirstName());
        if(employeeDetails.getLastName() != null) employee.setLastName(employeeDetails.getLastName());
        if(employeeDetails.getEmail() != null) employee.setEmail(employeeDetails.getEmail());
        if(employeeDetails.getDepartment() != null) employee.setDepartment(employeeDetails.getDepartment());
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
