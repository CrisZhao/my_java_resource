package persist.xmlconfig;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;


public class BeanSerializer <T extends Object>{

	private JAXBContext contextObj = null;
	private Unmarshaller unmarshaller = null;
	private Marshaller marshallerObj = null;
	
	public BeanSerializer(Class<T> clazz) throws JAXBException{
		contextObj = JAXBContext.newInstance(clazz);
		unmarshaller = contextObj.createUnmarshaller();
		marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	}
	
	protected void setNamespacePrefixMapper(Object mapper) throws PropertyException{
	    marshallerObj.setProperty("com.sun.xml.bind.namespacePrefixMapper", mapper);
	}
	
	@SuppressWarnings("unchecked")
	public T fromXml(String xml) throws JAXBException {

		return (T)unmarshaller.unmarshal(new ByteArrayInputStream(xml.getBytes()));
	}
	
	public String toXml(T context) throws JAXBException {
		StringWriter sw = new StringWriter();
		marshallerObj .marshal(context, sw);
		return sw.toString();
	}	

}
