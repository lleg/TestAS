package ru.digitalspirit.asaka.bpm.api.client;

import java.io.Closeable;


/**
 * Public root api client.
 * You can obtaint different api client's through it.
 * {@link BpmClient} propagates all it's settings to another client's.
 */
public interface BpmClient extends Closeable {

	/**
	 * Client for actions on exposed bpm api.
	 * @return {@link ExposedClient}
	 */
	ExposedClient getExposedClient();
	
	/**
	 * Client for actions on process bpm api.
	 * @return {@link ProcessClient}
	 */
	ProcessClient getProcessClient();
	
	/**
	 * Client for actions on task bpm api.
	 * @return {@link TaskClient}
	 */
	TaskClient getTaskClient();
	
	/**
	 * Client for actions on process apps bpm api.
	 * @return {@link ProcessAppsClient}
	 */
	ProcessAppsClient getProcessAppsClient();

	/**
	 * Client for actions on task query bpm api;
	 * @return {@link QueryClient}
	 */
	QueryClient getTaskQueryClient();
	
	/**
	 * Client for actions on task template query bpm api;
	 * @return {@link QueryClient}
	 */
	QueryClient getTaskTemplateQueryClient();
	
	/**
	 * Client for actions on process query bpm api;
	 * @return {@link QueryClient}
	 */
	QueryClient getProcessQueryClient();

	/**
	 * Client for actions on service bpm api;
	 * @return {@link ServiceClient}
	 */
	ServiceClient getServiceClient();

	/**
	 * Client for actions on organisation bpm api;
	 * @return {@link OrganisationClient}
	 */
	OrganisationClient getOrganisationClient();
}
