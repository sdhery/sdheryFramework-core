import com.sdhery.module.core.dataBase.IIDGenerator;
import com.sdhery.module.core.myBatisDialect.IDialect;
import com.sdhery.module.core.service.ISysObjectKeyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: sdhery
 * Date: 13-5-25
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/*.xml")
public class SysObjectKeyTest {

    @Autowired
    ISysObjectKeyService sysObjectKeyService;

    @Resource
    IIDGenerator mysqlIDGenerator;

    @Test
    public void testRun() {
        try {
            System.out.println(mysqlIDGenerator.getId("sys_tree"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
