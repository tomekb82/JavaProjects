package pl.tb.myApp.service.user;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import pl.tb.myApp.model.user.entity.User;
import pl.tb.myApp.model.user.entity.UserBuilder;
import pl.tb.myApp.model.util.exception.MyAppException;
import pl.tb.myApp.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by tomek on 25.10.15.
 */
public class UserServiceTest {

    private static final Long ID = 1L;
    private static final String NAME = "name";
    private static final String NAME_UPDATED = "updatedName";
    private static final String EMAIL = "email";
    private static final String EMAIL_UPDATED = "updatedEmail";

    private UserServiceImpl service;

    private UserRepository repositoryMock;

    @Before
    public void setUp() {
        repositoryMock = mock(UserRepository.class);

        service = new UserServiceImpl(repositoryMock);
    }

    @Test
    public void add_NewUserEntry_ShouldSaveUserEntry() throws MyAppException{
        User dto = new UserBuilder()
                .name(NAME)
                .email(EMAIL)
                .build();

        service.add(dto);

        ArgumentCaptor<User> toDoArgument = ArgumentCaptor.forClass(User.class);
        verify(repositoryMock, times(1)).save(toDoArgument.capture());
        verifyNoMoreInteractions(repositoryMock);

        User model = toDoArgument.getValue();

        assertNull(model.getId());
        assertThat(model.getName(), is(dto.getName()));
        assertThat(model.getEmail(), is(dto.getEmail()));
    }

    @Test
    public void deleteById_UserEntryFound_ShouldDeleteUserEntryAndReturnIt() throws MyAppException {
        User model = new UserBuilder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .build();

        when(repositoryMock.findOne(ID)).thenReturn(model);

        User actual = service.deleteById(ID);

        verify(repositoryMock, times(1)).findOne(ID);
        verify(repositoryMock, times(1)).delete(model);
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, is(model));
    }

    @Test(expected = MyAppException.class)
    public void deleteById_UserEntryNotFound_ShouldThrowException() throws MyAppException {
        when(repositoryMock.findOne(ID)).thenReturn(null);

        service.deleteById(ID);

        verify(repositoryMock, times(1)).findOne(ID);
        verifyNoMoreInteractions(repositoryMock);
    }

    @Test
    public void findAll_ShouldReturnListOfUserEntries() throws MyAppException{
        List<User> models = new ArrayList<>();
        when(repositoryMock.findAll()).thenReturn(models);

        List<User> actual = service.findAll();

        verify(repositoryMock, times(1)).findAll();
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, is(models));
    }

    @Test
    public void findById_UserEntryFound_ShouldReturnFoundUserEntry() throws MyAppException {
        User model = new UserBuilder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .build();

        when(repositoryMock.findOne(ID)).thenReturn(model);

        User actual = service.findById(ID);

        verify(repositoryMock, times(1)).findOne(ID);
        verifyNoMoreInteractions(repositoryMock);

        assertThat(actual, is(model));
    }

    @Test(expected = MyAppException.class)
    public void findById_UserEntryNotFound_ShouldThrowException() throws MyAppException {
        when(repositoryMock.findOne(ID)).thenReturn(null);

        service.findById(ID);

        verify(repositoryMock, times(1)).findOne(ID);
        verifyNoMoreInteractions(repositoryMock);
    }

    // todo
    //@Test
    public void update_UserEntryFound_ShouldUpdateUserEntry() throws MyAppException {
        User dto = new UserBuilder()
                .id(ID)
                .name(NAME_UPDATED)
                .email(EMAIL_UPDATED)
                .build();
        dto.setVersion(0L);
        dto.setCreatedDate(Calendar.getInstance());

        User model = new UserBuilder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .build();

        when(repositoryMock.findOne(ID)).thenReturn(model);

        User actual = service.update(dto);

        verify(repositoryMock, times(1)).findOne(ID);
        verifyNoMoreInteractions(repositoryMock);

        //assertThat(model.getId(), is(dto.getId()));
        //assertThat(model.getName(), is(dto.getName()));
        //assertThat(model.getEmail(), is(dto.getEmail()));
    }

    @Test(expected = MyAppException.class)
    public void update_UserEntryNotFound_ShouldThrowException() throws MyAppException {
        User dto = new UserBuilder()
                .id(ID)
                .name(NAME_UPDATED)
                .email(EMAIL_UPDATED)
                .build();

        when(repositoryMock.findOne(dto.getId())).thenReturn(null);

        service.update(dto);

        verify(repositoryMock, times(1)).findOne(dto.getId());
        verifyNoMoreInteractions(repositoryMock);
    }
}
