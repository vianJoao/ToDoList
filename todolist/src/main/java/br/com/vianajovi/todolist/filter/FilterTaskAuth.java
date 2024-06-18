package br.com.vianajovi.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.vianajovi.todolist.user.IUserRepository;
import jakarta.servlet.Filter; // WEB - SPRING é baseado em SERVLET << 
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Spring gerencia essa classe
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
              
              var servletPath = request.getServletPath();

              if(servletPath.startsWith("/tasks/")) {
                
                var authorization = request.getHeader("Authorization");
              

               var senhaUsuario = authorization.substring("Basic".length()).trim();

              byte[] authDecode = Base64.getDecoder().decode(senhaUsuario); //Conversão array
                    System.out.println(authDecode);

                    var authString = new String (authDecode);

                    String [] credenciais = authString.split(":");
                    String username = credenciais[0];
                    String password = credenciais[1];

                  var user =  this.userRepository.findByUsername(username);
                if (user == null) {
                    response.sendError(401, "Usuário sem autorização");
                } else {
                 var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if(passwordVerify.verified) {
                    request.setAttribute("IdUser", user.getId());
                    filterChain.doFilter(request, response);
                
                } else {
                    response.sendError(401);
                }
                }
                    
                } else {
                    filterChain.doFilter(request, response);;
                }
}

              }
              
/**
 * Aqui ele pega a autenticação (usuário e senha)
 * 
 * Valida usuário > Valida senha > Segue.
 */
