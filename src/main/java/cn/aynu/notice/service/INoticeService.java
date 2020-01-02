package cn.aynu.notice.service;

import cn.aynu.commons.beans.Notice;
import cn.aynu.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface INoticeService {


    int findNoticeCount(Notice notice);

    List<Notice> findNotice(Notice notice, PageModel pageModel);

    Notice findNoticeById(Integer id);

    int updateNotice(Notice notice);

    int addNotice(Notice notice);

    int renoveNotice(Integer[] ids);
}
