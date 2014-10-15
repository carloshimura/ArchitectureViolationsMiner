package org.violations;

public class ViolationPair {

	public String m_origin;
	public String m_destiny;
	public ViolationInfo m_info;

	
	public ViolationPair(String origin, String destiny, ViolationInfo info)
	{
		m_origin = origin;
		m_destiny = destiny;
		m_info = info;
	}
	
	public boolean equals(ViolationPair pair)
	{
		if(m_origin.equals(pair.m_origin) && m_destiny.equals(pair.m_destiny))
			return true;
		return false;
	}
}
