package com.dev.fellipe.redirectUrlShortner.persistencia;

import com.dev.fellipe.redirectUrlShortner.negocio.Anotacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnotacaoDAO {

    public void cadastrar(Anotacao anotacao) throws SQLException {
        String sql = "INSERT INTO anotacoes (titulo, descricao, cor, foto) VALUES (?, ?, ?, ?)";

        try (Connection conexao = new ConexaoPostgreSQL().getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, anotacao.getTitulo());
            preparedStatement.setString(2, anotacao.getDescricao());
            preparedStatement.setString(3, anotacao.getCor());

            if (anotacao.getFoto() != null) {
                preparedStatement.setBytes(4, anotacao.getFoto());
            } else {
                preparedStatement.setNull(4, java.sql.Types.BINARY);
            }

            preparedStatement.executeUpdate();
            System.out.println("anotação criada com sucesso!✅");
        }
        catch (SQLException e) {
            System.out.println("Erro ao cadastrar anotação: " + e.getMessage());
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM anotacoes WHERE id = ?;";

        try (Connection conexao = new ConexaoPostgreSQL().getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("ANOTAÇÃO DELETADA!✅");
            } else {
                System.out.println("ANOTAÇÃO NÃO ENCONTRADA!⚠️");
            }
        }
    }

    public void atualizar(Anotacao anotacao) throws SQLException {
        String sql = "UPDATE anotacoes SET titulo = ?, descricao = ?, cor = ?, foto = ? WHERE id = ?";

        try (Connection conexao = new ConexaoPostgreSQL().getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setString(1, anotacao.getTitulo());
            preparedStatement.setString(2, anotacao.getDescricao());
            preparedStatement.setString(3, anotacao.getCor());

            if (anotacao.getFoto() != null) {
                preparedStatement.setBytes(4, anotacao.getFoto());
            } else {
                preparedStatement.setNull(4, java.sql.Types.BINARY);
            }

            preparedStatement.setInt(5, anotacao.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Anotação atualizada com sucesso!✅");
            } else {
                System.out.println("Anotação não encontrada!⚠️");
            }
        }
    }

    public void copiar(int id) throws SQLException {
        String sql = "INSERT INTO anotacoes (titulo, descricao, cor, foto)\n" +
                "SELECT titulo, descricao, cor, foto\n" +
                "FROM anotacoes\n" +
                "WHERE id = ?;";

        try (Connection conexao = new ConexaoPostgreSQL().getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Anotação copiada com sucesso!✅");
            } else {
                System.out.println("Anotação não encontrada!⚠️");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao copiar anotação: " + e.getMessage());
        }
    }

    public List<Anotacao> listarPorDataHora() throws SQLException {
        List<Anotacao> anotacoes = new ArrayList<>();
        String sql = "SELECT * FROM anotacoes ORDER BY data_hora";

        try (Connection conexao = new ConexaoPostgreSQL().getConexao();
             PreparedStatement preparedStatement = conexao.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Anotacao anotacao = new Anotacao(
                        resultSet.getInt("id"),
                        resultSet.getString("titulo"),
                        resultSet.getTimestamp("data_hora"),
                        resultSet.getString("descricao"),
                        resultSet.getString("cor"),
                        resultSet.getBytes("foto")
                );
                anotacoes.add(anotacao);
            }
        }
        return anotacoes;
    }
}
