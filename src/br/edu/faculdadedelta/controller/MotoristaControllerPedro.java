package br.edu.faculdadedelta.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.edu.faculdadedelta.dao.MotoristaPedroDAO;
import br.edu.faculdadedelta.modelo.CnhPedro;
import br.edu.faculdadedelta.modelo.MotoristaPedro;

@ManagedBean
@SessionScoped
public class MotoristaControllerPedro {

	MotoristaPedro motoristaPedro = new MotoristaPedro();
	MotoristaPedroDAO dao = new MotoristaPedroDAO();
	CnhPedro cnhSelecionado = new CnhPedro();

	private static final String PAGINA_CADASTRO_MOTORISTA = "cadastroMotorista.xhtml";
	private static final String PAGINA_LISTA_MOTORISTA = "listaMotorista.xhtml";

	public MotoristaPedro getMotoristaPedro() {
		return motoristaPedro;
	}

	public void setMotoristaPedro(MotoristaPedro motoristaPedro) {
		this.motoristaPedro = motoristaPedro;
	}

	public CnhPedro getCnhSelecionado() {
		return cnhSelecionado;
	}

	public void setCnhSelecionado(CnhPedro cnhSelecionado) {
		this.cnhSelecionado = cnhSelecionado;
	}

	public void limparCampos() {
		motoristaPedro = new MotoristaPedro();
		cnhSelecionado = new CnhPedro();
	}

	private void exibirMensagem(String mensagem) {
		FacesMessage msg = new FacesMessage(mensagem);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String salvar() {
		try {
			if (motoristaPedro.getId() == 0) {
				motoristaPedro.setCnhPedro(cnhSelecionado);
				dao.incluir(motoristaPedro);
				exibirMensagem("Inclusão realizada com sucesso!");
				limparCampos();
			} else {
				motoristaPedro.setCnhPedro(cnhSelecionado);
				dao.alterar(motoristaPedro);
				exibirMensagem("Alteração realizada com sucesso!");
				limparCampos();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}

		return PAGINA_CADASTRO_MOTORISTA;
	}

	public String excluir() {
		try {
			dao.excluir(motoristaPedro);
			exibirMensagem("Exclusão realizada com sucesso!");
			limparCampos();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}
		return PAGINA_LISTA_MOTORISTA;
	}

	public String editar() {
		cnhSelecionado = motoristaPedro.getCnhPedro();
		return PAGINA_CADASTRO_MOTORISTA;
	}

	public List<MotoristaPedro> getLista() {
		List<MotoristaPedro> listaRetorno = new ArrayList<>();
		try {
			listaRetorno = dao.listar();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			exibirMensagem("Erro ao realizar a operação. " + e.getMessage());
		}
		return listaRetorno;
	}
}