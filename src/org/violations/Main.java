package org.violations;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		ViolationsManager l_manager = new ViolationsManager();
//		l_manager
//				.add_file(
//						"01",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_014_rev_500752_date_2007-01-28.txt");
//		l_manager
//				.add_file(
//						"02",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_015_rev_503370_date_2007-02-04.txt");
//		l_manager
//				.add_file(
//						"03",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_016_rev_505861_date_2007-02-11.txt");
//		l_manager
//				.add_file(
//						"04",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_017_rev_508885_date_2007-02-18.txt");
//		l_manager
//				.add_file(
//						"05",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_018_rev_511460_date_2007-02-25.txt");
//		l_manager
//				.add_file(
//						"06",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_019_rev_514368_date_2007-03-04.txt");
//		l_manager
//				.add_file(
//						"07",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_020_rev_516865_date_2007-03-11.txt");
//		l_manager
//				.add_file(
//						"08",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_021_rev_519567_date_2007-03-18.txt");
//		l_manager
//				.add_file(
//						"09",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_022_rev_522169_date_2007-03-25.txt");
//		l_manager
//				.add_file(
//						"10",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_023_rev_524546_date_2007-04-01.txt");
//		l_manager
//				.add_file(
//						"11",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_024_rev_526535_date_2007-04-08.txt");
//		l_manager
//				.add_file(
//						"12",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_025_rev_528937_date_2007-04-15.txt");
//		l_manager
//				.add_file(
//						"13",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_026_rev_531149_date_2007-04-22.txt");
//		l_manager
//				.add_file(
//						"14",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_027_rev_533474_date_2007-04-29.txt");
//		l_manager
//				.add_file(
//						"15",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_028_rev_535584_date_2007-05-06.txt");
//		l_manager
//				.add_file(
//						"16",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_029_rev_537555_date_2007-05-13.txt");
//		l_manager
//				.add_file(
//						"17",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_030_rev_539837_date_2007-05-20.txt");
//		l_manager
//				.add_file(
//						"18",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_031_rev_541965_date_2007-05-27.txt");
//		l_manager
//				.add_file(
//						"19",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_032_rev_543852_date_2007-06-03.txt");
//		l_manager
//				.add_file(
//						"20",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_033_rev_545849_date_2007-06-10.txt");
//		l_manager
//				.add_file(
//						"21",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_034_rev_548012_date_2007-06-17.txt");
//		l_manager
//				.add_file(
//						"22",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_035_rev_550172_date_2007-06-24.txt");
//		l_manager
//				.add_file(
//						"23",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_036_rev_552254_date_2007-07-01.txt");
//		l_manager
//				.add_file(
//						"24",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_037_rev_554329_date_2007-07-08.txt");
//		l_manager
//				.add_file(
//						"25",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_038_rev_556368_date_2007-07-15.txt");
//		l_manager
//				.add_file(
//						"26",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_039_rev_558463_date_2007-07-22.txt");
//		l_manager
//				.add_file(
//						"27",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_040_rev_560687_date_2007-07-29.txt");
//		l_manager
//				.add_file(
//						"28",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_041_rev_562838_date_2007-08-05.txt");
//		l_manager
//				.add_file(
//						"29",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_042_rev_565008_date_2007-08-12.txt");
//		l_manager
//				.add_file(
//						"30",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_043_rev_567361_date_2007-08-19.txt");
//		l_manager
//				.add_file(
//						"31",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_044_rev_569772_date_2007-08-26.txt");
//		l_manager
//				.add_file(
//						"32",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_045_rev_571925_date_2007-09-02.txt");
//		l_manager
//				.add_file(
//						"33",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_046_rev_573953_date_2007-09-09.txt");
//		l_manager
//				.add_file(
//						"34",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_047_rev_576054_date_2007-09-16.txt");
//		l_manager
//				.add_file(
//						"35",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_048_rev_578552_date_2007-09-23.txt");
//		l_manager
//				.add_file(
//						"36",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_049_rev_580679_date_2007-09-30.txt");
//		l_manager
//				.add_file(
//						"37",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_050_rev_582584_date_2007-10-07.txt");
//		l_manager
//				.add_file(
//						"38",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_051_rev_584500_date_2007-10-14.txt");
//		l_manager
//				.add_file(
//						"39",
//						"/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/ant_week_052_rev_626825_date_2007-10-21.txt");
		
		
//		File folder = new File("/ArchitectureViolationsMiner/trunk/data/violations/ant_violations/");
		File folder = new File("/ArchitectureViolationsMiner/trunk/data/violations/argouml_violations/");
//		File folder = new File("/ArchitectureViolationsMiner/trunk/data/violations/lucene_violations/");
//		File folder = new File("/ArchitectureViolationsMiner/trunk/data/violations/sweethome3d_violations/");
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
