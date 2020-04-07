package com.sc.dryer.service.serviceImpl;

import com.sc.dryer.dao.CostMapper;
import com.sc.dryer.pojo.Cost;
import com.sc.dryer.pojo.Users;
import com.sc.dryer.service.CostService;
import com.sc.dryer.utils.UpUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
public class CostServiceImpl implements CostService{
    @Resource
    private CostMapper costMapper;
    @Override
    public List<Cost> selectAll() {
        List<Cost> costs = costMapper.selectAll();
        return costs;
    }
    @Override
    public String del(String[] id) {
        for (int i = 0; i <id.length ; i++) {
            Cost cost = costMapper.selectByPrimaryKey(id[i]);
            if (cost !=null){
                costMapper.deleteByPrimaryKey(id[i]);
            }else {
                return "请刷新页面";
            }
        }
        return "success";
    }

    @Override
    public void up(MultipartFile file, HttpServletRequest request) {
        UpUtils.upfile(file,request);
    }

    @Override
    public void add(Cost cost) {
        String s = UUID.randomUUID().toString();
        cost.setId(s);
        costMapper.insert(cost);
    }

    @Override
    public List<Cost> selectByLike(String time, Integer page, Integer limit) {
        page=(page-1)*limit;
        List<Cost> costs=costMapper.selectByLike(time,page,limit);
        return costs;
    }

    @Override
    public Integer selectCount() {
        return costMapper.selectCount();
    }


}
