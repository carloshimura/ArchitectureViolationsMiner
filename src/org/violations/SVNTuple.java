package org.violations;

public class SVNTuple {
	
	public enum OPERATION {ADD, REMOVE, MODIFY};
	
	public String m_path;
	public String m_file;
	public int m_version;
	public OPERATION m_operation;
	
	public SVNTuple(OPERATION op, String file_path, String file, int version)
	{
		m_operation = op;
		m_path = file_path;
		m_file = file;
		m_version = version;
	}
}
