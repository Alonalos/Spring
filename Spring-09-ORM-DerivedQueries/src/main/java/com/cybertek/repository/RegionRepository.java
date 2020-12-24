package com.cybertek.repository;


import com.cybertek.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region,Integer> {

    //Display all regions in Canada
    List<Region> findByCountry(String country);

    //display all regions in Canada without duplicates
    List<Region> findDistinctByCountry(String country);

    //display all regions with country names that include "United"
    List<Region> findRegionsByCountryContaining(String country);

    //display all regions with country names that include "United" in order
    List<Region> findRegionsByCountryContainingOrderByCountry(String country);

    //display top two regions in Canada
    List<Region> findTop2ByCountry(String country);



}


