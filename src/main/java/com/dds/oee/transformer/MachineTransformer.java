package com.dds.oee.transformer;

import com.dds.oee.api.MachineApi.CreateModel;
import com.dds.oee.entity.Machine;
import com.dds.oee.entity.MachineStatus;
import com.dds.oee.payload.MachineModel;
import com.dds.oee.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author : duybv
 * Sep 16, 2019
 */

@Component
public class MachineTransformer {

	public Machine toEntity(CreateModel model) {
		Machine entity = new Machine();

		entity.setName(model.getName());
		entity.setStatus(MachineStatus.ACTIVE.getValue());
		entity.setDescription(model.getDescription());

		return entity;
	}

	public List<MachineModel> toModels(List<Machine> entities) {
		return entities.stream().map(this::toModel).collect(Collectors.toList());
	}

	public List<MachineModel> toModels(Set<Machine> entities) {
		return entities.stream().map(this::toModel).collect(Collectors.toList());
	}

	public MachineModel toModel(Machine entity) {
		MachineModel model = new MachineModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setDescription(entity.getDescription());

		MachineStatus status = MachineStatus.getStatus(entity.getStatus());
		model.setStatus(status.getValue());
		model.setStatusText(status.getText());
		model.setStatusColor(status.getColor());

		if (entity.getStatusedAt() != null) {
			model.setStatusedAt(DateUtils.toMillis(entity.getStatusedAt()));
		}
		return model;
	}

}
