package com.dds.oee.repositorycanong02;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong01.CanOng01Speed;
import com.dds.oee.entitycanong02.CanOng02Speed;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Nguyen Van Tan
 * *
 * March 05, 2021
 */
@Repository
public interface CanOng02SpeedRepository extends CommonRepository<CanOng02Speed, Long> {
    Optional<CanOng02Speed> findTopByOrderByIdDesc();
}
