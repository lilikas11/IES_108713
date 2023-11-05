package net.javaguides.springboot.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.entity.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository userRepository;

    @Override
    public Employee createUser(Employee employee) {
        return userRepository.save(employee);
    }

    @Override
    public Employee getUserById(Long employeeId) {
        Optional<Employee> optionalEmployee = EmployeeRepository.findById(employeeId);
        return optionalEmployee.get();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return userRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee user) {
        Employee existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        Employee updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteEmployee(Long userId) {
        userRepository.deleteById(userId);
    }
}
