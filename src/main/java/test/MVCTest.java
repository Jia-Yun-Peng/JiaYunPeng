package test;

import com.github.pagehelper.PageInfo;
import com.jiayunpeng.crud.bean.Employee;
import com.jiayunpeng.crud.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springmvc.xml"})
public class MVCTest {
    /*传入springMVC的IOC*/
    @Autowired
    WebApplicationContext context;
    /*虚拟mvc请求，获取处理结果*/
    MockMvc mockMvc;

    @Autowired
    EmployeeService employeeService;

    @Before
    public void initMokcMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPage() throws Exception {
        /*模拟请求拿到返回值*/
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
        /*请求成功后，请求域中会有pageinfo，可取出进行验证*/
        MockHttpServletRequest request = result.getRequest();
        PageInfo pi = (PageInfo) request.getAttribute("pageInfo");

        System.out.println(pi);

        System.out.println("当前页码：" + pi.getPageNum());
        System.out.println("总页码：" + pi.getPages());
        System.out.println("总记录数：" + pi.getTotal());
        System.out.println("在页面连续显示的页码");
        int[] nums = pi.getNavigatepageNums();
        for (int i :nums) {
            System.out.println(" " + i);
        }
        /*员工数据*/
        List<Employee> list = pi.getList();
        for (Employee employee:list
             ) {
            System.out.println("ID:"+employee.getEmail()+"====>Name:"+employee.getEmpName());

        }
    }

    @Test
    public void test1() throws Exception {
        List<Employee> employees=employeeService.getAll();
        for(Employee employee:employees){
            System.out.println(employee);
        }
    }
}

