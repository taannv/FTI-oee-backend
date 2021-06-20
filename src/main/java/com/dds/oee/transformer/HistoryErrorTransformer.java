package com.dds.oee.transformer;

import com.dds.oee.api.HistoryErrorApi.CreateModel;
import com.dds.oee.entity.HistoryError;
import com.dds.oee.entity.Machine;
import com.dds.oee.payload.HistoryErrorModel;
import com.dds.oee.security.UserPrincipal;
import com.dds.oee.service.MachineService;
import com.dds.oee.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author : duybv
 * Oct 4, 2019
 */

@Component
public class HistoryErrorTransformer {

  @Autowired
  private MachineService machineService;

  public HistoryError toEntity(CreateModel model, Long machineId, UserPrincipal currentUser) {
    HistoryError entity = new HistoryError();
    entity.setMachineId(machineId);
    entity.setCreatedBy(currentUser.getUsername());
    entity.setStartedAt(DateUtils.at(model.getStartedAt()));
    entity.setEndedAt(DateUtils.at(model.getEndedAt()));
    entity.setCause(model.getCause());

    return entity;
  }

  public HistoryErrorModel toModel(HistoryError history, Machine machine) {
    return HistoryErrorModel.builder()
            .id(history.getId())
            .machineName(machine.getName())
            .createdBy(history.getCreatedBy())
            .startedAt(DateUtils.toMillis(history.getStartedAt()))
            .endedAt(DateUtils.toMillis(history.getEndedAt()))
            .cause(history.getCause())
            .build();
  }

  public HistoryErrorModel toModel(HistoryError entity) {
    Machine machine = machineService.loadOrElseThrows(entity.getMachineId());
    return toModel(entity, machine);
  }

}
