import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.universe.Application;
import com.universe.mapper.ResourceMapper;
import com.universe.mapper.RoleMapper;
import com.universe.pojo.domain.ResourceDo;
import com.universe.pojo.domain.RoleDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class ResourceMapperTest {

	@Autowired
	private ResourceMapper resourceMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Test
	public void listResources() {
		List<ResourceDo> list = resourceMapper.selectList(Wrappers.<ResourceDo>lambdaQuery());
		System.err.println(list);
	}

	@Test
	public void getRolesByUsername() {
		List<RoleDo> roles = roleMapper.getRolesByUsername("root");
		System.err.println(roles);
	}
}
