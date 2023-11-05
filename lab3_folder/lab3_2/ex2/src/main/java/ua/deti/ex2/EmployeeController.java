package net.javaguides.springboot.controller;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.entity.Employee;
import net.javaguides.springboot.service.UserEmployee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class EmployeeController {

    private EmployeeService userService;

    // build create User REST API
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee user){
        Employee savedUser = userService.createEmployee(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // build get user by id REST API
    // http://localhost:8080/api/users/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long userId){
        Employee user = userService.getEmployeeById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Build Get All Users REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> users = userService.getAllEmployees();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Build Update User REST API
    @PutMapping("{id}")
    // http://localhost:8080/api/users/1
    public ResponseEntity<Employee> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody Employee user){
        user.setId(userId);
        Employee updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long userId){
        userService.deleteEmployee(userId);
        return new ResponseEntity<>("Employee successfully deleted!", HttpStatus.OK);
    }
}