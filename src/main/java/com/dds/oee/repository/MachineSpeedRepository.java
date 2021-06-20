package com.dds.oee.repository;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entity.MachineSpeed;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineSpeedRepository extends CommonRepository<MachineSpeed,Long> {
    Optional<MachineSpeed> findTopByOrderByIdDesc();
}
