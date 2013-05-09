package persist.xmlconfig;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.PropertyException;

import persist.xmlconfig.Entry.FileType;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import com.sun.xml.bind.v2.WellKnownNamespace;

import exception.ConfigException;
import exception.ErrorCode;

public class ConfigHandler<N extends IConfigNode> extends BaseConfigHandler<N> {


	private Entries entries = new Entries();

	public ConfigHandler(Class<N> nodeClazz, String home, String location,
			String entryFileName) throws ConfigException {
		super(nodeClazz, home, location, entryFileName);
		initCaches(entries);
		initNamespace(getNamespaceUris());
	}

    protected void initNamespace(String[] uris) throws ConfigException {
        if (uris != null) {
            try {
                final Set<String> ns = new HashSet<String>();
                ns.addAll(Arrays.asList(uris));
                ns.add(WellKnownNamespace.XML_SCHEMA_INSTANCE);
                ns.add(WellKnownNamespace.XML_SCHEMA);
                final String[] namespaces = ns.toArray(new String[] {});
                nodeSerializer
                        .setNamespacePrefixMapper(new NamespacePrefixMapper() {
                            @Override
                            public String[] getPreDeclaredNamespaceUris() {
                                return namespaces;
                            }

                            @Override
                            public String getPreferredPrefix(
                                    String namespaceUri, String suggestion,
                                    boolean requirePrefix) {
                                return suggestion;
                            }
                        });
            } catch (PropertyException e) {
                String msg = "Can not init Namespace "
                        + Arrays.deepToString(uris);
                throw new ConfigException(ErrorCode.CONFIG_ERROR, msg);
            }
        }
    }

	protected String[] getNamespaceUris() {
        return null;
    }

    public void addNode(N node) throws ConfigException {
		nodes.put(node.getId(), node);

		persistContext(node);

		persistEntry();

	}

	private void persistContext(N node) throws ConfigException {
		String fname = getPhysicalFileName(getNodeFileName(node.getId()));
		try {
			String xml = nodeSerializer.toXml(node);
			FileOutputStream f = new FileOutputStream(fname);
			FileUtils.streamCopy(new ByteArrayInputStream(xml.getBytes()), f);
			f.close();
		} catch (Exception e) {
			String msg = "Can not save " + fname;
			throw new ConfigException(ErrorCode.CONFIG_ERROR, msg);
		}
	}

	public void deleteNode(String id) throws ConfigException {
		if (nodes.containsKey(id)) {
			nodes.remove(id);

			persistEntry();

			String fname = getPhysicalFileName(getNodeFileName(id));

			new File(fname).delete();
		}
	}

	

	private void persistEntry() throws ConfigException {
		Entries entries = new Entries();
		for (N node : nodes.values()) {
			entries.addEntry(new Entry(getNodeFileName(node.getId()),
					FileType.XML));
		}
		try {
			String entryXml = entrySerializer.toXml(entries);
			FileOutputStream f = new FileOutputStream(getEntryFileFullName());
			FileUtils.streamCopy(new ByteArrayInputStream(entryXml.getBytes()),
					f);
			f.close();
		} catch (Exception e) {
			String msg = "Can not load " + getEntryFileFullName();
			throw new ConfigException(ErrorCode.CONFIG_ERROR, msg);
		}
	}

	private String getNodeFileName(String contextName) {
		return location + contextName + ".xml";
	}

}
