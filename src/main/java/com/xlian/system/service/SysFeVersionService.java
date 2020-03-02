package com.xlian.system.service;

import com.xlian.system.vo.SysFeVersionVO;
import com.xlian.system.model.SysFeVersion;

import java.util.List;

public interface SysFeVersionService {

    SysFeVersion findById(Integer id);

    List<SysFeVersion> findByCondition(SysFeVersionVO sysFeVersionVO);

    void save(SysFeVersion user);

    void update(SysFeVersion user);

    void deleteById(Integer id);

    List<SysFeVersion> findAll(SysFeVersionVO sysFeVersionVO);

}