package com.ma.phoenix.test.dao;

import com.ma.phoenix.test.pojo.Retail30daysAnalysis;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author autoGenerate
 * @since 2019-04-03
 */
public interface Retail30daysAnalysisMapper extends BaseMapper<Retail30daysAnalysis> {

    List<Retail30daysAnalysis> selectAll();

}
