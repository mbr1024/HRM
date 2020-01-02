package cn.aynu.document.dao;

import cn.aynu.commons.beans.Document;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface IDocumentDao {

    List<Document> selectDocument(Map map);

    int selectDocumentCount(@Param(value="title")String title);

    Document selectDocumentById(Integer id);

    int updateDocument(Document document);

    int deleteDocument(int[] ids);

    int insertDocument(Document document);
}
