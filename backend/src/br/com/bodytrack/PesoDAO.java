
package br.com.bodytrack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PesoDAO {

    public void salvar(Peso peso) throws SQLException {

        String sql = "INSERT INTO peso (usuario_id, peso, data_registro) VALUES (?, ?, ?)";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, peso.getUsuarioId());
            stmt.setDouble(2, peso.getPeso());
            stmt.setDate(3, java.sql.Date.valueOf(peso.getDataRegistro()));

            stmt.executeUpdate();
        }
    } // Fecha salvar()

    public List<Peso> listarPorUsuario(int usuarioId) throws SQLException {

        List<Peso> historico = new ArrayList<>();

        String sql = """
            SELECT id, usuario_id, peso, data_registro
            FROM peso
            WHERE usuario_id = ?
            ORDER BY data_registro DESC
        """;

        try (Connection conexao = Conexao.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);

            try (ResultSet resultado = stmt.executeQuery()) {

                while (resultado.next()) {

                    Peso peso = new Peso(
                        resultado.getInt("id"),
                        resultado.getInt("usuario_id"),
                        resultado.getDouble("peso"),
                        resultado.getDate("data_registro").toLocalDate()
                    );

                    historico.add(peso);
                }
            }
        }

        return historico;
    }

}
