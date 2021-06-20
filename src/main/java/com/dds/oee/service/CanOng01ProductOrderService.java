package com.dds.oee.service;

import com.dds.oee.api.CanOng01Api;
import com.dds.oee.common.CommonService;
import com.dds.oee.entitycanong01.CanOng01ProductionOrder;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repositorycanong01.CanOng01ProductionOrderRepository;
import com.dds.oee.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author: Nguyen Van Tan
 *
 * January 25, 2021
 *
 */
@Service
public class CanOng01ProductOrderService extends CommonService<CanOng01ProductionOrder, Long, CanOng01ProductionOrderRepository> {


    public CanOng01ProductOrderService(CanOng01ProductionOrderRepository repo) {
        super(repo);
    }

    public CanOng01ProductionOrder findTopByDate1EqualsOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    public Page<CanOng01ProductionOrder> getReports(CanOng01Api.QueryModel model, Pageable pageable) {
        try {
            Page<CanOng01ProductionOrder> page = repo.getReports(DateUtils.atStartOfDay(model.getFromDate()), DateUtils.atStartOfDay(model.getToDate()), model.getHFrom(), model.getHTo(), model.getWFrom(), model.getWTo(), pageable);
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
