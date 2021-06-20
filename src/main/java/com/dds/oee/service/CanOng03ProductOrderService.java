package com.dds.oee.service;

import com.dds.oee.api.CanOng02Api;
import com.dds.oee.api.CanOng03Api;
import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong02.CanOng02ProductionOrder;
import com.dds.oee.entitycanong03.CanOng03ProductionOrder;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong02.CanOng02ProductionOrderRepository;
import com.dds.oee.repositorycanong03.CanOng03ProductionOrderRepository;
import com.dds.oee.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2021
 *
 */
@Service
public class CanOng03ProductOrderService extends CommonService<CanOng03ProductionOrder, Long, CanOng03ProductionOrderRepository> {


    protected CanOng03ProductOrderService(CanOng03ProductionOrderRepository repo) {
        super(repo);
    }

    public CanOng03ProductionOrder findTopByDate1EqualsOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    public Page<CanOng03ProductionOrder> getReportsC3(CanOng03Api.QueryModel model, Pageable pageable) {
        try {
            Page<CanOng03ProductionOrder> page = repo.getReportsC3(DateUtils.atStartOfDay(model.getFromDate()), DateUtils.atStartOfDay(model.getToDate()), model.getHFrom(), model.getHTo(), model.getWFrom(), model.getWTo(), pageable);
            return page;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
