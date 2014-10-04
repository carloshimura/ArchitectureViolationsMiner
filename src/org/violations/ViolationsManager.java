package org.violations;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
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
}
