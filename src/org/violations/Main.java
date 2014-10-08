package org.violations;

public class Main {

	public static void main(String[] args) {
		ViolationsManager l_manager = new ViolationsManager();
		l_manager.add_file("1", 
				"/home/local/CEDROFINANCES/carlos.sabino/Masters/violations/violations/ant_week_014_rev_500752_date_2007-01-28.txt");
		l_manager.add_file("2", 
				"/home/local/CEDROFINANCES/carlos.sabino/Masters/violations/violations/ant_week_015_rev_503370_date_2007-02-04.txt");
		l_manager.add_file("3", 
				"/home/local/CEDROFINANCES/carlos.sabino/Masters/violations/violations/ant_week_016_rev_505861_date_2007-02-11.txt");
		
		l_manager.write_graph();
	}

}
