package com.dds.oee.repositorycanong02;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong02.CanOng02Split;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author: Nguyen Van Tan
 *
 * Feb 06, 2021
 *
 */
@Repository
public interface CanOng02Repository extends CommonRepository<CanOng02Split, Long> {
    Optional<CanOng02Split> findTopByOrderByIdDesc();
}
