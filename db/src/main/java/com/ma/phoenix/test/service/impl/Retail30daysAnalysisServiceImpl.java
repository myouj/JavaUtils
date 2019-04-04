package com.ma.phoenix.test.service.impl;

import com.ma.phoenix.test.pojo.Retail30daysAnalysis;
import com.ma.phoenix.test.dao.Retail30daysAnalysisMapper;
import com.ma.phoenix.test.service.IRetail30daysAnalysisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author autoGenerate
 * @since 2019-04-03
 */
@Service
public class Retail30daysAnalysisServiceImpl extends ServiceImpl<Retail30daysAnalysisMapper, Retail30daysAnalysis> implements IRetail30daysAnalysisService {

    @Autowired
    private Retail30daysAnalysisMapper retail30daysAnalysisMapper;

    @Override
    public List<Retail30daysAnalysis> selectAll() {
        return retail30daysAnalysisMapper.selectAll();
    }
}
