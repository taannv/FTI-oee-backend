package com.dds.oee.repositorycanong03;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong03.CanOng03Split;
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
public interface CanOng03Repository extends CommonRepository<CanOng03Split, Long> {
    Optional<CanOng03Split> findTopByOrderByIdDesc();
}
