package com.dds.oee.service;

import com.dds.oee.api.MachineSplitApi;
import com.dds.oee.common.CommonService;
import com.dds.oee.entity.ProductionOrder;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repository.ProductionOrderRepository;
import com.dds.oee.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderService extends CommonService<ProductionOrder, Long, ProductionOrderRepository> {

    public ProductOrderService(ProductionOrderRepository repo) {
        super(repo);
    }

    public ProductionOrder findTopByDate1EqualsOrderByIdDesc() {
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }

    public Page<ProductionOrder> getReports(MachineSplitApi.QueryModel model, Pageable pageable) {
        try {
            Page<ProductionOrder> page = repo.getReports(DateUtils.atStartOfDay(model.getFromDate()), DateUtils.atStartOfDay(model.getToDate()), model.getHFrom(), model.getHTo(), model.getWFrom(), model.getWTo(), pageable);
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
