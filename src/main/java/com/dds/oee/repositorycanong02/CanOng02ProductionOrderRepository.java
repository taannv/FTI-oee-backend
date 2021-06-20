package com.dds.oee.repositorycanong02;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong01.CanOng01ProductionOrder;
import com.dds.oee.entitycanong02.CanOng02ProductionOrder;
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
public interface CanOng02ProductionOrderRepository extends CommonRepository<CanOng02ProductionOrder, Long> {
    Optional<CanOng02ProductionOrder> findTopByOrderByIdDesc();

    @Query(value = "select * from  Table_2 " +
            "where (date1 between :dFrom and :dTo) and (Day_In between :hFrom and :hTo) and (Rong_In between :wFrom and :wTo)",
            nativeQuery = true)
    Page<CanOng02ProductionOrder> getReportsC2(@Param("dFrom") LocalDateTime dFrom, @Param("dTo") LocalDateTime dTo, @Param("hFrom") Long hFrom, @Param("hTo") Long hTo,
                                             @Param("wFrom") Long wFrom, @Param("wTo") Long wTo, Pageable pageable);
}
