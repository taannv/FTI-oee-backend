package com.dds.oee.repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.Production;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author: Nguyen Van Tan
 *
 * May 12, 2020
 *
 */
@Repository
public interface ProductionVolumeRepository extends CommonRepository<Production, String> {

    @Query(
            value = "select distinct Lenh_sx as lenh_sx, SUM(Khoi_luong) as khoi_luong \n" +
                    "from Table_2 Where date1 between ?1 and ?2 \n" +
                    "group by Lenh_sx",
            countQuery = "select count(distinct Lenh_sx) \n" +
                    "from Table_2\n"
            ,nativeQuery = true)
    Page<Production> getReportProductionVolume(String fromDate, String toDate, Pageable pageable);
}
