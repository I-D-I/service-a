package es.vn.sb.model;

import java.io.Serializable;

public class CustomError implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customErrorCoder;
	private String customDescripcion;
	private String statusCode;
	private String descStatusCode;

	public CustomError() {
		super();
	}

	public CustomError(String customErrorCoder, String customDescripcion, String statusCode, String descStatusCode) {
		super();
		this.customErrorCoder = customErrorCoder;
		this.customDescripcion = customDescripcion;
		this.statusCode = statusCode;
		this.descStatusCode = descStatusCode;

	}

	public String getCustomErrorCoder() {
		return customErrorCoder;
	}

	public void setCustomErrorCoder(String customErrorCoder) {
		this.customErrorCoder = customErrorCoder;
	}

	public String getCustomDescripcion() {
		return customDescripcion;
	}

	public void setCustomDescripcion(String customDescripcion) {
		this.customDescripcion = customDescripcion;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getDescStatusCode() {
		return descStatusCode;
	}

	public void setDescStatusCode(String descStatusCode) {
		this.descStatusCode = descStatusCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customDescripcion == null) ? 0 : customDescripcion.hashCode());
		result = prime * result + ((customErrorCoder == null) ? 0 : customErrorCoder.hashCode());
		result = prime * result + ((descStatusCode == null) ? 0 : descStatusCode.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
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
		if (customDescripcion == null) {
			if (other.customDescripcion != null)
				return false;
		} else if (!customDescripcion.equals(other.customDescripcion))
			return false;
		if (customErrorCoder == null) {
			if (other.customErrorCoder != null)
				return false;
		} else if (!customErrorCoder.equals(other.customErrorCoder))
			return false;
		if (descStatusCode == null) {
			if (other.descStatusCode != null)
				return false;
		} else if (!descStatusCode.equals(other.descStatusCode))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomError [customErrorCoder=" + customErrorCoder + ", customDescripcion=" + customDescripcion
				+ ", statusCode=" + statusCode + ", descStatusCode=" + descStatusCode + "]";
	}

}
