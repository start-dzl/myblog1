package com.dzl.myblog.controller;

import com.dzl.myblog.service.ArticleService;
import com.dzl.myblog.service.UserService;
import com.dzl.myblog.service.VisitorService;
import com.dzl.myblog.utils.TransCodingUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Principal;

@Slf4j
@Controller
public class BackController {

    @Autowired
    VisitorService visitorService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;

    /**
     * 跳转首页
     */
    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response,
                        @AuthenticationPrincipal Principal principal) {
        String username = null;
        try {
            username = principal.getName();
        } catch (NullPointerException e) {
            request.getSession().removeAttribute("lastUrl");
            return "index";

        }
        Cookie[] cookies = request.getCookies();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("lastUrl", (String) request.getSession().getAttribute("lastUrl"));
        return "index";
    }

    /**
     * 百度认证
     */
    @GetMapping("/baidu_verify_Wpb1PVCseS")
    public String baidu() {
        return "baidu_verify_Wpb1PVCseS";
    }

    @GetMapping("/adminindex")
    public String adminIndex(HttpServletRequest request, HttpServletResponse response) {
        return "adminindex";
    }

    /**
     * 获得统计信息
     *
     * @return
     */
    @GetMapping("/getStatisticsInfo")
    @ResponseBody
    public JSONObject getStatisticsInfo() {
        JSONObject returnJson = new JSONObject();
        long num = visitorService.getAllVisitor();
        returnJson.put("allVisitor", num);
        returnJson.put("allUser", userService.getNumberCount());
        // returnJson.put("yesterdayVisitor", num);
        returnJson.put("articleNum", articleService.getArticleCount());
        return returnJson;


    }

    /**
     * 跳转标签页
     *
     * @return
     */
    @GetMapping("/tags")
    public String getTags(HttpServletResponse response,
                          HttpServletRequest request) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");

        String tag = request.getParameter("tag");
        try {
            response.setHeader("tag", TransCodingUtil.stringToUnicode(tag));
        } catch (Exception e) {
        }
        return "Tags";
    }

    /**
     * 跳转标签页
     *
     * @return
     */
    @GetMapping("/category")
    public String getCategory(HttpServletResponse response,
                              HttpServletRequest request) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        request.getSession().removeAttribute("lastUrl");

        String Category = request.getParameter("category");
        try {
            response.setHeader("category", TransCodingUtil.stringToUnicode(Category));
        } catch (Exception e) {
        }
        return "Category";
    }

    @GetMapping("/File")
    public String GetFile(HttpServletRequest request, HttpServletResponse response) {
        return "File";
    }

    @PostMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam("fileName") MultipartFile file) {
        if (file.isEmpty()) {
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "C:/test";

        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }

    @GetMapping("/download")
    @ResponseBody
    public String downLoad(HttpServletResponse response) {
        String filename = "MyBlog-master.zip";
        String filePath = "C:/test";
        File file = new File(filePath + "/" + filename);
        if (file.exists()) { //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


}
