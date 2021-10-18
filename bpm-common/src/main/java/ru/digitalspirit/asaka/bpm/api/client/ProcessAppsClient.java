package ru.digitalspirit.asaka.bpm.api.client;


import ru.digitalspirit.asaka.bpm.model.other.processapp.ProcessApps;

/**
 * Client for process apps api actions.
 */
public interface ProcessAppsClient {

	/**
	 * Use this method to retrieve the all process applications that are installed in the system.
	 * @return {@link ProcessApps}
	 */
    ProcessApps listProcessApps();
	
}
