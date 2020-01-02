package cn.aynu.document.service;

import cn.aynu.commons.beans.Document;
import cn.aynu.utils.PageModel;

import java.util.List;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/1
 */
public interface IDocumentService {


    List<Document> findDocument(Document document, PageModel pageModel);

    int findDocumentCount(String title);

    Document findDocumentById(Integer id);

    int updateDucument(Document document);

    int removeDocument(int[] ids);

    int addDocument(Document document);
}
