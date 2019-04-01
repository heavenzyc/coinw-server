package com.heaven.springbootdemo.controller;

import com.heaven.springbootdemo.entity.CoinwTrend;
import com.heaven.springbootdemo.entity.vo.CoinwTrendVO;
import com.heaven.springbootdemo.service.CoinwTrendService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableAutoConfiguration
@RequestMapping("/coinw")
public class CoinwTrendController {

    @Resource
    private CoinwTrendService coinwTrendService;

    @RequestMapping(value = "/{id}")
    @ResponseBody
    public CoinwTrend getCoinwTrendById(@PathVariable Integer id) {
        return coinwTrendService.getById(id);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<CoinwTrend> getCoinwTrendList() {
        return coinwTrendService.getAll();
    }

    @RequestMapping(value = "/search/{board}")
    @ResponseBody
    public List<CoinwTrendVO> getCoinwTrendList(@PathVariable String board) {
        List<CoinwTrend> list = coinwTrendService.getAll();
        List<CoinwTrendVO> cts = new ArrayList<>();
        list.stream().filter(s -> s.getBoard().equals(board.toUpperCase())).forEach(x -> build(x, cts));
        return cts;
    }

    private List<CoinwTrendVO> build(CoinwTrend ct, List<CoinwTrendVO> vos) {
        CoinwTrendVO vo = new CoinwTrendVO();
        vo.setDt(ct.getDt());
        vo.setLow(ct.getLow());
        vos.add(vo);
        return vos;
    }
}
