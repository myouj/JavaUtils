package com.ma.phoenix.test.service;

import com.ma.phoenix.test.pojo.Retail30daysAnalysis;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author autoGenerate
 * @since 2019-04-03
 */
public interface IRetail30daysAnalysisService extends IService<Retail30daysAnalysis> {

    List<Retail30daysAnalysis> selectAll();

}
