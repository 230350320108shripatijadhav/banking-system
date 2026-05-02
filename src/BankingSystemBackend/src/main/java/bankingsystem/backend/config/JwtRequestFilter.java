package bankingsystem.backend.config;

import bankingsystem.backend.exception.MyJwtTokenExpiredException;
import bankingsystem.backend.service.JwtUserDetailService;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException {

        final String requestTokenHeader = request.getHeader("token");

        String username = null;
        String jwtToken = null;

        logger.info(request.getRequestURI());

        if (requestTokenHeader != null && !requestTokenHeader.isEmpty()) {
            jwtToken = requestTokenHeader;

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token expired");
                throw new MyJwtTokenExpiredException("Token Expired");
            }
        } else {
            logger.warn("JWT Token not found");
        }

        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    jwtUserDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}