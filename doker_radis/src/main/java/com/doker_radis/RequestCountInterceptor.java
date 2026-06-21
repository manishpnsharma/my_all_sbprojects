package com.doker_radis;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestCountInterceptor implements HandlerInterceptor {

    private final RequestCountService requestCountService;

    public RequestCountInterceptor(RequestCountService requestCountService) {
        this.requestCountService = requestCountService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        String method = request.getMethod();
        String uri = request.getRequestURI();

        /*
         * Avoid counting actuator/admin APIs if needed.
         */
        if (!uri.startsWith("/admin")) {
            requestCountService.incrementCount(method, uri);
        }

        return true;
    }
}