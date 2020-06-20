package br.edu.faculdadedelta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.faculdadedelta.modelo.CnhPedro;
import br.edu.faculdadedelta.util.Conexao;

public class CnhPedroDAO {

	public void incluir(CnhPedro cnh) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "INSERT INTO categorias_cnh (descricao_cat) VALUES (?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cnh.getDescCat().trim());
		ps.executeUpdate();

		Conexao.fecharConexao(conn, ps, null);
	}

	public void alterar(CnhPedro cnh) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "UPDATE categorias_cnh SET descricao_cat = ?  WHERE id_cat = ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, cnh.getDescCat().trim());
		ps.setLong(2, cnh.getId());

		ps.executeUpdate();

		Conexao.fecharConexao(conn, ps, null);
	}

	public void excluir(CnhPedro cnh) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "DELETE FROM categorias_cnh WHERE id_cat = ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setLong(1, cnh.getId());

		ps.executeUpdate();

		Conexao.fecharConexao(conn, ps, null);

	}

	public CnhPedro pesquisarPorId(Long id) throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_cat, descricao_cat FROM categorias_cnh WHERE id_cat = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, id);

		ResultSet rs = ps.executeQuery();
		CnhPedro retorno = new CnhPedro();
		if (rs.next()) {
			retorno = popularCnh(rs);
		}
		Conexao.fecharConexao(conn, ps, null);

		return retorno;
	}

	public List<CnhPedro> listar() throws ClassNotFoundException, SQLException {
		Connection conn = Conexao.conectarNoBancoDeDados();
		String sql = "SELECT id_cat, descricao_cat FROM categorias_cnh";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		List<CnhPedro> listaRetorno = new ArrayList<>();

		while (rs.next()) {
			listaRetorno.add(popularCnh(rs));
		}

		Conexao.fecharConexao(conn, ps, null);

		return listaRetorno;
	}

	private CnhPedro popularCnh(ResultSet rs) throws SQLException {

		CnhPedro cnh = new CnhPedro();
		cnh.setId(rs.getLong("id_cat"));
		cnh.setDescCat(rs.getString("descricao_cat").trim());

		return cnh;
	}
}