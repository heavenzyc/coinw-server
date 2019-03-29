package com.heaven.springbootdemo.service;

import com.heaven.springbootdemo.entity.CoinwTrend;

import java.util.List;

public interface CoinwTrendService {

    List<CoinwTrend> getAll();

    CoinwTrend getById(Integer id);
}
