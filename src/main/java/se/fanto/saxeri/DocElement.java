package se.fanto.saxeri;

import java.util.ArrayList;
import java.util.List;

public class DocElement {

	public String path;
	public String value;
	public List<NVP> attributes = new ArrayList<>();

	@Override
	public String toString() {
		String ret = "";
		if(value != null) {
			ret += "value=";
			ret += value;
		}
		if(attributes.size() > 0) {
			ret += " ";
			ret += attributes.toString();
		}
		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
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
		DocElement other = (DocElement) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}


}
