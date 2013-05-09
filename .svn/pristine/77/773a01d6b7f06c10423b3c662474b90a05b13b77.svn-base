package persist.xmlconfig;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.bind.JAXBException;

import exception.ConfigException;
import exception.ErrorCode;

public abstract class BaseConfigHandler<N extends IConfigNode> {
	
	private static final String HOMEVARIABLEKEY = "%KHFRAMEHOME%";

	protected Map<String, N> nodes = new ConcurrentHashMap<String, N>();

	protected BeanSerializer<Entries> entrySerializer = null;
	protected BeanSerializer<N> nodeSerializer = null;
	private String homeDir = null;
	protected String location = null;
	private String entryFileFullName = null;

	public BaseConfigHandler(Class<N> nodeClazz, String home, String location,
			String entryFileName) throws ConfigException {
		try {
			entrySerializer = new BeanSerializer<Entries>(Entries.class);
			nodeSerializer = new BeanSerializer<N>(nodeClazz);
		} catch (Exception e) {
			String msg = "Can not initialize serializers";
			throw new ConfigException(ErrorCode.CONFIG_ERROR, msg);
		}

		this.homeDir = home;
		this.location = location;
		this.entryFileFullName = getPhysicalFileName(location + entryFileName);

	}
	
	public N getNode(String name) {
		return nodes.get(name);
	}
	
	public Collection<String> getIds() {
		return nodes.keySet();
	}

	public Collection<N> getNodes() {
		return nodes.values();
	}
	
	protected void initCaches(Entries entries) throws ConfigException {
		if (new File(getEntryFileFullName()).exists()) {
			getNodes(entries);
		} else {
			String msg = "Can not find entry file " + getEntryFileFullName();
			throw new ConfigException(ErrorCode.CONFIG_ERROR, msg);
		}

	}
	
	protected String getPhysicalFileName(String fname) {
		return fname.replaceAll(HOMEVARIABLEKEY, homeDir);
	}
	
	protected String getEntryFileFullName() {
		return this.entryFileFullName;
	}

	private Entries getEntries() throws IOException, JAXBException {
		String xml = FileUtils.readTextFile(entryFileFullName);
		return entrySerializer.fromXml(xml);
	}

	private void getNodes(Entries entries)
			throws ConfigException {
		try {
			entries = getEntries();
		} catch (Exception e) {
			String msg = "Can not load " + entryFileFullName;
			throw new ConfigException(ErrorCode.CONFIG_ERROR, msg);
		}
		for (Entry entry : entries.getEntries()) {
			try {
				N n = getNode(entry);

				nodes.put(n.getId(), n);
			} catch (Exception e) {
				String msg = "Can not load " + entry.getFileName();
				throw new ConfigException(ErrorCode.CONFIG_ERROR, msg);
			}
		}
	}


	private N getNode(Entry entry) throws JAXBException, IOException {
		return nodeSerializer.fromXml(FileUtils
				.readTextFile(getPhysicalFileName(entry.getFileName())));
	}
}
