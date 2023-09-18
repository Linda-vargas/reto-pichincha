package reto.tecnico.com.pichincha.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name="registro_eventos")
public class RegistroEventos {
	
	@Id
	private int id;
	private int id_usuario;
	private int id_tipo_cambio;
	private String tipo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public int getId_tipo_cambio() {
		return id_tipo_cambio;
	}
	public void setId_tipo_cambio(int id_tipo_cambio) {
		this.id_tipo_cambio = id_tipo_cambio;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
