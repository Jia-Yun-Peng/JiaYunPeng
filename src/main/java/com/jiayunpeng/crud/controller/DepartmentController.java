package com.jiayunpeng.crud.controller;

import com.jiayunpeng.crud.bean.Department;
import com.jiayunpeng.crud.bean.Message;
import com.jiayunpeng.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: wangwei
 * @Description:
 * @Time: 2019/9/18 星期三 17:46
 **/
@Controller

public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    
    /**
     * 返回部门信息json数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/depts")
    public Message getDepts(){
        List<Department> list = departmentService.getDepts();
        return Message.success().add("depts",list);
    }
}
