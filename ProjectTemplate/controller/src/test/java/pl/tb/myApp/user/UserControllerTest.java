package pl.tb.myApp.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.tb.myApp.model.user.entity.User;
import pl.tb.myApp.model.user.entity.UserBuilder;
import pl.tb.myApp.service.user.UserService;
import pl.tb.myApp.util.TestUtil;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Created by tomek on 24.10.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml", "classpath:myAppContext-web.xml"})
//@ContextConfiguration(classes = {TestContext.class/*, WebAppContext.class*/})
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private UserService userServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
//We have to reset our mock between tests because the mock objects
//are managed by the Spring container. If we would not reset them,
//stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(userServiceMock);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void findAll_UsersFound_ShouldReturnFoundUserEntries() throws Exception {
        User first = new UserBuilder()
                .id(1L)
                .name("Adam")
                .email("adam@wp.pl")
                .build();
        User second = new UserBuilder()
                .id(2L)
                .name("Robert")
                .email("robert@onet.pl")
                .build();

        when(userServiceMock.findAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/myApp/user/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Adam")))
                .andExpect(jsonPath("$[0].email", is("adam@wp.pl")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Robert")))
                .andExpect(jsonPath("$[1].email", is("robert@onet.pl")));

        verify(userServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(userServiceMock);
    }
}
