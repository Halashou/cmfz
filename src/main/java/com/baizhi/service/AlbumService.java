package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    Map findAll(Integer page,Integer rows);
    List<Album> findAllAlbums();
    Album findOne(Album album);
}
