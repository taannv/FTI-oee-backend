package com.dds.oee.repositorycanong03;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong03.CanOng03SplitStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Nguyen Van Tan
 * *
 * March 04, 2031
 */
@Repository
public interface CanOng03StatusRepository extends CommonRepository<CanOng03SplitStatus, Long> {
    Optional<CanOng03SplitStatus> findTopByOrderByIdDesc();
}
