package com.dds.oee.api;

import com.dds.oee.common.CommonQuery;
import com.dds.oee.entitycanong01.CanOng01ProductionOrder;
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
 * January 15, 2021
 *
 */
@Api(tags = "CanOng01Api")
@RestController
@RequestMapping("/api")
public class CanOng01Api {

    private final CanOng01Processor canOng01Processor;
    private final CanOng01ProductionOrderProcessor canOng01ProductionOrderProcessor;
    private final CanOng01ErrorProcessor canOng01ErrorProcessor;
    private final CanOng01StatusProcessor canOng01StatusProcessor;
    private final CanOng01SpeedProcessor canOng01SpeedProcessor;

    public CanOng01Api(CanOng01Processor canOng01Processor, CanOng01ProductionOrderProcessor canOng01ProductionOrderProcessor, CanOng01ErrorProcessor canOng01ErrorProcessor, CanOng01StatusProcessor canOng01StatusProcessor, CanOng01SpeedProcessor canOng01SpeedProcessor) {
        this.canOng01Processor = canOng01Processor;
        this.canOng01ProductionOrderProcessor = canOng01ProductionOrderProcessor;
        this.canOng01ErrorProcessor = canOng01ErrorProcessor;
        this.canOng01StatusProcessor = canOng01StatusProcessor;
        this.canOng01SpeedProcessor = canOng01SpeedProcessor;
    }

    @GetMapping("/canong01/list")
    @ApiOperation(value = "Get CanOng01. ", response = CanOng01Model.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all canong01 successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng01Model getCanOng01() {
        return canOng01Processor.getAllCanOng01();
    }

    @GetMapping("/canong01/production")
    @ApiOperation(value = "Get Production Order. ", response = CanOng01ProductionOrder.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all machines successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng01ProductionModel getCanOng01Production() {
        try {
            return canOng01ProductionOrderProcessor.getCanOng01ProductionOrder();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/time/produced")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report Produced. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all produced successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<CanOng01Model> reportTimeProducedC1(@Valid @ModelAttribute QueryModel queryModel) {
        try {
            return canOng01Processor.query(queryModel);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/canong01/errors")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report errors. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Error successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<CanOng01SplitErrorModel> reportError(@Valid @ModelAttribute QueryModel queryModel) {
        try {
            Page<CanOng01SplitErrorModel> page = canOng01ErrorProcessor.queryError(queryModel);
            return page;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/canong01/production/orders")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report Production Order. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<CanOng01ProductionModel> reportProduce(@Valid @ModelAttribute QueryModel queryModel) {
        try {
            return canOng01ProductionOrderProcessor.getReports(queryModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/canong01/status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng01StatusModel getCanOng01Status() {
        try {
            return canOng01StatusProcessor.getCanOng01Status();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/canong01/speed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng01SpeedModel getSpeed(){
        try {
            return canOng01SpeedProcessor.getSpeed();
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
