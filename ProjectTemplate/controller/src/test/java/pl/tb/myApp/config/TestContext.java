package pl.tb.myApp.config;

import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import pl.tb.myApp.service.user.UserService;

/**
 * Created by tomek on 25.10.15.
 */
@Configuration
public class TestContext {

    private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/messages";
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
    @Bean
    public UserService todoService() {
        return Mockito.mock(UserService.class);
    }
}
