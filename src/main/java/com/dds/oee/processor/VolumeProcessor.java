package com.dds.oee.processor;

import com.dds.oee.api.VolumeApi.CreateModel;
import com.dds.oee.api.VolumeApi.QueryModel;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.QVolume;
import com.dds.oee.entity.Volume;
import com.dds.oee.exception.InputInvalidException;
import com.dds.oee.payload.VolumeModel;
import com.dds.oee.security.UserPrincipal;
import com.dds.oee.service.VolumeService;
import com.dds.oee.transformer.VolumeTransformer;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Author : duybv
 * Sep 17, 2019
 */

@Component
public class VolumeProcessor extends CommonProcessor<VolumeService, QVolume, VolumeTransformer> {

	public VolumeProcessor(VolumeService service, VolumeTransformer transform) {
		super(service, QVolume.volume, transform);
	}

	public VolumeModel create(Long machineId, @Valid CreateModel model, UserPrincipal currentUser) {
		if (model.getStartedAt() == null || model.getEndedAt() == null || model.getStartedAt() > model.getEndedAt()) {
			throw InputInvalidException.create("", "Thời gian bắt đầu & kết thúc không phù hợp");
		}

		Volume volume = service.save(transformer.toEntity(machineId, model, currentUser));

		return transformer.toModel(volume);
	}

	public Page<VolumeModel> query(QueryModel model) {
		return service.query(buildCondition(model), model.getPage(), model.getSize(), desc(Q.createdAt))
				.map(transformer::toModel);
	}

	private Predicate buildCondition(QueryModel model) {
		BooleanBuilder condition = new BooleanBuilder();
		if (model.getMachineId() != null) {
			condition.and(Q.machineId.eq(model.getMachineId()));
		}
		buildSubCondition(model).ifPresent(sub -> condition.and(sub));
		return condition;
	}

	private Optional<BooleanBuilder> buildSubCondition(QueryModel model) {
		if (model.getKeyword() == null || model.getKeyword().isEmpty()) {
			return Optional.empty();
		}

		BooleanBuilder subCondition = new BooleanBuilder();
		//TODO add condition by keyword
		//...
		return Optional.of(subCondition);
	}


}
