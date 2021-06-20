package com.dds.oee.repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.MachineSplit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineSplitRepository extends CommonRepository<MachineSplit, Long> {

    Optional<MachineSplit> findTopByOrderByIdDesc();

    @Query(value = "select T1.* from Table_1 T1 " +
            "inner join (select max(ID) id,date1 from Table_1 group by date1) T2 on T1.ID = T2.id Where T1.date1 between ?1 and ?1", nativeQuery = true)
    Page<MachineSplit> getMachineSplitByQuery(String date1, String date2, Pageable pageable);
}
