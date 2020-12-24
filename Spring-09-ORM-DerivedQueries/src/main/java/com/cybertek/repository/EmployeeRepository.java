package com.cybertek.repository;

import com.cybertek.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //display all employees with email address ''
    List<Employee> findByEmail(String email);

    //display all employees with first name '' and last name '' also show all employees with email ''
    List<Employee> findByFirstNameAndLastNameOrEmail(String firstName, String lastName, String email);

    //display all employees with first name not ''
    List<Employee> findEmployeeByFirstNameIsNot(String firstName);

    //display all employees where last name starts with ''
    List<Employee> findEmployeeByLastNameStartingWith(String pattern);

    //display all employees with salaries higher than ''
    List<Employee> findEmployeeBySalaryGreaterThan(Integer salary);

    //display all employees with salaries less/equal than ''
    List<Employee> findEmployeeBySalaryLessThanEqual(Integer salary);

    //display all employees that have been hired between '' ''
    List<Employee> findByHireDateBetween(LocalDate startDate, LocalDate endDate);

    //display all employees with salaries greater/equal to '' in order
    List<Employee> findEmployeeBySalaryGreaterThanEqualOrderBySalaryDesc(Integer salary);

    //display top unique 3 employees that are making less/equal than ''
    List<Employee> findDistinctTop3BySalaryLessThanEqual(Integer salary);

    //display all employees that do not have email address
    List<Employee> findByEmailIsNull();


}
