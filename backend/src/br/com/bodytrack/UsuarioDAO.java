
package br.com.bodytrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UsuarioDAO {

    public void salvar(Usuario usuario) throws SQLException {

        String sql = """
            INSERT INTO usuario
            (nome, email, senha, data_nascimento, altura)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setDate(4, java.sql.Date.valueOf(usuario.getDataNascimento()));
            stmt.setDouble(5, usuario.getAltura());

            stmt.executeUpdate();
        }
    }

    // Buscar por email
    public Usuario buscarPorEmail(String email) throws SQLException {

        String sql = """
            SELECT id, nome, email, senha, data_nascimento, altura
            FROM usuario
            WHERE email = ?
        """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {

                    return new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("email"),
                        resultado.getString("senha"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getDouble("altura")
                    );
                }
            }
        }

        return null;
    }

    // Buscar por ID
    public Usuario buscarPorId(int id) throws SQLException {

        String sql = """
            SELECT id, nome, email, senha, data_nascimento, altura
            FROM usuario
            WHERE id = ?
        """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet resultado = stmt.executeQuery()) {

                if (resultado.next()) {

                    return new Usuario(
                        resultado.getInt("id"),
                        resultado.getString("nome"),
                        resultado.getString("email"),
                        resultado.getString("senha"),
                        resultado.getDate("data_nascimento").toLocalDate(),
                        resultado.getDouble("altura")
                    );
                }
            }
        }

        return null;
    }
}
