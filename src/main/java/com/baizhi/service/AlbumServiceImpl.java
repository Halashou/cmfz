package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Override
    public Map findAll(Integer page, Integer rows){
        Map map=new HashMap();
        List<Album> albums = albumDao.selectAll();
        Integer records=albumDao.selectCount(new Album());
        Integer total=records%rows==0?records/rows:records/rows+1;
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",albums);
        //records page rows total
        return map;
    }

    @Override
    public List<Album> findAllAlbums() {
        List<Album> albums = albumDao.selectAll();
        return albums;
    }

    @Override
    public Album findOne(Album album) {
        Album a = albumDao.selectByPrimaryKey(album);
        return a;
    }
}
