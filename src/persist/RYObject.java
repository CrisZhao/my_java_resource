package persist;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 
 * @author Criszhao
 * 
 */
public class RYObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3645776392828353820L;

	/**
	 * 
	 */
	public RYObject() {
	}

	/**
	 * 
	 * @param obj
	 * @return object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object cloneObject(Object obj) throws IOException,
			ClassNotFoundException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

		ObjectOutputStream out = new ObjectOutputStream(byteOut);

		out.writeObject(obj);

		ByteArrayInputStream byteIn = new ByteArrayInputStream(
				byteOut.toByteArray());

		ObjectInputStream in = new ObjectInputStream(byteIn);

		return in.readObject();

	}
}
