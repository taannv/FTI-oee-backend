package com.dds.oee.repositorycanong02;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong01.CanOng01SplitStatus;
import com.dds.oee.entitycanong02.CanOng02SplitStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author: Nguyen Van Tan
 * *
 * March 04, 2021
 */
@Repository
public interface CanOng02StatusRepository extends CommonRepository<CanOng02SplitStatus, Long> {
    Optional<CanOng02SplitStatus> findTopByOrderByIdDesc();
}
