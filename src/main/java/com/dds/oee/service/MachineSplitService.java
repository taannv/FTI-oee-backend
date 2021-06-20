package com.dds.oee.service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.MachineSplit;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repository.MachineSplitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MachineSplitService extends CommonService<MachineSplit, Long, MachineSplitRepository> {
    public MachineSplitService(MachineSplitRepository repo) {
        super(repo);
    }

    public MachineSplit findTopByDate1EqualsOrderByIdDesc(){
        return repo.findTopByOrderByIdDesc()
                .orElseThrow(NotFoundEntityException.ofSupplier(this.notFoundMessage((long) 1)));
    }
    public Page<MachineSplit> getListMachine(String date1, String date2, Pageable pageable){
        return repo.getMachineSplitByQuery(date1,date2,pageable);
    }



    @Override
    protected String notFoundMessage(Long aLong) {
        return "Không tìm thấy bản ghi " + aLong;
    }
}
