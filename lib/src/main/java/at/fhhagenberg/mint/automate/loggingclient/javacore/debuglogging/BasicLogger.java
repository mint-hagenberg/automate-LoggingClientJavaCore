package at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging;

import java.util.ArrayList;
import java.util.List;

import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.filter.LogFilter;
import at.fhhagenberg.mint.automate.loggingclient.javacore.debuglogging.formatter.LogFormatter;

/**
 * A basic implementation of the log listener that allows adding of filters.
 */
public abstract class BasicLogger implements LogListener {
	private LogFormatter mFormatter;
	private List<LogFilter> mFilters = new ArrayList<>();

	public BasicLogger(LogFilter filter, LogFormatter formatter) {
		if (filter != null) {
			addFilter(filter);
		}

		mFormatter = formatter;
	}

	/**
	 * Return all the registered filters.
	 *
	 * @return -
	 */
	public List<LogFilter> getFilters() {
		return mFilters;
	}

	/**
	 * Add a filter.
	 *
	 * @param filter -
	 */
	public final void addFilter(LogFilter filter) {
		mFilters.add(filter);
	}

	/**
	 * Remove a filter.
	 *
	 * @param filter -
	 */
	public final void removeFilter(LogFilter filter) {
		mFilters.remove(filter);
	}

	/**
	 * Remove all filters.
	 */
	public final void removeAllFilters() {
		mFilters.clear();
	}

	/**
	 * Get the set formatter.
	 *
	 * @return -
	 */
	public LogFormatter getFormatter() {
		return mFormatter;
	}
}
