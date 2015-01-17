package org.violations;

import java.io.File;

public class Main {

	public final static String PATH_PREFIX = ""; 
	public final static int CO_CHANGES_NUMBER = 2; 
//	public final static String CONVERSION_PREFIX = "/ant/core/trunk/src/main/";
//	public final static String CONVERSION_PREFIX = "/trunk/src_new/";
//	public final static String CONVERSION_PREFIX = "/lucene/dev/trunk/lucene/src/java/";
	public final static String CONVERSION_PREFIX = "/trunk/src/";

	public static void main(String[] args) {
		ViolationsManager l_manager = new ViolationsManager();

//		File folder = new File(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/");
//		File folder = new File(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/violations/argouml_violations/");
//		File folder = new File(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/violations/lucene_violations/");
		File folder = new File(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/violations/sweethome3d_violations/");
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

		//		//l_manager.write_graph();
		//		//l_manager.write_degree();
		//		 l_manager.write_package_degree();


//		SVNLogReader l_log_reader = new SVNLogReader(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/ant_commits_timed.txt");
//		SVNLogReader l_log_reader = new SVNLogReader(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/argo_commits_timed.txt");
//		SVNLogReader l_log_reader = new SVNLogReader(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/lucene_commits_timed.txt");
		SVNLogReader l_log_reader = new SVNLogReader(PATH_PREFIX + "/ArchitectureViolationsMiner/trunk/data/sweethome3d_commits_timed.txt");
		l_log_reader.set_classes_with_violations(l_manager.get_violations());
		l_log_reader.read_file();
		l_log_reader.calc_common_change_percentage();
		l_log_reader.write_data();

	}

}
