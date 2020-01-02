package cn.aynu.document.service;

import cn.aynu.commons.beans.Document;
import cn.aynu.document.dao.IDocumentDao;
import cn.aynu.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class DocumentServiceImpl implements IDocumentService {
    @Resource
    private IDocumentDao documentDao;
    @Override
    public List<Document> findDocument(Document document, PageModel pageModel) {
        Map map= new HashMap();
        map.put("document",document);
        map.put("pageModel",pageModel);
        return documentDao.selectDocument(map);
    }

    @Override
    public int findDocumentCount(String title) {
        return documentDao.selectDocumentCount(title);
    }

    @Override
    public Document findDocumentById(Integer id) {
        return documentDao.selectDocumentById(id);
    }

    @Override
    public int updateDucument(Document document) {
        return documentDao.updateDocument(document);
    }

    @Override
    public int removeDocument(int[] ids) {
        return documentDao.deleteDocument(ids);
    }

    @Override
    public int addDocument(Document document) {
        return documentDao.insertDocument(document);
    }
}
