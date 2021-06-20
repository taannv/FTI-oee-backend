package com.dds.oee.common;

import com.dds.oee.utils.DateUtils;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Sort.Order;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
* Author : duybv
* Oct 12, 2019
*/

public abstract class CommonProcessor<S, QE, T> {

	protected final S service;
	protected final QE Q;
	protected final T transformer;

	public CommonProcessor(S service, QE Q, T transformer) {
		this.service = service;
		this.Q = Q;
		this.transformer = transformer;
	}

	protected String getFieldName(Path<?> fieldExpression) {
		return fieldExpression.getMetadata().getName();
	}

	protected Order desc(Path<?> fieldExpression) {
		return Order.desc(getFieldName(fieldExpression));
	}

	protected Order asc(Path<?> fieldExpression) {
		return Order.asc(getFieldName(fieldExpression));
	}

	protected Predicate buildDateRangeCondition(Long fromDate, Long toDate, DateTimePath<LocalDateTime> fieldExpression) {
		if (fromDate == null && toDate == null) {
			return null;
		}

		if (fromDate != null && toDate != null) {
			return fieldExpression.between(DateUtils.atStartOfDay(fromDate), DateUtils.atEndOfDay(toDate));
		}
		if (toDate != null) {
			return fieldExpression.loe(DateUtils.atEndOfDay(toDate));
		}
		if (fromDate != null) {
			return fieldExpression.goe(DateUtils.atStartOfDay(fromDate));
		}

		return null;
	}

	protected Predicate buildKeywordCondition(String keyword, StringPath... fieldExpressions) {
		if (StringUtils.isEmpty(keyword)) {
			return null;
		}
		BooleanBuilder subCondition = new BooleanBuilder();
		for (StringPath fieldExpression : fieldExpressions) {
			subCondition.or(fieldExpression.contains(keyword));
		}
		return subCondition;
	}

}
