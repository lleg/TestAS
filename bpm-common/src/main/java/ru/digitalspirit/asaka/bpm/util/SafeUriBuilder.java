package ru.digitalspirit.asaka.bpm.util;

import com.google.common.base.MoreObjects;
import org.apache.http.client.utils.URIBuilder;
import ru.digitalspirit.asaka.bpm.exception.BpmApiException;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Builder for {@link URI} instances.
 * Added some useful methods.
 * Doesn't throw checked exceptions.
 */
public class SafeUriBuilder extends URIBuilder {

	private String pathId;

	/**
     * Constructs an empty instance.
     */
	@SuppressWarnings("unused")
	public SafeUriBuilder() {
		super();
	}
	
	/**
	 * Creates SafeUriBuilder instance from given URI.
	 * @param source is a URI to build from.
	 */
	public SafeUriBuilder(URI source) {
		fromUri(source);
	}
	
	/**
	 * Constructs the SafeUriBuilder from given URI.
	 * @param source is a URI to build from.
	 * @return this instance of SafeUriBuilder.
	 */
	private SafeUriBuilder fromUri(URI source) {
		setScheme(source.getScheme());
		setHost(source.getHost());
		setPort(source.getPort());
		setPath(source.getPath());
		return this;
	}
	
	/**
	 * Builds new path of SafeUriBuilder, which contain's old path with new one appended.
	 * @param path new segment to add.
	 * @return this instance of SafeUriBuilder.
	 */
	public SafeUriBuilder addPath(String path) {
		setPath(getPath() + normalizePath(path));
		return this;
	}

	/**
	 * Set new path id of SafeUriBuilder
	 * @param pathId new path id.
	 * @return this instance of SafeUriBuilder.
	 */
	public SafeUriBuilder setPathId(String pathId) {
		this.pathId = pathId;
		return this;
	}
	
	/**
	 * Replaces '/' from the path end and add '/' to beggining if them don't present.
	 * @param path given new segment of path.
	 * @return normalized path.
	 */
	private static String normalizePath(String path) {
		//Avoid NPE
		path = MoreObjects.firstNonNull(path, Constants.EMPTY_STRING);
		if (path.endsWith(Constants.SLASH)) {
			path = path.substring(0, path.length() - 1);
		}
		if (!path.startsWith(Constants.SLASH)) {
			path = Constants.SLASH + path;
		}
		return path;
	}

	@Override
	public URI build() {
		try {		
			return addPathId(super.build());
		} catch (URISyntaxException e) {
			throw new BpmApiException("Can't build Uri for: " + getScheme() + getHost() + getPort() + getPath(), e);
		} 
	}

	@Override
	public SafeUriBuilder addParameter(String param, String value) {
		super.addParameter(param, value);
		return this;
	} 
	
	public SafeUriBuilder addParameter(String param, Object value) {
		return this.addParameter(param, String.valueOf(value));
	}

	private URI addPathId(URI uri) throws URISyntaxException {
		if (this.pathId == null || "".equals(pathId)) {
			return uri;
		}

		StringBuilder sbUri = new StringBuilder(uri.toString());
		int insertIndex = sbUri.indexOf("?");
		insertIndex = insertIndex < 0 ? sbUri.length() : insertIndex;

		sbUri.insert(insertIndex, '/');
		sbUri.insert(insertIndex + 1, getEncodedPathId());

		return new URI(sbUri.toString());
	}

	private String getEncodedPathId() throws URISyntaxException {
		return new URI(null, null, this.pathId, null).getRawPath().replaceAll("/", "%2F");
	}

}
