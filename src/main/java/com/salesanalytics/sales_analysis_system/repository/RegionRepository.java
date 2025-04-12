package com.salesanalytics.sales_analysis_system.repository;

import com.salesanalytics.sales_analysis_system.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region,String> {
}
