package com.sc.dryer.service.serviceImpl;

import com.sc.dryer.dao.FixMapper;
import com.sc.dryer.pojo.Fix;

import com.sc.dryer.service.FixService;
import com.sc.dryer.utils.UpUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
public class FixServiceImpl implements FixService{
    @Resource
    private FixMapper fixMapper;
    @Override
    public List<Fix> selectAll() {
        List<Fix> fixes = fixMapper.selectAll();
        return fixes;
    }

    @Override
    public void up(MultipartFile file, HttpServletRequest request) {
        UpUtils.upfile(file,request);
    }

    @Override
    public void add(Fix fix) {
        String s = UUID.randomUUID().toString();
        fix.setId(s);
        String pid = fix.getPid();
        String p="img/"+pid;
        fix.setPid(p);
        fixMapper.insert(fix);
    }

    @Override
    public String del(String[] id) {
        for (int i = 0; i < id.length; i++) {
            Fix fix = fixMapper.selectByPrimaryKey(id[i]);
            if (fix!=null){
                fixMapper.deleteByPrimaryKey(id[i]);
            }else {
                return "请刷新页面";
            }
        }
        return "success";
    }


}
