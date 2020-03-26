package com.sc.dryer.service.serviceImpl;

import com.sc.dryer.dao.BalanceMapper;
import com.sc.dryer.pojo.Balance;
import com.sc.dryer.service.BalanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService{
    @Resource
    private BalanceMapper balanceMapper;
    @Override
    public List<Balance> selectAll() {
        List<Balance> balances = balanceMapper.selectAll();
        return balances;
    }
}
