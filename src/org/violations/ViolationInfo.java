package org.violations;

public class ViolationInfo {
	public String m_type;
	public String m_origin_class;
	public String m_destiny_class;
	public String m_origin_method;
	public String m_destiny_method;
	public String m_origin_package;
	public String m_destiny_package;
	public String m_state;
	public String m_version;

	public ViolationInfo() {

	}

	public boolean hasNullComponents() {
		if (m_origin_class.equals("null") || m_destiny_class.equals("null")
				|| m_origin_method.equals("null")
				|| m_destiny_method.equals("null"))
			return true;
		return false;
	}

	public String toStringPackages() {
		String l_return = m_origin_package + "," + m_destiny_package;
		l_return += "," + m_type + "," + m_version;
		return l_return;
	}

	public String toStringClasses() {
		String l_return = m_origin_class + "," + m_destiny_class + "," + m_type
				+ "," + m_version;
		return l_return;
	}

	public String toStringMethods() {
		String l_return = m_origin_method + "," + m_destiny_method + ","
				+ m_type + "," + m_version;
		return l_return;
	}

	public String toString() {
		String l_return = m_origin_package + "," + m_destiny_package;
		if (!m_origin_class.equals("null"))
			l_return += "," + m_origin_class;
		if (!m_destiny_class.equals("null"))
			l_return += "," + m_destiny_class;
		if (!m_origin_method.equals("null"))
			l_return += "," + m_origin_method;
		if (!m_destiny_method.equals("null"))
			l_return += "," + m_destiny_method;

		l_return += "," + m_type + "," + m_version;
		return l_return;
	}

	public boolean equals(ViolationInfo info) {
		if (m_origin_class == info.m_origin_class && m_type == info.m_type
				&& m_destiny_class == info.m_destiny_class
				&& m_origin_method == info.m_origin_method
				&& m_destiny_method == info.m_destiny_method
				&& m_origin_package == info.m_origin_package
				&& m_destiny_package == info.m_destiny_package
				&& m_state == info.m_state && m_version == info.m_version)
			return true;
		return false;
	}
}
