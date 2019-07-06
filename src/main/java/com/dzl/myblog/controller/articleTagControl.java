package com.dzl.myblog.controller;

import com.dzl.myblog.service.ArticeTagService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class articleTagControl {
    @Autowired
    ArticeTagService articeTagService;

    @GetMapping("/GetTagCloud")
    @ResponseBody
    public JSONObject GetTagCloud()
    {
        return articeTagService.findTagsCloud();
    }
}
