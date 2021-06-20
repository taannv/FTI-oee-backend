package com.dds.oee.repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.MachineSplitStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineStatusRepository extends CommonRepository<MachineSplitStatus, Long> {
    Optional<MachineSplitStatus> findTopByOrderByIdDesc();
}
