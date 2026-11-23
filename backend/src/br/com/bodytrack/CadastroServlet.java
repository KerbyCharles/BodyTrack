
package br.com.bodytrack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.SQLException;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String dataNascimento = request.getParameter("dataNascimento");
        String alturaTexto = request.getParameter("altura");

        LocalDate nascimento = LocalDate.parse(dataNascimento);
        double altura = Double.parseDouble(alturaTexto);

        Usuario usuario = new Usuario(
            nome,
            email,
            senha,
            nascimento,
            altura
        );

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {
            usuarioDAO.salvar(usuario);

            response.sendRedirect("cadastro-sucesso.html");

        } catch (SQLException e) {
            throw new ServletException("Erro ao cadastrar usuário.", e);
        }
    }
}
