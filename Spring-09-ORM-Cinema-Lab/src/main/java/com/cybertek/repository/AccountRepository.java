package com.cybertek.repository;

import com.cybertek.entity.Account;
import com.cybertek.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // ------------------- DERIVED QUERIES ------------------- //

//Write a derived query to list all accounts with a specific country or state
    List<Account> findByCountryOOrState(String country, String state);

//Write a derived query to list all accounts with age lower than or equal to a specific value
    List<Account> findByAgeLessThanEqual(Integer age);

//Write a derived query to list all accounts with a specific role
    List<Account>findByRole(UserRole role);


//Write a derived query to list all accounts between a range of ages
    List<Account> findByAgeBetween(Integer from, Integer to);

//Write a derived query to list all accounts where the beginning of the address contains the keyword
    List<Account> findByAddressStartingWith(String pattern);

//Write a derived query to sort the list of accounts with age
    List<Account> findByOrderByAgeDesc();

// ------------------- JPQL QUERIES ------------------- // -> search based on entity and its fields
//Write a JPQL query that returns all accounts
@Query("SELECT a FROM Account a")
List<Account> fetchAllJPQL();

//Write a JPQL query to list all user accounts
@Query("SELECT a FROM Account a WHERE a.role='USER'")
List<Account> getAdminAccount();

//Write a JPQL query to sort all accounts with age
@Query("SELECT a FROM Account a order by a.age desc")
List<Account> sortAccountByAge();

// ------------------- Native QUERIES ------------------- // -> pure SQL based on the database
//Write a native query to read all accounts with an age lower than a specific value
    @Query(value="SELECT * FROM account_details WHERE age < :age", nativeQuery = true) //or age<?1
    List<Account> retrieveAllByAgeLowersThan(Integer age);
//Write a native query to read all accounts that a specific value can be containable in the name, address, country, state city
    @Query(value = "select * from account_details where name ILIKE concat('%',?1,'%') OR country ILIKE concat('%',?1,'%') OR address ILIKE concat('%',?1,'%') or state ILIKE concat('%',?1,'%')", nativeQuery = true)
    List<Account> retrieveBySearchCriteria(String pattern);
//Write a native query to read all accounts with an age lower than a specific value
    @Query(value="select * from account_details where age<?1",nativeQuery = true)
    List<Account>retrieveLessThanAge(Integer age);
}
