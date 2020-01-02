package cn.aynu.notice.service;

import cn.aynu.commons.beans.Notice;
import cn.aynu.notice.dao.INoticeDao;
import cn.aynu.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class NoticeServiceImpl implements INoticeService {
    @Resource
    private INoticeDao noticeDao;
    @Override
    public int findNoticeCount(Notice notice) {
        return noticeDao.selectNoticeCount(notice);
    }

    @Override
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        Map map=new HashMap();
        map.put("notice",notice);
        map.put("pageModel",pageModel);
        return noticeDao.selectNotice(map);
    }

    @Override
    public Notice findNoticeById(Integer id) {
        return noticeDao.selectNoticeById(id);
    }

    @Override
    public int updateNotice(Notice notice) {
        return noticeDao.updateNotice(notice);
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeDao.insertNotice(notice);
    }

    @Override
    public int renoveNotice(Integer[] ids) {
        return noticeDao.deleteNotice(ids);
    }
}
