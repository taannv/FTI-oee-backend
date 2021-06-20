package com.dds.oee.repositorycanong03;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong03.CanOng03Speed;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Nguyen Van Tan
 * *
 * March 05, 2031
 */
@Repository
public interface CanOng03SpeedRepository extends CommonRepository<CanOng03Speed, Long> {
    Optional<CanOng03Speed> findTopByOrderByIdDesc();
}
