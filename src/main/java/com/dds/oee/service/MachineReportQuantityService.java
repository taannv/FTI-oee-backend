package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.MachineQuantity;
import com.dds.oee.entity.ProductionOrder;
import com.dds.oee.repository.MachineReportQuantityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Phan Thi Hong
 * <p>
 * May 26, 2020
 */
@Service
public class MachineReportQuantityService extends CommonService<MachineQuantity, String, MachineReportQuantityRepository> {

    public MachineReportQuantityService(MachineReportQuantityRepository repo) {
        super(repo);
    }

    public List<MachineQuantity> getProductionByMonth() {
        try {
            List<MachineQuantity> pro = repo.getProductionByMonth();

            return pro;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected String notFoundMessage(String description) {
        return null;
    }
}
