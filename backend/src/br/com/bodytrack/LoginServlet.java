
package br.com.bodytrack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            Usuario usuario = usuarioDAO.buscarPorEmail(email);
            if (usuario != null) {

                HttpSession sessao = request.getSession();
                sessao.setAttribute("usuario", usuario);

                response.sendRedirect("dashboard.html");

            } else {
                response.sendRedirect("login.html?erro=1");
            }

        } catch (SQLException e) {
            throw new ServletException("Erro ao realizar login.", e);
        }
    }
}
