package com.dds.oee.repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.MachineQuantity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 *
 * @author: Phan Thi Hong
 *
 * May 26, 2020
 *
 */
 @Repository
public interface MachineReportQuantityRepository extends CommonRepository<MachineQuantity,String> {

    @Query(value = "select t1.* from (select Ngay as Ngay, SUM(Khoi_luong) as khoi_luong from Table_2 where MONTH(Ngay)=1 and YEAR(Ngay)=2020 group by Ngay) as t1", nativeQuery = true)
    List<MachineQuantity> getProductionByMonth();
}
