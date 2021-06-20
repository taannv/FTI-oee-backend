package com.dds.oee.repositorycanong03;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong03.CanOng03ProductionOrder;
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
 * Feb 06, 2021
 *
 */
@Repository
public interface CanOng03ProductionOrderRepository extends CommonRepository<CanOng03ProductionOrder, Long> {
    Optional<CanOng03ProductionOrder> findTopByOrderByIdDesc();

    @Query(value = "select * from  Table_2 " +
            "where (date1 between :dFrom and :dTo) and (Day_In between :hFrom and :hTo) and (Rong_In between :wFrom and :wTo)",
            nativeQuery = true)
    Page<CanOng03ProductionOrder> getReportsC3(@Param("dFrom") LocalDateTime dFrom, @Param("dTo") LocalDateTime dTo, @Param("hFrom") Long hFrom, @Param("hTo") Long hTo,
                                             @Param("wFrom") Long wFrom, @Param("wTo") Long wTo, Pageable pageable);
}
