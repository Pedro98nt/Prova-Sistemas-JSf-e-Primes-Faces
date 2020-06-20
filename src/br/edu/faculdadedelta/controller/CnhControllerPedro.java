package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.CnhPedroDAO;
import br.edu.faculdadedelta.modelo.CnhPedro;

@ManagedBean
@SessionScoped
public class CnhControllerPedro {

	CnhPedro cnhPedro = new CnhPedro();
	CnhPedroDAO dao = new CnhPedroDAO();

	private static final String PAGINA_CADASTRO_CNH = "cadastroCnh.xhtml";
	private static final String PAGINA_LISTA_CNH = "listaCnh.xhtml";

	public CnhPedro getCnhPedro() {
		return cnhPedro;
	}

	public void setCnhPedro(CnhPedro cnhPedro) {
		this.cnhPedro = cnhPedro;
	}

	public void limparCampos() {
		cnhPedro = new CnhPedro();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (cnhPedro.getId() == 0) {
				dao.incluir(cnhPedro);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				dao.alterar(cnhPedro);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return PAGINA_CADASTRO_CNH;
	}

	public String excluir() {
		try {
			dao.excluir(cnhPedro);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return PAGINA_LISTA_CNH;
	}

	public String editar() {
		return PAGINA_CADASTRO_CNH;
	}

	public List<CnhPedro> getLista() {
		List<CnhPedro> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação: " + e.getMessage());
		}
		return listaRetorno;
	}
}