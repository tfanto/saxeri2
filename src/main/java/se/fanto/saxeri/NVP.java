package se.fanto.saxeri;

public class NVP {

	public String name;
	public String value;

	public NVP(String name, String value) {
		this.name = name;
		this.value = value;

	}

	@Override
	public String toString() {
		String ret = "";
		if(name != null || name != null && name.trim().length() > 0) {
			ret += "(attributeName=" + name + ", attributeValue=" + value+")" ;
		}
		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		NVP other = (NVP) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
