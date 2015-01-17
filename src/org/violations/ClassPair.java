package org.violations;

public class ClassPair 
{
	String m_first;
	String m_second;
	
	public ClassPair(String first, String second)
	{
		m_first = first;
		m_second = second;
	}
	
	public boolean equals(ClassPair pair)
	{
		if((m_first.equals(pair.m_first) && m_second.equals(pair.m_second)) ||
				(m_first.equals(pair.m_second) && m_second.equals(pair.m_first)))
			return true;
		return false;
	}
}
