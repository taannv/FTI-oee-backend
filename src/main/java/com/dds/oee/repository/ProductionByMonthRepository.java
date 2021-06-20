package com.dds.oee.repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.ProductionChart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author: Nguyen Van Tan
 *
 * May 20, 2020
 *
 */
@Repository
public interface ProductionByMonthRepository extends CommonRepository<ProductionChart, String> {

    @Query(value = "select Ngay, SUM(Khoi_luong) as khoi_luong from Table_2 where CONVERT(DATETIME, Ngay) between '2020-01-01' and '2020-01-31' group by Ngay",

            countQuery = "select count(distinct Ngay)  from Table_2 where CONVERT(DATETIME, Ngay) between '2020-01-01' and '2020-01-31'",
            nativeQuery = true)
    Page<ProductionChart> getProductionByMonth(String fromDate, String toDate, Pageable pageable);
}
