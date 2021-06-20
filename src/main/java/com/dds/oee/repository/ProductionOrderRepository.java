package com.dds.oee.repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.ProductionOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ProductionOrderRepository extends CommonRepository<ProductionOrder, Long> {

    Optional<ProductionOrder> findTopByOrderByIdDesc();

    @Query(value = "select * from  Table_2 T1 inner join (select max(id) as maxid, Lenh_sx as lxs from Table_2 group by Lenh_sx) T2  on T1.id = T2.maxid " +
            "where (date1 between :dFrom and :dTo) and (Do_day between :hFrom and :hTo) and (Chieu_rong between :wFrom and :wTo)",
            nativeQuery = true)
    Page<ProductionOrder> getReports(@Param("dFrom") LocalDateTime dFrom, @Param("dTo") LocalDateTime dTo, @Param("hFrom") Long hFrom, @Param("hTo") Long hTo,
                                     @Param("wFrom") Long wFrom, @Param("wTo") Long wTo, Pageable pageable);
}
