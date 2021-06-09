package es.vn.sb.model;

import java.io.Serializable;

public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer iteraciones;
	
	public Pedido() {
		super();
	}

	public Pedido(Long id, Integer iteraciones) {
		super();
		this.id = id;
		this.iteraciones = iteraciones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIteraciones() {
		return iteraciones;
	}

	public void setIteraciones(Integer iteraciones) {
		this.iteraciones = iteraciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((iteraciones == null) ? 0 : iteraciones.hashCode());
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
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (iteraciones == null) {
			if (other.iteraciones != null)
				return false;
		} else if (!iteraciones.equals(other.iteraciones))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", iteraciones=" + iteraciones + "]";
	}
	
}
