package crm.crmbackend.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import crm.crmbackend.exception.CrmError;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final HttpMessageConverter<String> messageConverter;

    private final ObjectMapper mapper;

    public JwtAuthenticationEntryPoint(HttpMessageConverter<String> messageConverter, ObjectMapper mapper) {
        this.messageConverter = messageConverter;
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException{
        CrmError error = new CrmError(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), authException.getLocalizedMessage());

        ServerHttpResponse outputMessage = new ServletServerHttpResponse(response);
        outputMessage.setStatusCode(HttpStatus.UNAUTHORIZED);

        messageConverter.write(mapper.writeValueAsString(error), MediaType.APPLICATION_JSON, outputMessage);
    }
}
