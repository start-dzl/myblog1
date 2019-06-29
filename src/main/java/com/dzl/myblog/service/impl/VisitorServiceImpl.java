package com.dzl.myblog.service.impl;

import com.dzl.myblog.entity.Visitor;
import com.dzl.myblog.mapper.VisitorMapper;
import com.dzl.myblog.service.VisitorService;
import net.sf.json.JSONObject;
import org.apache.ibatis.binding.BindingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    private Logger logger = LoggerFactory.getLogger(VisitorServiceImpl.class);

    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public void addVisitorNumByPageName(String pageName, HttpServletRequest request) {

        String visitor;
        if("visitorVolume".equals(pageName)){
            visitor = (String) request.getSession().getAttribute("visitor");
            if(visitor == null){
                visitorMapper.updateVisitorNumByTotalVisitorAndPageName(pageName);
                request.getSession().setAttribute("visitor","yes");
            }else {
                visitorMapper.updateVisitorNumByTotalVisitor();
            }
        } else {
            visitor = (String) request.getSession().getAttribute(pageName);
            if(visitor == null){
                visitorMapper.updateVisitorNumByTotalVisitorAndPageName(pageName);
                request.getSession().setAttribute(pageName, "yes");
            } else {
                visitorMapper.updateVisitorNumByTotalVisitor();
            }
        }

    }

    @Override
    public JSONObject getVisitorNumByPageName(String pageName) {

        JSONObject jsonObject = new JSONObject();
        long totalVisitor = visitorMapper.getVisitorNumByPageName("totalVisitor");
        long pageVisitor;
        try {
            pageVisitor = visitorMapper.getVisitorNumByPageName(pageName);
            jsonObject.put("totalVisitor", totalVisitor);
            jsonObject.put("pageVisitor", pageVisitor);
        } catch (BindingException e){
            logger.info("Page '" + pageName + "' is not exist");
        }
        return jsonObject;
    }

    @Override
    public long getNumByPageName(String pageName) {
        return visitorMapper.getVisitorNumByPageName(pageName);
    }

    @Override
    public void insertVisitorArticlePage(String pageName) {
        visitorMapper.insertVisitorArticlePage(pageName);
    }

    @Override
    public long getAllVisitor() {
        return visitorMapper.getAllVisitor();
    }

    @Override
    public void deleteVisitorArticlePage(String VisitorPage) {

        if(VisitorPage.equals("totalVisitor")||VisitorPage.equals("visitorWithoutArticle"))
        {
            return;
        }else
        {
            visitorMapper.deleteVisitorArtcle(VisitorPage);
        }

    }

    @Override
    public List<Visitor> grtHotVistor() {
        return visitorMapper.getHotAtrcle();
    }

}
