package br.com.praticas.mygroup.util;

import br.com.praticas.mygroup.modelo.entidades.Usuario;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(servletNames = {"Faces Servlet"})
public class ControleDeAcesso implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init() foi chamado.");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);

        Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;
        String loginURL = request.getContextPath() + "/index.xhtml";

        boolean loginRequest = request.getRequestURI().startsWith(loginURL);
        boolean resourceRequest = request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

        if (usuario != null || loginRequest || resourceRequest) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURL);
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy() foi chamado.");
    }
}
