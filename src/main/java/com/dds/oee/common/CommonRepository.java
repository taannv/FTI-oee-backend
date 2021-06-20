package com.dds.oee.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Author : duybv
 * Mar 28, 2019
 */

@NoRepositoryBean
public interface CommonRepository<E, ID> extends JpaRepository<E, ID>, QuerydslPredicateExecutor<E> {

}