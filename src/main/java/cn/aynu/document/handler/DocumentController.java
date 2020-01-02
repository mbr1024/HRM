package cn.aynu.document.handler;

import cn.aynu.commons.beans.Document;
import cn.aynu.commons.beans.User;
import cn.aynu.document.service.IDocumentService;
import cn.aynu.utils.PageModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("/document")
@Controller
public class DocumentController {
    @Autowired
    private IDocumentService documentService;

    @RequestMapping("/findDocument.do")
    public String findDocument(Document document, String title, Model model, @RequestParam(defaultValue = "1") Integer pageIndex) throws Exception {
        //System.out.println(title);
        // if (1==1){
        //     throw new Exception();
        // }
        PageModel pageModel = new PageModel();
        int count = documentService.findDocumentCount(title);
        pageModel.setRecordCount(count);
        pageModel.setPageIndex(pageIndex);
        List<Document> documents = documentService.findDocument(document, pageModel);
        model.addAttribute("title", title);
        model.addAttribute("documents", documents);
        model.addAttribute("pageModel", pageModel);
        return "/jsp/document/document.jsp";
    }

    @RequestMapping("/updateDocument.do")
    public String updateDocument(Integer flag, Integer id, Document document, Model model) {
        if (flag == 1) {
            document = documentService.findDocumentById(id);
            // System.out.println(document);
            model.addAttribute("document", document);
            return "/jsp/document/showUpdateDocument.jsp";
        }
        //System.out.println(document);
        String path = "E:/upload/";
        String fileName = document.getFile().getOriginalFilename();
        //System.out.println(fileName+"上传的文件名");
        if (fileName != null&&!fileName.equals("")) {
            try {
                //利用文件保存路径+文件名删除文件
                File file = new File(path + document.getFilename());
                if (file.exists()) {
                    file.delete();
                }
                //设置新的文件名字
                long random = System.currentTimeMillis();
                document.setFilename(random + fileName);
                //保存文件
                document.getFile().transferTo(new File(path, random + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(document.getFilename()+"要修改document文件名");
        int rows = documentService.updateDucument(document);
        if (rows > 0) {
            return "redirect:findDocument.do";
        } else {
            model.addAttribute("message", "修改失败!");
            return "/error.jsp";
        }
    }

    @RequestMapping("/removeDocument.do")
    public String removeDocument(int[] ids, Model model) {
        String path = "E:/upload/";
        Document document = new Document();
        for (Integer id : ids
        ) {
            document = documentService.findDocumentById(id);
            System.out.println(document);
            File file = new File(path + document.getFilename());
            if (file.exists()) {
                file.delete();
            }
        }
        int rows = documentService.removeDocument(ids);

        if (rows == ids.length) {
            return "redirect:/document/findDocument.do";
        } else {
            model.addAttribute("message", "删除失败");
            return "/error.jsp";
        }
    }

    @RequestMapping("/addDocument.do")
    public String addDocument(Model model, HttpSession session, Document document) {
        //System.out.println(document);
        String path = "E:/upload";
        File upload = new File(path);
        if (!upload.exists()) {
            upload.mkdirs();
        }
        String fileName = document.getFile().getOriginalFilename();
        long random = System.currentTimeMillis();
        document.setFilename(random + fileName);
        User user_session = (User) session.getAttribute("user_session");
        document.setUser(user_session);
        int rows = documentService.addDocument(document);
        try {
            //保存文件
            document.getFile().transferTo(new File(path, random + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (rows > 0) {
            return "redirect:/document/findDocument.do";
        } else {
            model.addAttribute("message", "添加文档失败");
            return "error.jsp";
        }
    }

    @RequestMapping("/downLoad.do")
    public ResponseEntity<byte[]> doLoad(Integer id, HttpServletRequest request) throws IOException {
        Document document = documentService.findDocumentById(id);
        String filename = document.getFilename();
        String path = "E:/upload";
        File file = new File(path, filename);
        //调用文件名转换方法防止文件名出现乱码
        String downloadFilename = processFileName(filename, request);
        HttpHeaders headers = new HttpHeaders();
        //设置响应方式
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //设置响应文件,以及默认保存的文件名
        headers.setContentDispositionFormData("attachment", downloadFilename);
        //以二进制形式响应文件
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }

    //解决下载中文文件名乱码问题
    public String processFileName(String filename, HttpServletRequest request) {
        String codeFileName = null;
        String agent = request.getHeader("USER-AGENT");
        try {
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent && -1 != agent.indexOf("Trident")) {
                //IE需要使用URL编码
                String name = java.net.URLEncoder.encode(filename, "UTF-8");
                codeFileName = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
                //火狐或chrome直接还原编码格式为iso-8859-1
                codeFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeFileName;
    }
}


