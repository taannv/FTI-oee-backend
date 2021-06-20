package com.dds.oee.processor;

import com.dds.oee.api.MachineApi.CreateModel;
import com.dds.oee.common.CommonProcessor;
import com.dds.oee.entity.Machine;
import com.dds.oee.entity.QMachine;
import com.dds.oee.entity.User;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.payload.MachineModel;
import com.dds.oee.security.UserPrincipal;
import com.dds.oee.service.MachineService;
import com.dds.oee.service.UserService;
import com.dds.oee.transformer.MachineTransformer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author : duybv
 * Oct 12, 2019
 */

@Component
public class MachineProcessor extends CommonProcessor<MachineService, QMachine, MachineTransformer> {

    private final UserService userService;

    public MachineProcessor(MachineService service, MachineTransformer transform, UserService userService) {
        super(service, QMachine.machine, transform);
        this.userService = userService;
    }

    public void create(CreateModel model) {
        Machine entity = transformer.toEntity(model);
        service.save(entity);
    }

    public List<MachineModel> all() {
        return service.all(desc(Q.createdAt)).stream()
                .map(transformer::toModel).collect(Collectors.toList());
    }

    public List<MachineModel> getByUserName(String username, UserPrincipal currentUser) {
        User user = userService.findByUsernameOrElseThrow(username);
        return transformer.toModels(user.getMachines());
    }

    public MachineModel getByCurrentUser(UserPrincipal currentUser) {
        User user = userService.findByUsernameOrElseThrow(currentUser.getUsername());

        if (CollectionUtils.isEmpty(user.getMachines())) {
            throw NotFoundEntityException.create("UserName", "Không tìm thấy máy được quản lý bởi user: " + currentUser.getUsername());
        }
        return transformer.toModel(user.getMachines().iterator().next());
    }

}
