package com.dds.oee.repositorycanong01;

import com.dds.oee.common.CommonRepository;
import com.dds.oee.entitycanong01.CanOng01Split;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author: Nguyen Van Tan
 *
 * Jan 15, 2021
 *
 */
@Repository
public interface CanOng01Repository extends CommonRepository<CanOng01Split, Long> {
    Optional<CanOng01Split> findTopByOrderByIdDesc();
}
