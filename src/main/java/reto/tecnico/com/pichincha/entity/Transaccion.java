package reto.tecnico.com.pichincha.entity;

import java.io.Serializable;
import java.math.BigDecimal;


public class Transaccion implements Serializable {


	private int moneda_origen;
	private int moneda_destino;
	private BigDecimal monto;
	private BigDecimal tipo_cambio;
	private BigDecimal monto_tipo_cambio;	

	

	public int getMoneda_origen() {
		return moneda_origen;
	}




	public void setMoneda_origen(int moneda_origen) {
		this.moneda_origen = moneda_origen;
	}




	public int getMoneda_destino() {
		return moneda_destino;
	}




	public void setMoneda_destino(int moneda_destino) {
		this.moneda_destino = moneda_destino;
	}




	public BigDecimal getMonto() {
		return monto;
	}




	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}




	public BigDecimal getTipo_cambio() {
		return tipo_cambio;
	}




	public void setTipo_cambio(BigDecimal tipo_cambio) {
		this.tipo_cambio = tipo_cambio;
	}




	public BigDecimal getMonto_tipo_cambio() {
		return monto_tipo_cambio;
	}




	public void setMonto_tipo_cambio(BigDecimal monto_tipo_cambio) {
		this.monto_tipo_cambio = monto_tipo_cambio;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	private static final long serialVersionUID = 1L;

}
