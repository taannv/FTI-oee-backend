package com.dds.oee.repositorycanong01;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong01.CanOng01SplitStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Nguyen Van Tan
 * *
 * March 04, 2021
 */
@Repository
public interface CanOng01StatusRepository extends CommonRepository<CanOng01SplitStatus, Long> {
    Optional<CanOng01SplitStatus> findTopByOrderByIdDesc();
}
