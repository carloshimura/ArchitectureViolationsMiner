package org.violations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.violations.SVNTuple.OPERATION;

public class SVNLogReader 
{
	
	String m_file_path;
	LinkedList<SVNTuple> m_changes;
	Map<String, Map<String, Integer>> m_common_percentage;
	
	public SVNLogReader(String file)
	{
		m_file_path = file;
		m_changes = new LinkedList<SVNTuple>();
		m_common_percentage = new HashMap<String, Map<String,Integer>>(); 
	}
	
	public void read_file()
	{
		/*r39 | carlos.sabino | 2012-11-09 13:47:47 -0200 (Fri, 09 Nov 2012)
Changed paths:
   A /trunk/api
   A /trunk/cmp_maintenance
   A /trunk/components
   A /trunk/components/connectors
   A /trunk/components/fast_decoder
   A /trunk/components/server
   A /trunk/components/utils
   A /trunk/front
   A /trunk/proxy
   A /trunk/proxy/main
   A /trunk/proxy/recovery
   A /trunk/referee
   A /trunk/service_bus
*/
		boolean l_reading_files = false;
		int l_current_version = 0;
		try {
			String l_current_line = null;
			BufferedReader l_br = new BufferedReader(new FileReader(m_file_path));
			 
			while ((l_current_line = l_br.readLine()) != null) {
				
				if(l_current_line.contains("----"))
				{
					//clear or do nothing
					l_reading_files = false;
				}
				else
				{
					l_current_line = l_current_line.trim();
					if(l_current_line.charAt(0) == 'r')
					{
						//get version
						String l_fiedls[] = l_current_line.split("|");
						l_current_version = Integer.parseInt(l_fiedls[0].trim().replaceAll("r", ""));
					}
					else
					{
						switch(l_current_line.charAt(0))
						{
						case 'A':
							if(l_current_line.contains(".java"))
							{
								String l_folder_fiedls[] = l_current_line.split("/");
								String l_file_name = l_folder_fiedls[l_folder_fiedls.length - 1];
								l_folder_fiedls = l_current_line.split(" ");
								String l_path = l_folder_fiedls[1];
								
								SVNTuple l_tuple = new SVNTuple(OPERATION.ADD, l_path, l_file_name, l_current_version);
								m_changes.add(l_tuple);
							}
							break;
						case 'M':
							if(l_current_line.contains(".java"))
							{
								String l_folder_fiedls[] = l_current_line.split("/");
								String l_file_name = l_folder_fiedls[l_folder_fiedls.length - 1];
								l_folder_fiedls = l_current_line.split(" ");
								String l_path = l_folder_fiedls[1];
								
								SVNTuple l_tuple = new SVNTuple(OPERATION.MODIFY, l_path, l_file_name, l_current_version);
								m_changes.add(l_tuple);
							}
							break;
						case 'D':
							if(l_current_line.contains(".java"))
							{
								String l_folder_fiedls[] = l_current_line.split("/");
								String l_file_name = l_folder_fiedls[l_folder_fiedls.length - 1];
								l_folder_fiedls = l_current_line.split(" ");
								String l_path = l_folder_fiedls[1];
								
								SVNTuple l_tuple = new SVNTuple(OPERATION.REMOVE, l_path, l_file_name, l_current_version);
								m_changes.add(l_tuple);
							}
							break;
						default:
							//do nothing
							break;
						}
					}
				}
				
			}
			l_br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void calc_common_change_percentage()
	{
		
	}

}
