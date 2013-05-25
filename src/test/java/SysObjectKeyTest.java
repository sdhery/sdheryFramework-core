import com.sdhery.core.service.ISysObjectKeyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    @Qualifier("sysObjectKeyService")
    ISysObjectKeyService sysObjectKeyService;

    @Test
    public void testRun() {
        try {
            System.out.println("----"+sysObjectKeyService.getById("sysUserKey_root").getSysObjectKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
