package com.ma.phoenix.test.controller;


import com.ma.phoenix.test.pojo.Retail30daysAnalysis;
import com.ma.phoenix.test.service.IRetail30daysAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author autoGenerate
 * @since 2019-04-03
 */
@RestController
@RequestMapping("/retail30daysAnalysis")
public class Retail30daysAnalysisController {

    @Autowired
    private IRetail30daysAnalysisService retail30daysAnalysisService;

    @GetMapping("/selectAll")
    public List<Retail30daysAnalysis> selectAll(){
        List<Retail30daysAnalysis> list = retail30daysAnalysisService.selectAll();
        return list;
    }

}

