package reto.tecnico.com.pichincha.entity;

import java.math.BigDecimal;

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
@Table(name = "tipo_cambio")
public class TipoCambio {

	@Id
    private Long id;

    private int monedaOrigenCodigo;
    private int monedaDestinoCodigo;

    private BigDecimal valor;
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMonedaOrigenCodigo() {
		return monedaOrigenCodigo;
	}

	public void setMonedaOrigenCodigo(int monedaOrigenCodigo) {
		this.monedaOrigenCodigo = monedaOrigenCodigo;
	}

	public int getMonedaDestinoCodigo() {
		return monedaDestinoCodigo;
	}

	public void setMonedaDestinoCodigo(int monedaDestinoCodigo) {
		this.monedaDestinoCodigo = monedaDestinoCodigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public static TipoCambio.Builder builder() {
        return new TipoCambio.Builder();
    }

    public static class Builder {

    	private Long id;

        private int monedaOrigenCodigo;
        private int monedaDestinoCodigo;
        private BigDecimal valor;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder monedaOrigenCodigo(int monedaOrigenCodigo) {
            this.monedaOrigenCodigo = monedaOrigenCodigo;
            return this;
        }

        public Builder monedaDestinoCodigo(int monedaDestinoCodigo) {
            this.monedaDestinoCodigo = monedaDestinoCodigo;
            return this;
        }

        public Builder valor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        public TipoCambio build() {
            return new TipoCambio(this.id,this.monedaOrigenCodigo, this.monedaDestinoCodigo, this.valor);
        }
    }

    public TipoCambio(Long id, int monedaOrigenCodigo, int monedaDestinoCodigo,BigDecimal valor) {
    	this.id=id;
        this.monedaOrigenCodigo = monedaOrigenCodigo;
        this.monedaDestinoCodigo = monedaDestinoCodigo;
        this.valor = valor;
    }
}
