package com.cybertek.repository;

import com.cybertek.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    // ------------------- DERIVED QUERIES ------------------- //
//Write a derived query to get cinema with a specific name
    Optional<Cinema> findByName(String name);

    //Write a derived query to read sorted the top 3 cinemas that contains a specific sponsored name
    List<Cinema> findTop3BySponsoredNameContainingOrderBySponsoredName(String sponsoredName);

    //Write a derived query to list all cinemas in a specific country
    List<Cinema> findAllByLocationCountry(String country);

    //Write a derived query to list all cinemas with a specific name or sponsored name
    List<Cinema> findBySponsoredNameOrName(String name, String sponsoredName);

    // ------------------- JPQL QUERIES ------------------- //
//Write a JPQL query to read the cinema name with a specific id
    @Query("select c.name from Cinema c where c.id=?1")
    List<Cinema> findById();

    // ------------------- Native QUERIES ------------------- //
//Write a native query to read all cinemas by location country
    @Query(value = "SELECT * FROM cinema c join location l on l.id=c.location_id where l.country=?1", nativeQuery = true)
    List<Cinema> retrieveAllByLocationCountry(String locationCountry);

    //Write a native query to read all cinemas by name or sponsored name contains a specific pattern
    @Query(value = "SELECT * FROM cinema WHERE name ILIKE concat('%',?1,'%') or  sponsored_name ILIKE concat('%',?1,'%')", nativeQuery = true)
    List<Cinema> retrieveAllByNameOrSponsoredName(String pattern);

    //Write a native query to sort all cinemas by name
    @Query(value = "SELECT * FROM cinema order by name", nativeQuery = true)
    List<Cinema> sortByName();

    //Write a native query to distinct all cinemas by sponsored name
    @Query(value = "select distinct sponsored_name from cinema", nativeQuery = true)
    List<String> distinctBySponsoredName();
}
