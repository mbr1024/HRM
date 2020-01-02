package cn.aynu.notice.dao;

import cn.aynu.commons.beans.Notice;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface INoticeDao {

    int selectNoticeCount(Notice notice);

    List<Notice> selectNotice(Map map);

    Notice selectNoticeById(Integer id);

    int updateNotice(Notice notice);

    int insertNotice(Notice notice);

    int deleteNotice(Integer[] ids);
}
