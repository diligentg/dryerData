package com.sc.dryer.service;

import com.sc.dryer.pojo.Fix;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface FixService {
    List<Fix> selectAll();

    void up(MultipartFile file, HttpServletRequest request);


    void add(Fix fix);

    String del(String[] id);
}
