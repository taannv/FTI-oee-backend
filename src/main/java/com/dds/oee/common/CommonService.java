package com.dds.oee.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.dds.oee.exception.NotFoundEntityException;
import com.querydsl.core.types.Predicate;

/**
 * Author : duybv
 * Oct 4, 2019
 */

public abstract class CommonService<E, ID, R extends CommonRepository<E, ID>> {

	private static final int DEFAULT_OFFSET = 0;
	private static final int DEFAULT_LIMIT = 20;

	protected final R repo;

	protected CommonService(R repo) {
		this.repo = repo;
	}

	public E save(E entity) {
		repo.save(entity);
		return entity;
	}

	public List<E> save(List<E> entities) {
		repo.saveAll(entities);
		return entities;
	}

	public Optional<E> get(ID id) {
		return repo.findById(id);
	}

	public E getOrElseThrow(ID id) throws NotFoundEntityException {
		return get(id)
				.orElseThrow(NotFoundEntityException.ofSupplier(notFoundMessage(id)));
	}

	public E delete(E entity) {
		repo.delete(entity);
		return entity;
	}

	public E deleteIfExisted(ID id) {
		Optional<E> optional = get(id);
		if (!optional.isPresent()) {
			return null;
		}
		E entity = optional.get();
		return delete(entity);
	}

	public Page<E> query(Order...orders) {
		return query(null, null, orders);
	}

	public Page<E> query(Integer offset, Integer limit, Order...orders) {
		return query(null, offset, limit, orders);
	}

	public Page<E> query(Predicate condition, Integer offset, Integer limit, Order...orders) {
		if (offset == null) {
			offset = DEFAULT_OFFSET;
		}
		if (limit == null) {
			limit = DEFAULT_LIMIT;
		}
		Pageable pageable = PageRequest.of(offset, limit, Sort.by(orders));
		return condition != null ? repo.findAll(condition, pageable) : repo.findAll(pageable);
	}

	public List<E> all(Order...orders) {
		List<E> result = new LinkedList<>();

		Page<E> page = query(orders);
		while (page.hasContent()) {
			result.addAll(page.getContent());

			Pageable nextPageable = page.getPageable().next();
			page = repo.findAll(nextPageable);
		}
		return result;
	}

	protected abstract String notFoundMessage(ID id);
}
