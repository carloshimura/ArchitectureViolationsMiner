package org.violations;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ViolationsManager 
{
	private Map<String, String> m_files;
	private Map<String, List<ViolationInfo>> m_info;
	
	public ViolationsManager()
	{
		m_files = new LinkedHashMap<String, String>();
		m_info = new LinkedHashMap<String, List<ViolationInfo>>();
	}

	public void add_file(String version, String file)
	{
		if(!m_files.containsKey(version))
			m_files.put(version, file);
		ViolationsReader l_reader = new ViolationsReader(file, version, this);
		l_reader.readViolations();
	}
	
	public void add_violations(String version, ViolationInfo info)
	{
		if(!m_info.containsKey(version))
			m_info.put(version, new LinkedList<ViolationInfo>());
		m_info.get(version).add(info);
	}
	
	public void write_transactions()
	{
		FileOutputStream l_output_stream;
		try {
			l_output_stream = new FileOutputStream("/home/local/CEDROFINANCES/carlos.sabino/Masters/transactions_classes.txt");
			BufferedWriter l_bufferBufferedWriter = new BufferedWriter(new OutputStreamWriter(l_output_stream));
			for(Entry<String,  List<ViolationInfo>> l_entry : m_info.entrySet())
			{
				for(ViolationInfo l_info : l_entry.getValue())
				{
					if(!l_info.hasNullComponents() && !l_info.equals("unchanged"))
						l_bufferBufferedWriter.write(l_info.toStringClasses() + "\n");
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
	
	public void write_graph()
	{
		List<ViolationPair> l_pairs_list = new LinkedList<ViolationPair>();
		
		for(Entry<String,  List<ViolationInfo>> l_entry : m_info.entrySet())
		{
			for(ViolationInfo l_info : l_entry.getValue())
			{
				ViolationPair l_pair = new ViolationPair(l_info.m_origin_class, l_info.m_destiny_class, l_info);
				boolean l_found = false;
				for(ViolationPair pair : l_pairs_list)
				{
					if(pair.equals(l_pair))
					{
						l_found = true;
						break;
					}
				}
				
				if(!l_found)
					l_pairs_list.add(l_pair);
			}
		}
		
		
		FileOutputStream l_output_stream;
		try {
			l_output_stream = new FileOutputStream("/home/local/CEDROFINANCES/carlos.sabino/Masters/graph_full.dot");
			BufferedWriter l_bufferBufferedWriter = new BufferedWriter(new OutputStreamWriter(l_output_stream));
			l_bufferBufferedWriter.write("digraph G {\n");
			for(ViolationPair pair : l_pairs_list)
			{
				if(!pair.m_info.hasNullComponents() && !pair.m_info.equals("unchanged"))
				{
					String l_origin = pair.m_origin.replace("org.apache.tools.ant.", "").replaceAll("\\.", "_");
					String l_destiny = pair.m_destiny.replace("org.apache.tools.ant.", "").replaceAll("\\.", "_");
					l_bufferBufferedWriter.write(l_origin + " -> " + l_destiny + ";\n");
				}
			}
//			for(Entry<String,  List<ViolationInfo>> l_entry : m_info.entrySet())
//			{
//				for(ViolationInfo l_info : l_entry.getValue())
//				{
//					if(!l_info.hasNullComponents() && !l_info.equals("unchanged"))
//						l_bufferBufferedWriter.write(l_info.m_origin_class.replaceAll("\\.", "_") + " -> " + l_info.m_destiny_class.replaceAll("\\.", "_") + ";\n");
//				}
//			}
			l_bufferBufferedWriter.write("}\n");
			l_bufferBufferedWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void write_degree()
	{
		Map<String, Map<String,NodeDegree>> l_pairs_list = new HashMap<String, Map<String,NodeDegree>>();
		
		List<ViolationPair> l_viol_pairs_list = new LinkedList<ViolationPair>();
		
		for(Entry<String,  List<ViolationInfo>> l_entry : m_info.entrySet())
		{
			for(ViolationInfo l_info : l_entry.getValue())
			{
				ViolationPair l_pair = new ViolationPair(l_info.m_origin_class, l_info.m_destiny_class, l_info);
				boolean l_found = false;
				for(ViolationPair pair : l_viol_pairs_list)
				{
					if(pair.equals(l_pair))
					{
						l_found = true;
						break;
					}
				}
				
				if(!l_found)
				{
					l_viol_pairs_list.add(l_pair);
					if(l_pairs_list.containsKey(l_info.m_origin_class))
					{
						if(l_pairs_list.get(l_info.m_origin_class).containsKey(l_info.m_version))
							++(l_pairs_list.get(l_info.m_origin_class).get(l_info.m_version).m_outdegree);
						else
							l_pairs_list.get(l_info.m_origin_class).put(l_info.m_version, new NodeDegree(0,1));
					}
					else
					{
						Map<String,NodeDegree> l_v_map = new HashMap<String, NodeDegree>();
						l_v_map.put(l_info.m_version, new NodeDegree(0,1));
						l_pairs_list.put(l_info.m_origin_class, l_v_map);
					}
					
					if(l_pairs_list.containsKey(l_info.m_destiny_class))
					{
						if(l_pairs_list.get(l_info.m_origin_class).containsKey(l_info.m_version))
							++(l_pairs_list.get(l_info.m_origin_class).get(l_info.m_version).m_indegree);
						else
							l_pairs_list.get(l_info.m_origin_class).put(l_info.m_version, new NodeDegree(1,0));
					}
					else
					{
						Map<String,NodeDegree> l_v_map = new HashMap<String, NodeDegree>();
						l_v_map.put(l_info.m_version, new NodeDegree(0,1));
						l_pairs_list.put(l_info.m_destiny_class, l_v_map);
					}
				}
			}
		}
		
		
		FileOutputStream l_output_stream;
		try {
			l_output_stream = new FileOutputStream("/home/local/CEDROFINANCES/carlos.sabino/Masters/graph_degree.csv");
			BufferedWriter l_bufferBufferedWriter = new BufferedWriter(new OutputStreamWriter(l_output_stream));
			
			l_bufferBufferedWriter.write("Class;Indegree;Outdegree\n");
			
			for(Entry<String, Map<String,NodeDegree>> pair_master : l_pairs_list.entrySet())
			{
				l_bufferBufferedWriter.write(pair_master.getKey());
				for(Entry<String,NodeDegree> pair : pair_master.getValue().entrySet())
				{
					l_bufferBufferedWriter.write(";" + pair.getValue().m_indegree + ";" + pair.getValue().m_outdegree);
				}
				l_bufferBufferedWriter.write("\n");
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
