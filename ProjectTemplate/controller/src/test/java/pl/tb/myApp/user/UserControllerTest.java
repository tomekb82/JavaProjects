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
import pl.tb.myApp.controller.user.dto.UserDTO;
import pl.tb.myApp.controller.user.dto.UserDTOBuilder;
import pl.tb.myApp.model.user.entity.User;
import pl.tb.myApp.model.user.entity.UserBuilder;
import pl.tb.myApp.model.util.exception.MyAppException;
import pl.tb.myApp.model.util.validation.ErrorMessage;
import pl.tb.myApp.service.user.UserService;
import pl.tb.myApp.service.util.validation.ServiceValidator;
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


    @Test
    public void findById_UserEntryNotFound_ShouldReturnHttpStatusCode404() throws Exception {
        when(userServiceMock.findById(1L)).thenThrow(new MyAppException(ServiceValidator.getErrorMessage("", ErrorMessage.NOT_FOUND)));

        mockMvc.perform(get("/myApp/user/findById/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(userServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void findById_UserEntryFound_ShouldReturnFoundUserEntry() throws Exception {
        User found = new UserBuilder()
                .id(1L)
                .name("Adam")
                .email("adam@wp.pl")
                .build();

        when(userServiceMock.findById(1L)).thenReturn(found);

        mockMvc.perform(get("/myApp/user/findById/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Adam")))
                .andExpect(jsonPath("$.email", is("adam@wp.pl")));

        verify(userServiceMock, times(1)).findById(1L);
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void add_TitleAndDescriptionAreTooLong_ShouldReturnValidationErrorsForTitleAndDescription() throws Exception {
        String name = TestUtil.createStringWithLength(201);
        String email = TestUtil.createStringWithLength(51);

        UserDTO dto = new UserDTOBuilder()
                .name(name)
                .email(email)
                .build();

        mockMvc.perform(post("/myApp/user/add")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(dto))
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.fieldErrors", hasSize(2)))
                .andExpect(jsonPath("$.fieldErrors[*].path", containsInAnyOrder("name", "email")))
                .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder(
                        "The maximum length of the name is 200 characters.",
                        "The maximum length of the email is 50 characters."
                )));

        verifyZeroInteractions(userServiceMock);
    }
}
