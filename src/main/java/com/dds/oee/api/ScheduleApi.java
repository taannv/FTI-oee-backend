package com.dds.oee.api;

import com.dds.oee.payload.ScheduleModel;
import com.dds.oee.processor.ScheduleProcessor;
import com.dds.oee.security.CurrentUser;
import com.dds.oee.security.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Api(tags = "ScheduleApi")
@RestController
@RequestMapping("/api")
public class ScheduleApi {
    @Autowired
    private ScheduleProcessor processor;

    @PostMapping("/schedules")
    @ApiOperation(value = "Create new schedule.", response = ScheduleModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Create new schedule successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public ScheduleModel create(@Valid @RequestBody ScheduleCreateModel model, @CurrentUser UserPrincipal currentUser) {
        return processor.create(model, currentUser);
    }

    @GetMapping("/schedules")
    @ApiOperation(value = "Get list schedule.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get list schedule successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public List<ScheduleModel> getListSchedule() {
        return processor.getListSchedule();
    }

    @ApiOperation(value = "Update schedule.", response = ScheduleModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Update schedule successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping("/schedules/{id}/edit")
    public ScheduleModel update(@PathVariable Long id, @Valid @RequestBody ScheduleCreateModel model, @CurrentUser UserPrincipal currentUser) {
        return processor.update(id, model, currentUser);
    }

    @Setter
    @Getter
    public static class ScheduleQuery {
        private LocalDateTime start;
        private LocalDateTime end;
    }

    @Setter
    @Getter
    public static class ScheduleCreateModel {
        private String title;
        private String content;
        private Long start;
        private Long end;
        private int send_mail;
        private String send_type;
        private String user_maintenance;
        private int status;
    }
}
