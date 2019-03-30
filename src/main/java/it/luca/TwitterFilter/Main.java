package it.luca.TwitterFilter;

import it.luca.TwitterFilter.core.Twitter4JStreamer;
import it.luca.TwitterFilter.util.PropertyUtil;

/**
 *
 */
public class Main {
	public static void main(String[] args) {

		String filter = PropertyUtil.getPropertyValue("filters");
		String[] filters;

		if (filter == null) {
			System.out.println("The property \"filters\" must be present into the config.properties file");
		} else if (filter.equals("")) {
			Twitter4JStreamer.sampleSearch();
		} else {
			if (filter.contains(",")) {
				filters = filter.split(",");
			} else {
				filters = new String[] { filter };
			}

			Twitter4JStreamer.filteredSearch(filters);
		}
	}
}
