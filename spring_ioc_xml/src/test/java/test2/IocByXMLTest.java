package test2;

import com.atguigu.spring.pojo.Student;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 中北大学软件学院王袭明版权声明(c) 2022/9/30
 */
public class IocByXMLTest {

    @Test
    public void testIOC(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc-fuzhi.xml");
        Student studentOne = (Student) context.getBean("studentOne");
        System.out.println(studentOne);
    }

    @Test
    public void testIOCTwo(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc-fuzhi.xml");
        Student studentOne = context.getBean("studentTwo",Student.class);
        System.out.println(studentOne);
    }

    @Test
    public void testIOCThree(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc-fuzhi.xml");
        Student studentOne = context.getBean("studentThree",Student.class);
        System.out.println(studentOne);
    }

    @Test
    public void testIOCFour(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc-fuzhi.xml");
        Student studentFour = context.getBean("studentFour", Student.class);
        System.out.println(studentFour);
    }
}
