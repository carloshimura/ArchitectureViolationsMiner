package org.violations;

import java.io.File;

public class Main {
	
	public final static String PATH_PREFIX = "D:/Mestrado"; 

	public static void main(String[] args) {
		ViolationsManager l_manager = new ViolationsManager();
		
//		File folder = new File(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/");
		File folder = new File(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/violations/argouml_violations/");
//		File folder = new File(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/violations/lucene_violations/");
//		File folder = new File(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/violations/sweethome3d_violations/");
		 File[] listOfFiles = folder.listFiles();

		 int i = 1;
		 for (File file : listOfFiles) {
		     if (file.isFile()) 
		     {
		    	 String l_version = i + "";
		    	 while(l_version.length() < 2)
		    		 l_version = "0" + l_version;
		    	 l_manager.add_file(l_version, file.getAbsolutePath());
		    	 ++i;
		     }
		 }


		
		l_manager.write_graph();
		//l_manager.write_degree();
	}

}
