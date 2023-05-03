package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Service;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public int getSalarySum() {
        return employeeRepository.getAllEmployees().stream()
                .mapToInt(Employee::getSalary).sum();
    }

    @Override
    public Employee getEmployeeWithMinSalary() {
        return employeeRepository.getAllEmployees()
                .stream()
                .min(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    @Override
    public Employee getEmployeeWithMaxSalary() {
        return employeeRepository.getAllEmployees()
                .stream()
                .max(Comparator.comparing(Employee::getSalary)).orElse(null);
    }

    @Override
    public List<Employee> getEmployeesWithHighSalary() {
        int averageSalary = getSalarySum()/employeeRepository.getAllEmployees().size();
        return employeeRepository.getAllEmployees().stream()
                .filter(e -> e.getSalary() >= averageSalary).toList();
    }
}
