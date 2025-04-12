package com.salesanalytics.sales_analysis_system.repository;

import com.salesanalytics.sales_analysis_system.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    // 1. Total Revenue
    @Query("""
        SELECT SUM((p.unitPrice * od.quantitySold) - (p.unitPrice * od.quantitySold * p.discount))
        FROM OrderDetail od
        JOIN od.order o
        JOIN od.product p
        WHERE o.dateOfSale BETWEEN :start AND :end
    """)
    BigDecimal findTotalRevenue(@Param("start") LocalDate start, @Param("end") LocalDate end);

    // 2. Revenue by Product
    @Query("""
        SELECT p.name, SUM((p.unitPrice * od.quantitySold) - (p.unitPrice * od.quantitySold * p.discount))
        FROM OrderDetail od
        JOIN od.order o
        JOIN od.product p
        WHERE o.dateOfSale BETWEEN :start AND :end
        GROUP BY p.name
    """)
    List<Object[]> findRevenueByProduct(@Param("start") LocalDate start, @Param("end") LocalDate end);

    // 3. Revenue by Category
    @Query("""
        SELECT c.name, SUM((p.unitPrice * od.quantitySold) - (p.unitPrice * od.quantitySold * p.discount))
        FROM OrderDetail od
        JOIN od.order o
        JOIN od.product p
        JOIN p.category c
        WHERE o.dateOfSale BETWEEN :start AND :end
        GROUP BY c.name
    """)
    List<Object[]> findRevenueByCategory(@Param("start") LocalDate start, @Param("end") LocalDate end);

    // 4. Revenue by Region
    @Query("""
        SELECT r.name, SUM((p.unitPrice * od.quantitySold) - (p.unitPrice * od.quantitySold * p.discount))
        FROM OrderDetail od
        JOIN od.order o
        JOIN o.region r
        JOIN od.product p
        WHERE o.dateOfSale BETWEEN :start AND :end
        GROUP BY r.name
    """)
    List<Object[]> findRevenueByRegion(@Param("start") LocalDate start, @Param("end") LocalDate end);
}

