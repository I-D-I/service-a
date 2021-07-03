package es.vn.sb.model;

import java.io.Serializable;

public class CustomError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customErrorCoder;
	private String httpStatusCode;
	private String descripcion;

	public CustomError() {
		super();
	}

	public CustomError(String customErrorCoder, String httpStatusCode, String descripcion) {
		super();
		this.customErrorCoder = customErrorCoder;
		this.httpStatusCode = httpStatusCode;
		this.descripcion = descripcion;
	}

	public String getCustomErrorCoder() {
		return customErrorCoder;
	}

	public void setCustomErrorCoder(String customErrorCoder) {
		this.customErrorCoder = customErrorCoder;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customErrorCoder == null) ? 0 : customErrorCoder.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((httpStatusCode == null) ? 0 : httpStatusCode.hashCode());
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
		CustomError other = (CustomError) obj;
		if (customErrorCoder == null) {
			if (other.customErrorCoder != null)
				return false;
		} else if (!customErrorCoder.equals(other.customErrorCoder))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (httpStatusCode == null) {
			if (other.httpStatusCode != null)
				return false;
		} else if (!httpStatusCode.equals(other.httpStatusCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomError [customErrorCoder=" + customErrorCoder + ", httpStatusCode=" + httpStatusCode
				+ ", descripcion=" + descripcion + "]";
	}

}
