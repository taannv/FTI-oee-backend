package com.dds.oee.api;

import com.dds.oee.common.CommonQuery;
import com.dds.oee.payload.MachineSpeedModel;
import com.dds.oee.payload.MachineSplitErrorModel;
import com.dds.oee.payload.MachineSplitModel;
import com.dds.oee.payload.MachineStatusModel;
import com.dds.oee.payload.ProductChartModel;
import com.dds.oee.payload.ProductionReportVolumeModel;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "MachineSplitApi")
@RestController
@RequestMapping("/api")
public class MachineSplitApi {
    private final MachineSplitProcessor machineProcessor;
    private final ProductionOrderProcessor productionOrderProcessor;
    private final MachineErrorProcessor machineErrorProcessor;
    private final MachineStatusProcessor machineStatusProcessor;
    private final MachineSpeedProcessor machineSpeedProcessor;
    private final MachineReportQuantityProcessor machineReportQuantityProcessor;
    private final ReportProductionByMonthProcessor reportProductionByMonth;
    private final ProductionVolumeProcessor productionVolumeProcessor;

    MachineSplitApi(MachineSplitProcessor machineProcessor, ProductionOrderProcessor productionOrderProcessor, MachineErrorProcessor machineErrorProcessor, MachineStatusProcessor machineStatusProcessor, MachineSpeedProcessor machineSpeedProcessor, MachineReportQuantityProcessor machineReportQuantityProcessor, ReportProductionByMonthProcessor reportProductionByMonth, ProductionVolumeProcessor productionVolumeProcessor) {
        this.machineProcessor = machineProcessor;
        this.productionOrderProcessor = productionOrderProcessor;
        this.machineErrorProcessor = machineErrorProcessor;
        this.machineStatusProcessor = machineStatusProcessor;
        this.machineSpeedProcessor = machineSpeedProcessor;
        this.machineReportQuantityProcessor = machineReportQuantityProcessor;
        this.reportProductionByMonth = reportProductionByMonth;
        this.productionVolumeProcessor = productionVolumeProcessor;
    }

    @GetMapping("/machinesplit/split")
    @ApiOperation(value = "Get Machine. ", response = MachineSplitModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all machines successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public MachineSplitModel getMachineSplit() {
        return machineProcessor.getAllMachineSplit();
    }

    @GetMapping("/Machinequantity/chart")
    @ApiOperation(value = "Get Machine. ", response = MachineSplitModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all machines successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public List<MachineSplitModel> getProductionByMonth() {
        try {
            return machineReportQuantityProcessor.getProductionByMonth();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/machinesplit/production")
    @ApiOperation(value = "Get Production Order. ", response = MachineSplitModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all machines successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public MachineSplitModel getProduction() {
        try {
            return productionOrderProcessor.getProductionOrder();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/production")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report Produced. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all produced successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<MachineSplitModel> reportTimeProduced(@Valid @ModelAttribute QueryModel queryModel) {
        try {
            return machineProcessor.query(queryModel);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/errors")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report errors. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Error successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<MachineSplitErrorModel> reportError(@Valid @ModelAttribute QueryModel queryModel) {
        try {
            Page<MachineSplitErrorModel> page = machineErrorProcessor.queryError(queryModel);
            return page;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/production/orders")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report Production Order. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<MachineSplitModel> reportProduce(@Valid @ModelAttribute QueryModel queryModel) {
        try {
            return productionOrderProcessor.getReports(queryModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @GetMapping("/machine/status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public MachineStatusModel getMachineStatus() {
        try {
            return machineStatusProcessor.getMachineStatus();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    @GetMapping("/machine/speed")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Order successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public MachineSpeedModel getSpeed(){
        try {
            return machineSpeedProcessor.getSpeed();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @GetMapping("/reports/production/byMonth")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report Production By Month. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production By Month successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<ProductChartModel> reportProductionByMonth(@Valid @ModelAttribute  QueryModel queryModel) {
        try {
            return reportProductionByMonth.getProductionByMonth(queryModel);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("=============22222================");
        }
        return null;
    }

    @GetMapping("/reports/production/volume")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get Report Production Volume. ", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Report Production Volume successfully"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public Page<ProductionReportVolumeModel> getReportProductionVolume(@Valid @ModelAttribute  QueryModel queryModel) {
        try {
            return productionVolumeProcessor.getReportProductionVolume(queryModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;

    }


    @Getter
    @Setter
    public static class QueryModel extends CommonQuery {
        private Long wFrom, wTo, hFrom, hTo;

        public QueryModel() {
        }

        public QueryModel(Long wFrom, Long wTo, Long hFrom, Long hTo) {
            this.wFrom = wFrom;
            this.wTo = wTo;
            this.hFrom = hFrom;
            this.hTo = hTo;
        }
    }


}
