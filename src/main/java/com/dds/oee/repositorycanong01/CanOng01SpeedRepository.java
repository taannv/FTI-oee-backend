package com.dds.oee.repositorycanong01;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong01.CanOng01Speed;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Nguyen Van Tan
 * *
 * March 05, 2021
 */
@Repository
public interface CanOng01SpeedRepository extends CommonRepository<CanOng01Speed, Long> {
    Optional<CanOng01Speed> findTopByOrderByIdDesc();
}
