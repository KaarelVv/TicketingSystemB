package ee.sda.ticketingsystem.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

public class CustomCookiFilter implements Filter{


        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            chain.doFilter(request, response);

            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            Collection<String> headers = httpServletResponse.getHeaders("Set-Cookie");
            boolean firstHeader = true;
            for (String header : headers) {
                if (firstHeader) {
                    httpServletResponse.setHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=Lax"));
                    firstHeader = false;
                    continue;
                }
                httpServletResponse.addHeader("Set-Cookie", String.format("%s; %s", header, "SameSite=Lax"));
            }
        }

    }

