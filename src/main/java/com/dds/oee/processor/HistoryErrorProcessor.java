package com.dds.oee.processor;

import com.dds.oee.api.HistoryErrorApi.CreateModel;
import com.dds.oee.api.HistoryErrorApi.QueryModel;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.HistoryError;
import com.dds.oee.entity.Machine;
import com.dds.oee.entity.QHistoryError;
import com.dds.oee.exception.InputInvalidException;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.payload.HistoryErrorModel;
import com.dds.oee.security.UserPrincipal;
import com.dds.oee.service.HistoryErrorService;
import com.dds.oee.service.MachineService;
import com.dds.oee.transformer.HistoryErrorTransformer;
import com.dds.oee.utils.DateUtils;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Author : duybv
 * Oct 12, 2019
 */

@Component
public class HistoryErrorProcessor extends CommonProcessor<HistoryErrorService, QHistoryError, HistoryErrorTransformer>{

	private final MachineService machineService;

	public HistoryErrorProcessor(HistoryErrorService service, HistoryErrorTransformer transform, MachineService machineService) {
		super(service, QHistoryError.historyError, transform);
		this.machineService = machineService;
	}

	public HistoryErrorModel create(Long machineId, CreateModel model, UserPrincipal currentUser) throws NotFoundEntityException {
		if (model.getStartedAt() == null || model.getEndedAt() == null || model.getStartedAt() > model.getEndedAt()) {
			throw InputInvalidException.create("", "Thời gian bắt đầu & kết thúc không phù hợp");
		}

		Machine machine = machineService.loadOrElseThrows(machineId);
		HistoryError history = service.save(transformer.toEntity(model, machine.getId(), currentUser));

		return transformer.toModel(history, machine);
	}

	public Page<HistoryErrorModel> query(Long machineId, QueryModel model) {
		BooleanBuilder condition = new BooleanBuilder();
		condition.and(Q.machineId.eq(machineId));
		condition.and(buildSubCondition(model));

		return service.query(condition, model.getPage(), model.getSize(), desc(Q.createdAt))
				.map(transformer::toModel);
	}

	private BooleanBuilder buildSubCondition(QueryModel model) {
		BooleanBuilder subCondition = new BooleanBuilder();
		if (!StringUtils.isEmpty(model.getKeyword())) {
			subCondition.and(Q.cause.contains(model.getKeyword()));
		}
		if (model.getFromDate() != null && model.getToDate() != null) {
			subCondition.and(Q.startedAt.after(DateUtils.atStartOfDay(model.getFromDate())).and(Q.endedAt.before(DateUtils.atEndOfDay(model.getToDate()))));
		} else if (model.getFromDate() != null) {
			subCondition.and(Q.startedAt.after(DateUtils.atStartOfDay(model.getFromDate())));
		} else if (model.getToDate() != null) {
			subCondition.and(Q.endedAt.before(DateUtils.atEndOfDay(model.getToDate())));
		}
		return subCondition;
	}

}
