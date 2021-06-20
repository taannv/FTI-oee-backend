package com.dds.oee.service;

import com.dds.oee.api.CanOng01Api;
import com.dds.oee.api.CanOng02Api;
import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong01.CanOng01ProductionOrder;
import com.dds.oee.entitycanong02.CanOng02ProductionOrder;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong02.CanOng02ProductionOrderRepository;
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
public class CanOng02ProductOrderService extends CommonService<CanOng02ProductionOrder, Long, CanOng02ProductionOrderRepository> {


    protected CanOng02ProductOrderService(CanOng02ProductionOrderRepository repo) {
        super(repo);
    }

    public CanOng02ProductionOrder findTopByDate1EqualsOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    public Page<CanOng02ProductionOrder> getReportsC2(CanOng02Api.QueryModel model, Pageable pageable) {
        try {
            Page<CanOng02ProductionOrder> page = repo.getReportsC2(DateUtils.atStartOfDay(model.getFromDate()), DateUtils.atStartOfDay(model.getToDate()), model.getHFrom(), model.getHTo(), model.getWFrom(), model.getWTo(), pageable);
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
