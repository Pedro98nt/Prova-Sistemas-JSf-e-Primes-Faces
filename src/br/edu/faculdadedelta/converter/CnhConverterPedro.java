package br.edu.faculdadedelta.converter;

import java.sql.SQLException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.edu.faculdadedelta.dao.CnhPedroDAO;
import br.edu.faculdadedelta.modelo.CnhPedro;

@FacesConverter(value = "cnhConverter")
public class CnhConverterPedro implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) {

		if (valor != null) {
			CnhPedroDAO dao = new CnhPedroDAO();
			try {
				return dao.pesquisarPorId(Long.valueOf(valor));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) {

		if (valor != null) {
			return String.valueOf(((CnhPedro) valor).getId());
		}

		return null;
	}

}