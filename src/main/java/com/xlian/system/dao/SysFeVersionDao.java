package com.xlian.system.dao;

import com.xlian.system.vo.SysFeVersionVO;
import com.xlian.system.model.SysFeVersion;

import java.util.List;

public interface SysFeVersionDao {

    SysFeVersion findById(Integer id);

    List<SysFeVersion> findByCondition(SysFeVersionVO sysFeVersionVO);

    void save(SysFeVersion user);

    int update(SysFeVersion user);

    int deleteById(Integer id);

}