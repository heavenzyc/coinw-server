package com.heaven.springbootdemo.dao;

import com.heaven.springbootdemo.entity.CoinwTrend;

import java.util.List;

public interface CoinwTrendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CoinwTrend record);

    int insertSelective(CoinwTrend record);

    CoinwTrend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoinwTrend record);

    int updateByPrimaryKey(CoinwTrend record);

    List<CoinwTrend> selectAll();

    List<CoinwTrend> selectByBoard(String board);
}