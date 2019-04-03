package com.springbootlearn.damo.controller;

import com.springbootlearn.damo.service.IESRestfulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping
public class ESController {

    @Autowired
    private IESRestfulService restfulService;

    @RequestMapping( "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/search")
    public String search(Model model,
            @RequestParam(value = "keyword") String keyword){
        String[] searchFields={"title","filecontent"};
        ArrayList<Map<String,Object>> fileList= restfulService.searchDocs("userdoc",keyword,searchFields,1,10);
        model.addAttribute("flist",fileList);
        model.addAttribute("keyword" ,keyword);
        return "result";
    }
}
