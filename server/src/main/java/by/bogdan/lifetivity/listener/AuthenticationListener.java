package by.bogdan.lifetivity.listener;

import by.bogdan.lifetivity.security.TokenUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent> {

    @Value("${logging.show-password}")
    private boolean showPassword;

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        if (event instanceof AuthenticationSuccessEvent) {
            final Authentication authentication = event.getAuthentication();
            final TokenUserDetails userDetails = (TokenUserDetails) authentication.getPrincipal();
            log.debug("Authentication success:\n" +
                    "username: {}", userDetails.getUsername());
        }
        if (event instanceof AuthenticationFailureBadCredentialsEvent) {
            final Authentication authentication = event.getAuthentication();
            final String login = (String) authentication.getPrincipal();
            String password = (String) authentication.getCredentials();
            if (!showPassword) password = "****SECRET****\n" +
                    "set \"logging.show-password=true\" if you want to print password in logs";
            log.debug("Authentication attempt: \n" +
                            "username: {}\n" +
                            "password: {}\n" +
                            "status: {}", login, password,
                    authentication.isAuthenticated());
        }
    }
}
