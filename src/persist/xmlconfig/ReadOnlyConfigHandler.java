package persist.xmlconfig;

import exception.ConfigException;

public class ReadOnlyConfigHandler<N extends IConfigNode> extends
		BaseConfigHandler<N> {


	public ReadOnlyConfigHandler(Class<N> nodeClazz, String home,
			String location, String entryFileName) throws ConfigException {
		super(nodeClazz, home, location, entryFileName);
		initCaches(new Entries());
	}

}
