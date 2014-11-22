package org.violations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.violations.SVNTuple.OPERATION;

public class SVNLogReader 
{
	
	String m_file_path;
	SortedMap<Integer, Set<SVNTuple>> m_changes;
	Map<String, Map<String, TupleInfo>> m_common_percentage;
	
	public SVNLogReader(String file)
	{
		m_file_path = file;
		m_changes = new TreeMap<Integer, Set<SVNTuple>>();
		m_common_percentage = new HashMap<String, Map<String, TupleInfo>>(); 
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
		int l_current_version = 0;
		try {
			String l_current_line = null;
			BufferedReader l_br = new BufferedReader(new FileReader(m_file_path));
			 
			while ((l_current_line = l_br.readLine()) != null) {
				
				if(l_current_line.contains("----"))
				{
					//clear or do nothing
				}
				else
				{
					l_current_line = l_current_line.trim();
					if(l_current_line.charAt(0) == 'r')
					{
						//get version
						String l_fields[] = l_current_line.split("\\|");
						l_current_version = Integer.parseInt(l_fields[0].trim().replaceAll("r", ""));
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
								
								if(m_changes.containsKey(l_current_version))
								{
									m_changes.get(l_current_version).add(l_tuple);
								}
								else
								{
									Set<SVNTuple> l_list = new TreeSet<SVNTuple>();
									l_list.add(l_tuple);
									m_changes.put(l_current_version, l_list);
								}
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
								
								if(m_changes.containsKey(l_current_version))
								{
									m_changes.get(l_current_version).add(l_tuple);
								}
								else
								{
									Set<SVNTuple> l_list = new TreeSet<SVNTuple>();
									l_list.add(l_tuple);
									m_changes.put(l_current_version, l_list);
								}
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
								
								if(m_changes.containsKey(l_current_version))
								{
									m_changes.get(l_current_version).add(l_tuple);
								}
								else
								{
									Set<SVNTuple> l_list = new TreeSet<SVNTuple>();
									l_list.add(l_tuple);
									m_changes.put(l_current_version, l_list);
								}
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
		for(Integer l_key : m_changes.keySet())
		{
			Set<SVNTuple> l_version_tuples = m_changes.get(l_key);
			for(SVNTuple tuple : l_version_tuples)
			{
				if(!m_common_percentage.containsKey(tuple.m_path))
				{
					SortedMap<String,TupleInfo> l_info_map = new TreeMap<String, TupleInfo>(); 
					int l_mod_number = 0;
					for(Entry<Integer, Set<SVNTuple>> entry : m_changes.entrySet())
					{
						if(entry.getValue().contains(tuple))
						{
							++l_mod_number;
							for(SVNTuple tup : entry.getValue())
							{
								if(tuple.m_path.equals(tup.m_path)) //same file
									continue;
								if(l_info_map.containsKey(tup.m_path))
								{
									++(l_info_map.get(tup.m_path).m_common_modified_count);
								}
								else
								{
									TupleInfo l_tinfo = new TupleInfo();
									l_tinfo.m_common_modified_count = 1;
									l_info_map.put(tup.m_path, l_tinfo);
								}
							}
						}
					}
					
					for(Entry<String, TupleInfo> entry_info : l_info_map.entrySet())
					{
						entry_info.getValue().m_total_modified_count = l_mod_number;
						entry_info.getValue().m_percentage = 100.0 * ((double)(entry_info.getValue().m_common_modified_count) / 
								(double)(entry_info.getValue().m_total_modified_count));
					}
					
					m_common_percentage.put(tuple.m_path, l_info_map);
				}
				else
				{
					//next file
				}
			}
		}
	}
	
	void write_data()
	{
		FileOutputStream l_output_stream;
		try {
			l_output_stream = new FileOutputStream(
					"/ArchitectureViolationsMiner/trunk/data/results/svn_result.csv");
			BufferedWriter l_bufferBufferedWriter = new BufferedWriter(
					new OutputStreamWriter(l_output_stream));
			
			l_bufferBufferedWriter.write("Class;Co-change-class;Comon-percentage;Common-count;Total(Class)\n"); 

			for(Entry<String, Map<String, TupleInfo>> entry : m_common_percentage.entrySet())
			{
				for(Entry<String, TupleInfo> sub_entry : entry.getValue().entrySet())
				{
					if(sub_entry.getValue().m_common_modified_count > 3)
					{
						l_bufferBufferedWriter.write(entry.getKey() + ";" + sub_entry.getKey() + ";" + sub_entry.getValue().m_percentage + ";" +
								sub_entry.getValue().m_common_modified_count + ";" + sub_entry.getValue().m_total_modified_count + "\n");
					}
				}
			}
			
			l_bufferBufferedWriter.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
