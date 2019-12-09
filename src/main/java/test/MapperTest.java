package test;

import com.jiayunpeng.crud.dao.DepartmentMapper;
import com.jiayunpeng.crud.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testCRUD() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        DepartmentMapper bean1 = applicationContext.getBean(DepartmentMapper.class);
        EmployeeMapper bean2 = applicationContext.getBean(EmployeeMapper.class);

        System.out.println(departmentMapper);
        System.out.println(employeeMapper);
        /*1.插入部门*/
//        departmentMapper.insertSelective(new Department(null,"开发部"));
//        departmentMapper.insertSelective(new Department(null,"测试部"));

        /*2.插入员工*/
//        employeeMapper.insertSelective(new Employee(null, "Marry", "F", "998998@qq.com", 3));

        /*3.批量插入员工*/
//        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//        for (int i = 0; i < 10; i++) {
//            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
//            int a=mapper.insertSelective(new Employee(null,uid,"A",uid+"@jiayunpeng",1));
//        }
        /*4.读取员工数据*/


    }
}


