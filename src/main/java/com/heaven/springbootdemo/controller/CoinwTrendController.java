package com.heaven.springbootdemo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.heaven.springbootdemo.common.Message;
import com.heaven.springbootdemo.entity.CoinwTrend;
import com.heaven.springbootdemo.entity.vo.CoinwTrendVO;
import com.heaven.springbootdemo.service.CoinwTrendService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
    public List<CoinwTrendVO> searchTrendList(@PathVariable String board) {
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

    private JSONArray buildDate(CoinwTrend ct, JSONArray date) {
        date.add(ct.getDt().substring(4));
        return date;
    }

    private JSONArray buildLow(CoinwTrend ct, JSONArray low) {
        low.add(ct.getLow());
        return low;
    }

    private JSONArray buildHigh(CoinwTrend ct, JSONArray high) {
        high.add(ct.getHigh());
        return high;
    }

    @RequestMapping(value = "/trend/{board}")
    @ResponseBody
    public Message getAllTrend(@PathVariable String board, HttpServletResponse response) {
//        String[] boards = {"AE", "BTC", "COINS", "ENJ", "EOS", "ETH", "LTC", "OMG", "TNT"};
        List<CoinwTrend> trends = coinwTrendService.getByBoard(board);
        Message msg = new Message();
        msg.setCode("0000");
        JSONObject data =  new JSONObject();
        JSONArray date = new JSONArray();
        trends.stream().forEach(x -> buildDate(x, date));
        data.put("date", date);

        JSONArray low = new JSONArray();
        trends.stream().forEach(x -> buildLow(x, low));
        data.put("low", low);

        JSONArray high = new JSONArray();
        trends.stream().forEach(x -> buildHigh(x, high));
        data.put("high", high);

        data.put("name", board);
        msg.setData(data);
        msg.setMsg("success");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        return msg;
    }
}
