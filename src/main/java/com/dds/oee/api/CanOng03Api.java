package com.dds.oee.api;

import com.dds.oee.common.CommonQuery;
import com.dds.oee.entitycanong03.CanOng03ProductionOrder;
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
 * February 06, 2031
 *
 */
@Api(tags = "CanOng03Api")
@RestController
@RequestMapping("/api")
public class CanOng03Api {

    private final CanOng03Processor canOng03Processor;
    private final CanOng03ProductionOrderProcessor canOng03ProductionOrderProcessor;
    private final CanOng03ErrorProcessor canOng03ErrorProcessor;
    private final CanOng03SpeedProcessor canOng03SpeedProcessor;
    private final CanOng03StatusProcessor canOng03StatusProcessor;

    public CanOng03Api(CanOng03Processor canOng03Processor, CanOng03ProductionOrderProcessor canOng03ProductionOrderProcessor, CanOng03ErrorProcessor canOng03ErrorProcessor, CanOng03SpeedProcessor canOng03SpeedProcessor, CanOng03StatusProcessor canOng03StatusProcessor) {
        this.canOng03Processor = canOng03Processor;
        this.canOng03ProductionOrderProcessor = canOng03ProductionOrderProcessor;
        this.canOng03ErrorProcessor = canOng03ErrorProcessor;
        this.canOng03SpeedProcessor = canOng03SpeedProcessor;
        this.canOng03StatusProcessor = canOng03StatusProcessor;
    }

    @GetMapping("/canong03/list")
    @ApiOperation(value = "Get CanOng03. ", response = CanOng03Model.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all CanOng03 successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng03Model getCanOng03() {
        return canOng03Processor.getAllCanOng03();
    }

    @GetMapping("/canong03/production")
    @ApiOperation(value = "Get Production Order. ", response = CanOng03ProductionOrder.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all machines successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng03ProductionModel getCanOng03Production() {
        try {
            return canOng03ProductionOrderProcessor.getCanOng03ProductionOrder();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/time/produced/canOng03")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report Produced. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all produced successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<CanOng03Model> reportTimeProducedC3(@Valid @ModelAttribute CanOng03Api.QueryModel queryModel) {
        try {
            return canOng03Processor.query(queryModel);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/canong03/errors")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report errors. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Error successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<CanOng03SplitErrorModel> reportErrorC3(@Valid @ModelAttribute CanOng03Api.QueryModel queryModel) {
        try {
            Page<CanOng03SplitErrorModel> page = canOng03ErrorProcessor.queryError(queryModel);
            return page;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/canong03/production/orders")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report Production Order. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<CanOng03ProductionModel> reportProduceC3(@Valid @ModelAttribute CanOng03Api.QueryModel queryModel) {
        try {
            return canOng03ProductionOrderProcessor.getReportsC3(queryModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/canong03/status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng03StatusModel getCanOng03Status() {
        try {
            return canOng03StatusProcessor.getCanOng03Status();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/canong03/speed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public CanOng03SpeedModel getSpeed(){
        try {
            return canOng03SpeedProcessor.getSpeed();
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
