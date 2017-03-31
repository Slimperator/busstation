/**
 * Created by Владимир on 29.03.2017.
 */
import com.busstation.server.storage.dao.impl.XmlBusDao;
import com.busstation.shared.Sorts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/classes/action-servlet.xml",})
public class xmlBusDaoTest {
    @Autowired
    XmlBusDao dao;

    @Test
    public  void testCount() {
        Integer test = dao.getBusCount();
        assertTrue("Count yep", test!=null);
    }
    @Test
    public  void testGetBus() {
        List<String> testList = dao.getBus(1, Sorts.NumA);
        assertTrue("Get Bus Yep", (testList != null && testList.size()!=0));
    }
}
