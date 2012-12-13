import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;

public class main {
    // For a given polling location,
    // the number of residents (population)
    // and the number of families
	static class PopFamilies {
		public int population;
		public int num_families;
	}

    // Define a sort order on the class, so we can sort a list of them	
	static class PopFamiliesComparator implements Comparator<PopFamilies> {
		public int compare(PopFamilies p1, PopFamilies p2) {
			// Avoid division by zero in the comparison
			if (p1.num_families == 0 || p2.num_families == 0) {
				// We're comparing denominators,
				// so flip the sense of the comparison
				return p1.num_families - p2.num_families;
			}

			double p1_density = ((double)p1.population)/((double)p1.num_families);
			double p2_density = ((double)p2.population)/((double)p2.num_families);

			// Can't simply subtract the two and return the result,
			// as the difference may overflow an integer.
			// So, write a bunch of "if"'s.
			if (p1_density < p2_density) {
				return -1;
			} else if (p1_density > p2_density) {
				return 1;
			} else {
				return 0;
			}
		}
	}

    /**
	 * Extract columns from a comma-separated record
	 * 	
	 * @param record A single comma-separated record
	 * 	
	 * So for example, 'getFields("a,b,c,d,e,f,g")'
	 * would return {"a", "b", "c", "d", "e", "f", "g"}
	 */
	static List<String> getFields(String record) {
		return Arrays.asList(record.split(","));
	}

	/**
	 * Given a census data file 00001 (containing the number of residents)
	 * and a census data file 00004 (containing answers to questions about family size),
	 * emit a list of tuples for each block in both files, of
	 * numbers of residents and numbers of families in each block.
	 */
	static List<PopFamilies> computeHouseholdsPerPopulation(String population_file, String families_file) throws java.io.IOException, java.io.FileNotFoundException {
		// First, read in both data files
		List<String> population_raw_data = new ArrayList<String>();
		List<String> families_raw_data = new ArrayList<String>();
		BufferedReader in;
		String line;

		in = new BufferedReader(new FileReader(population_file));
		while ((line = in.readLine()) != null) {
			population_raw_data.add(line);
		}
		in.close();

		in = new BufferedReader(new FileReader(families_file));
		while ((line = in.readLine()) != null) {
			families_raw_data.add(line);
		}
		in.close();
		
		// Second, join the two input files together by record ID
		// and emit PopFamilies structs		
		// (See DataLayout.txt; we could probably be smarter here...)
		List<PopFamilies> join_data = new ArrayList<PopFamilies>();
		for (String pop_rec : population_raw_data) {
			List<String> pop_fields = getFields(pop_rec);

			// The ID column is always the fifth column
			// Zero-indexing makes that column #4.
			for (String fam_rec : families_raw_data) {
				List<String> fam_fields = getFields(fam_rec);
				
				if (fam_fields.get(4).equals(pop_fields.get(4))) {
					PopFamilies p = new PopFamilies();
					p.population = Integer.parseInt(pop_fields.get(5));
					p.num_families = Integer.parseInt(fam_fields.get(7)) /* Married couple */
						+ Integer.parseInt(fam_fields.get(14)) /* Single Father */
						+ Integer.parseInt(fam_fields.get(20)) /* Single Mother */
						;

					join_data.add(p);

					// Optimization:  Each record only appears once per file;
					// so if we've found it, no need to keep searching.
					break;
				}
			}
		}

		// Omit empty records
		for (Iterator<PopFamilies> it = join_data.iterator(); it.hasNext(); ) {
			PopFamilies p = it.next();
			if (p.population < 100 || p.num_families < 10) {
				it.remove();
			}
		}

		return join_data;
	}

	public static void main(String[] args) throws java.io.IOException, java.io.FileNotFoundException {
		// Record data that we care about
		List<PopFamilies> records = new ArrayList<PopFamilies>();
		
		if (args.length < 2) {
			System.err.println("Please input at least two arguments; a number of max and in values to print, and at least one pair of input files.");
			System.err.println("For example: java " + main.class.getName() + " 5 ma00001.uf1,ma00004.uf1");
			System.exit(-1);
		}

		// Get the number of max's and min's to print
		final long K = Integer.parseInt(args[0]);
	   
		// Iterate over command-line arguments
		// One pair of input files per arg
		for (int i = 1; i < args.length; i++) {
			List<String> files = getFields(args[i]);
			if (files.size() != 2) {
				System.err.println("Please specify a list of 'file00001.uf1,file00004,uf1' pairs");
				System.exit(-1);
			}

			List<PopFamilies> batch_result = computeHouseholdsPerPopulation(files.get(0), files.get(1));
			records.addAll(batch_result);
		}

		// Sort the input data
		Collections.sort(records, new PopFamiliesComparator());

		// Print out the results
		for (int i = 0; i < Math.min(K, records.size()); i++) {
			// We didn't store the ratio, so re-compute it.	
			double ratio = ((double)records.get(i).population)/((double)records.get(i).num_families);
			System.out.println(String.format("%d\t%d\t%f", records.get(i).population, records.get(i).num_families, ratio));
		}

		System.out.println("...");

		for (int i = Math.max(0, records.size()-(int)K); i < records.size(); i++) {
			// We didn't store the ratio, so re-compute it.	
			double ratio = ((double)records.get(i).population)/((double)records.get(i).num_families);
			System.out.println(String.format("%d\t%d\t%f", records.get(i).population, records.get(i).num_families, ratio));
		}
	}

}
