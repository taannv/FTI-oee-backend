package com.dds.oee.repositorycanong01;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.ProductionOrder;
import com.dds.oee.entitycanong01.CanOng01ProductionOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 *
 * @author: Nguyen Van Tan
 *
 * Jan 25, 2021
 *
 */
@Repository
public interface CanOng01ProductionOrderRepository extends CommonRepository<CanOng01ProductionOrder, Long> {

    Optional<CanOng01ProductionOrder> findTopByOrderByIdDesc();
//    T1 inner join (select max(id) as maxid, Lenh_sx as lxs from Table_2 group by Lenh_sx) T2  on T1.id = T2.maxid
    @Query(value = "select * from  Table_2 " +
            "where (date1 between :dFrom and :dTo) and (Day_In between :hFrom and :hTo) and (Rong_In between :wFrom and :wTo)",
            nativeQuery = true)
    Page<CanOng01ProductionOrder> getReports(@Param("dFrom") LocalDateTime dFrom, @Param("dTo") LocalDateTime dTo, @Param("hFrom") Long hFrom, @Param("hTo") Long hTo,
                                     @Param("wFrom") Long wFrom, @Param("wTo") Long wTo, Pageable pageable);
}
