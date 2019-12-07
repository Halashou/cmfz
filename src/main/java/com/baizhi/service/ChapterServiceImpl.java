package com.baizhi.service;

import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map findAll(Integer page, Integer rows,String id) {
        Map map=new HashMap();
        List<Chapter> chapters = chapterDao.select(new Chapter().setAlbum_id(id));
        Integer records=chapterDao.selectCount(new Chapter());
        Integer total=records%rows==0?records/rows:records/rows+1;
        map.put("page",page);
        map.put("records",records);
        map.put("total",total);
        map.put("rows",chapters);
        return map;
    }
    @Override
    public Map add(Chapter chapter) {
        Map map =new HashMap();
        String s = UUID.randomUUID().toString();
        chapter.setId(s);
        chapterDao.insert(chapter);
        map.put("status",200);
        map.put("chapterId",s);
        return map;
    }

    @Override
    public void updateById(Chapter chapter) {
        chapterDao.updateByPrimaryKeySelective(chapter);
    }

    @Override
    public List<Chapter> findChaptersByalbumId(Chapter chapter) {
        List<Chapter> select = chapterDao.select(chapter);
        return select;
    }
}
