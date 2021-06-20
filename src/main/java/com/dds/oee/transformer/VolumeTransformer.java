package com.dds.oee.transformer;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.dds.oee.api.VolumeApi.CreateModel;
import com.dds.oee.entity.Volume;
import com.dds.oee.payload.VolumeModel;
import com.dds.oee.security.UserPrincipal;

/**
 * Author : duybv
 * Oct 6, 2019
 */

@Component
public class VolumeTransformer {

	public VolumeModel toModel(Volume entity) {
		return VolumeModel.builder()
				.machineId(entity.getMachineId())
				.createdBy(entity.getCreatedBy())
				.input(entity.getInput())
				.output(entity.getOutput())
				.startedAt(entity.getStartedAt().getTime())
				.endedAt(entity.getEndedAt().getTime())
				.command(entity.getCommand())
				.note(entity.getNote())
				.build();
	}

	public Volume toEntity(Long machineId, CreateModel model, UserPrincipal currentUser) {
		Volume entity = new Volume();

		entity.setMachineId(machineId);
		entity.setCreatedBy(currentUser.getUsername());
		entity.setInput(model.getInput());
		entity.setOutput(model.getOutput());
		entity.setStartedAt(new Date(model.getStartedAt()));
		entity.setEndedAt(new Date(model.getEndedAt()));
		entity.setCommand(model.getCommand());
		entity.setNote(model.getNote());

		return entity;
	}

}
