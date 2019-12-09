package com.jiayunpeng.crud.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jiayunpeng.crud.bean.Employee;
import com.jiayunpeng.crud.bean.Message;
import com.jiayunpeng.crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @ResponseBody
    @RequestMapping(value = "/checkUser")
    public Message checkUser(@RequestParam("empName")String empName) {
        boolean b = employeeService.checkUser(empName);
        if (b) {
            return Message.success();
        } else {
            return Message.fail();
        }

    }


    @ResponseBody
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public Message getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Message.success().add("emp", employee);
    }


    /*查询员工数据（分页查询）*/
    @RequestMapping("/emps")
    @ResponseBody
    /*ajax方法返回分页的json数据*/
    public Message getEmpWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        /*引入分页插件,传入页码和每页的条数*/
        PageHelper.startPage(pn, 5);
        /*查询*/
        List<Employee> emps = employeeService.getAll();
        /*使用pageinfo包装 经过视图解析器处理，会加上前缀/WEB-INF/pages/和后缀*/
        PageInfo page = new PageInfo(emps, 5);
//        for(Employee e:emps){
//            System.out.println(e);
//        }
        return Message.success().add("pageInfo", page);
    }

    //    @RequestMapping("/emps")
//    @ResponseBody
//    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
//
//        /*引入分页插件,传入页码和每页的条数*/
//        PageHelper.startPage(pn, 5);
//        /*查询*/
//        List<Employee> emps = employeeService.getAll();
//        /*使用pageinfo包装 经过视图解析器处理，会加上前缀/WEB-INF/pages/和后缀*/
//        PageInfo page = new PageInfo(emps, 5);
//        model.addAttribute("pageInfo", page);
//
//        return "list";
//    }


    /**
     * 保存员工
     *
     * @param employee
     * @param result
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Message saveEmp(@Valid Employee employee, BindingResult result) {
        //JSR303校验
//        if (result.hasErrors()) {
//            List<FieldError> errors = result.getFieldErrors();
//            Map<String, Object> map = new HashMap<String, Object>();
//            for (FieldError fieldError : errors) {
//                System.out.println("错误信息的字段名:" + fieldError.getField());
//                System.out.println("错误信息:" + fieldError.getDefaultMessage());
//                map.put(fieldError.getField(), fieldError.getDefaultMessage());
//            }
//            return Message.fail().add("errorField", map);
//        }
        employeeService.save(employee);
        return Message.success();
    }

    /**
     * 更新员工
     *
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
    public Message saveEmp(Employee employee) {
        employeeService.updateEmp(employee);
        return Message.success().add("emp", employee.toString());
    }

    /**
     * 根据id删除单个员工或者批量删除员工,通过传入的参数进行判断
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    public Message deleteEmpById(@PathVariable("ids") String ids) {
        if (!ids.contains("-")) {
            //如果ids中没有"-"，认为传入的是单个id，进行单个删除操作
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        } else {
            //如果ids中有"-"，认为传入的是多个id，将ids解析进行批量删除操作
            List<Integer> id = new ArrayList<Integer>();
            String[] idStr = ids.split("-");
            for (String str : idStr) {
                id.add(Integer.parseInt(str));
            }

        }
        return Message.success();
    }
}
