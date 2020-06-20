package br.edu.faculdadedelta.modelo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class MotoristaPedro {

	private long id;
	private int idCat;
	private String nomeMotorista;
	private String destino;
	private double distancia;
	private Date dataCorrida;
	private double valorKm;
	private CnhPedro cnhPedro;
	public MotoristaPedro() {
		super();
	}
	public MotoristaPedro(long id, int idCat, String nomeMotorista, String destino, double distancia, Date dataCorrida,
			double valorKm, CnhPedro cnhPedro) {
		super();
		this.id = id;
		this.idCat = idCat;
		this.nomeMotorista = nomeMotorista;
		this.destino = destino;
		this.distancia = distancia;
		this.dataCorrida = dataCorrida;
		this.valorKm = valorKm;
		this.cnhPedro = cnhPedro;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	public String getNomeMotorista() {
		return nomeMotorista;
	}
	public void setNomeMotorista(String nomeMotorista) {
		this.nomeMotorista = nomeMotorista;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	public Date getDataCorrida() {
		return dataCorrida;
	}
	public void setDataCorrida(Date dataCorrida) {
		this.dataCorrida = dataCorrida;
	}
	public double getValorKm() {
		return valorKm;
	}
	public void setValorKm(double valorKm) {
		this.valorKm = valorKm;
	}
	public CnhPedro getCnhLucasGomes() {
		return cnhPedro;
	}
	public void setCnhLucasGomes (CnhPedro cnhPedro) {
		this.cnhPedro = cnhPedro;
	}
	
	public Double getDesconto() {

		Double desconto = 0.0;

		if (distancia > 600) {
			desconto = (valorKm * distancia) * 0.025;
		}
		return desconto;
	}

	public Double getDesconto2() throws java.text.ParseException {

		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		Date dr = null;
		Double desconto2 = 0.0;

		try {
			dr = sd.parse("01/12/2020");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (dataCorrida.after(dr)) {
			desconto2 = (valorKm * 0.01);
		}
		return desconto2;
	}

	public Double getValorTotal() throws java.text.ParseException {

		double total = ((valorKm * distancia)) - (getDesconto() + getDesconto2());
		return total;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MotoristaPedro other = (MotoristaPedro) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public void setCnhPedro(CnhPedro cnhSelecionado) {
		// TODO Auto-generated method stub
		
	}
	public CnhPedro getCnhPedro() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
