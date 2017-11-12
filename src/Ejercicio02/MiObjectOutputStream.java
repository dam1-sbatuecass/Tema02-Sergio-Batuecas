package Ejercicio02;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {

	protected MiObjectOutputStream(FileOutputStream fos) throws IOException, SecurityException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void writeStreamHeader() throws IOException {
		// No hace nada.

	}

}
