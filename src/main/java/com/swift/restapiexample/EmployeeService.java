package com.swift.restapiexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public List<Employee> getAll(){
        List<Employee> employeeList = employeeRepository.findAll();
        if(employeeList.isEmpty()) return null;
        return employeeList;
    }

    public Employee save(Employee newEmployee){
        Employee employee = employeeRepository.save(new Employee(newEmployee.getName()));
        return employee;
    }

    public Employee update(Long id, Employee updatedEmployee){
        Optional<Employee> employeeData = employeeRepository.findById(id);
        if(employeeData.isPresent()){
            Employee employee = employeeData.get();
            employee.setName(updatedEmployee.getName());
            return employeeRepository.save(employee);
        }
        return this.save(updatedEmployee);
    }

    public void delete(Long id){
        employeeRepository.deleteById(id);
        System.out.println("The user has been deleted.");
    }

}

