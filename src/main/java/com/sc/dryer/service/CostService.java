package com.sc.dryer.service;
import com.sc.dryer.pojo.Cost;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CostService {


    List<Cost> selectAll(Integer page, Integer limit);

    String del(String[] id);

    void up(MultipartFile file, HttpServletRequest request);


    void add(Cost cost);

    List<Cost> selectByLike(String time, Integer page, Integer limit);
    //统计数据库中的数据条数
    Integer selectCount();
}
