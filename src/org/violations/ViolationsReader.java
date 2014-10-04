package org.violations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViolationsReader {

	private ViolationsManager m_callback;
	private String m_file_path;
	private String m_version;
	
	public ViolationsReader(String file, String file_version, ViolationsManager callback)
	{
		m_file_path = file;
		m_callback = callback;
		m_version = file_version;
	}
	
	public void readViolations()
	{
		try {
			String l_current_line = null;
			BufferedReader l_br = new BufferedReader(new FileReader(m_file_path));
			 
			while ((l_current_line = l_br.readLine()) != null) {
				String l_split[] = l_current_line.split(" ");
				ViolationInfo l_info = new ViolationInfo();
				l_info.m_origin_package = l_split[0];
				l_info.m_destiny_package = l_split[1];
				l_info.m_origin_class = l_split[2];
				l_info.m_destiny_class = l_split[3];
				l_info.m_origin_method = l_split[4];
				l_info.m_destiny_method = l_split[5];
				l_info.m_type = l_split[6];
				l_info.m_state = l_split[8];
				l_info.m_version = m_version;
				m_callback.add_violations(m_version, l_info);
			}
			l_br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

			