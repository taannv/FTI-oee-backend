package com.dds.oee.service;

import com.dds.oee.api.MachineSplitApi.QueryModel;
import com.dds.oee.common.CommonService;
import com.dds.oee.entity.ProductionChart;
import com.dds.oee.repository.ProductionByMonthRepository;
import com.dds.oee.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 *
 * @author: Nguyen Van Tan
 *
 * May 20, 2020
 *
 */
@Service
public class ProductionByMonthService extends CommonService<ProductionChart, String, ProductionByMonthRepository> {


    protected ProductionByMonthService(ProductionByMonthRepository repo) {
        super(repo);
    }

    public Page<ProductionChart> getProductionByMonth(QueryModel model, Pageable pageable) {
        try {
            LocalDateTime from = DateUtils.atStartOfDay(model.getFromDate());
            LocalDateTime to = DateUtils.atEndOfDay(model.getToDate());
            Page<ProductionChart> page = repo.getProductionByMonth(DateUtils.formatDate(from), DateUtils.formatDate(to),pageable);
            return page;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected String notFoundMessage(String aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
