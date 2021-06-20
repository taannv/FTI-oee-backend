package com.dds.oee.api;

import com.dds.oee.common.CommonQuery;
import com.dds.oee.entitycanong02.CanOng02ProductionOrder;
import com.dds.oee.payload.*;
import com.dds.oee.processor.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2021
 *
 */
@Api(tags = "CanOng02Api")
@RestController
@RequestMapping("/api")
public class CanOng02Api {

    private final CanOng02Processor canOng02Processor;
    private final CanOng02ProductionOrderProcessor canOng02ProductionOrderProcessor;
    private final CanOng02ErrorProcessor canOng02ErrorProcessor;
    private final CanOng02StatusProcessor canOng02StatusProcessor;
    private final CanOng02SpeedProcessor canOng02SpeedProcessor;

    public CanOng02Api(CanOng02Processor canOng02Processor, CanOng02ProductionOrderProcessor canOng02ProductionOrderProcessor, CanOng02ErrorProcessor canOng02ErrorProcessor, CanOng02StatusProcessor canOng02StatusProcessor, CanOng02SpeedProcessor canOng02SpeedProcessor) {
        this.canOng02Processor = canOng02Processor;
        this.canOng02ProductionOrderProcessor = canOng02ProductionOrderProcessor;
        this.canOng02ErrorProcessor = canOng02ErrorProcessor;
        this.canOng02StatusProcessor = canOng02StatusProcessor;
        this.canOng02SpeedProcessor = canOng02SpeedProcessor;
    }

    @GetMapping("/canong02/list")
    @ApiOperation(value = "Get CanOng02. ", response = CanOng02Model.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all CanOng02 successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng02Model getCanOng02() {
        return canOng02Processor.getAllCanOng02();
    }

    @GetMapping("/canong02/production")
    @ApiOperation(value = "Get Production Order. ", response = CanOng02ProductionOrder.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all machines successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng02ProductionModel getCanOng02Production() {
        try {
            return canOng02ProductionOrderProcessor.getCanOng02ProductionOrder();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/time/produced/canOng02")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report Produced. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all produced successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<CanOng02Model> reportTimeProducedC2(@Valid @ModelAttribute CanOng02Api.QueryModel queryModel) {
        try {
            return canOng02Processor.query(queryModel);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/canong02/errors")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report errors. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Error successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<CanOng02SplitErrorModel> reportErrorC2(@Valid @ModelAttribute CanOng02Api.QueryModel queryModel) {
        try {
            Page<CanOng02SplitErrorModel> page = canOng02ErrorProcessor.queryError(queryModel);
            return page;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/canong02/production/orders")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report Production Order. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<CanOng02ProductionModel> reportProduceC2(@Valid @ModelAttribute CanOng02Api.QueryModel queryModel) {
        try {
            return canOng02ProductionOrderProcessor.getReportsC2(queryModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/canong02/status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng02StatusModel getCanOng02Status() {
        try {
            return canOng02StatusProcessor.getCanOng02Status();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/canong02/speed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng02SpeedModel getSpeed(){
        try {
            return canOng02SpeedProcessor.getSpeed();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Getter
    @Setter
    public static class QueryModel extends CommonQuery {
        private Long wFrom, wTo, hFrom, hTo;
    }
}
