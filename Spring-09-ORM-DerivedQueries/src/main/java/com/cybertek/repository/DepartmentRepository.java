package com.cybertek.repository;

import com.cybertek.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    //display all departments in the furniture department
    List<Department> findByDepartment(String department);

    //display all departments in the health division
    List<Department> findByDivisionIs(String division);

    //display all departments in the health division
    List<Department> findByDivision(String division);

    //display all departments with division ends with 'ics'
    List<Department> findByDivisionEndingWith(String pattern);

    //display top 3 departments with division name includes 'Hea' with no duplicates
    List<Department> findDistinctTop3ByDivisionContains(String pattern);



}
