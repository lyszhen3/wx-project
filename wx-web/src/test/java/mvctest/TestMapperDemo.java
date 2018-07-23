package mvctest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.logging.Logger;

/**
 * Created by pc on 2017/9/29.
 *
 * @author pc
 * @version 3.0.0-SNAPSHOT
 * @description
 * @since 3.0.0-SNAPSHOT
 * <p>
 * SpringJUnit4ClassRunner 支持多线程
 * Junit4可不支持
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/lys_spring_test.xml")
public class TestMapperDemo {
    private final static Logger log = Logger.getLogger(TestMapperDemo.class.getName());

}
