package com.heaven.springbootdemo.service.impl;

import com.heaven.springbootdemo.dao.CoinwTrendMapper;
import com.heaven.springbootdemo.entity.CoinwTrend;
import com.heaven.springbootdemo.service.CoinwTrendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("CoinwTrendService")
public class CoinwTrendServiceImpl implements CoinwTrendService {
    @Resource
    private CoinwTrendMapper coinwTrendMapper;


    @Override
    public List<CoinwTrend> getAll() {
        return coinwTrendMapper.selectAll();
    }

    @Override
    public CoinwTrend getById(Integer id) {
        return coinwTrendMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CoinwTrend> getByBoard(String board) {
        return coinwTrendMapper.selectByBoard(board);
    }
}
