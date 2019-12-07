package com.baizhi.service;

import com.baizhi.annotation.LogAnnotation;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;
    @Override
    @LogAnnotation("findAll查找banner")
    public Map findAll(Integer page, Integer rows){
        //records rows total page
        HashMap map = new HashMap();
        List<Banner> banners = bannerDao.selectByRowBounds(new Banner(), new RowBounds((page - 1) * rows, rows));
        Integer records=bannerDao.selectCount(new Banner());
        Integer total=records%rows==0?records/rows:records/rows+1;
        map.put("page",page);
        map.put("total",total);
        map.put("rows",banners);
        map.put("records",records);
        return map;
    }

    @Override
    public Map add(Banner banner){
        //设置id
        String s = UUID.randomUUID().toString();
        banner.setId(s);
        //set create_date
        long time = new Date().getTime();
        banner.setCreate_date(new java.sql.Date(time));
        //插入banner对象
        bannerDao.insert(banner);
        HashMap map =new HashMap();
        //返回状态和插入的id是多少  回传
        map.put("bannerId",s);
        map.put("status",200);
        return map;
    }

    @Override
    public Map update(Banner banner) {
        banner.setUrl(null);
        bannerDao.updateByPrimaryKeySelective(banner);
        HashMap map =new HashMap();
        map.put("status",200);
        return map;
    }

    @Override
    public void updateByPkey(Banner banner) {
        bannerDao.updateByPrimaryKeySelective(banner);
    }

    @Override
    public Map delete(String[] id) {
        bannerDao.deleteByIdList(Arrays.asList(id));
        HashMap map =new HashMap();
        map.put("status",200);
        return map;
    }

    @Override
    public List<Banner> findAll() {
        List<Banner> banners = bannerDao.selectAll();
        return banners;
    }
}
