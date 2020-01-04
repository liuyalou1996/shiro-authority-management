import com.universe.Application;
import com.universe.service.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class ResourceServiceTest {

	@Autowired
	private ResourceService resourceService;

	@Test
	public void getMenuByUsernameTest() {
		System.err.println(resourceService.getMenuByUsername("root"));
	}
}
