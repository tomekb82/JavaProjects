package com.io_decorator;

import java.io.*;

public class LowerCaseInputStream extends FilterInputStream {

	public LowerCaseInputStream(InputStream in){
		super(in);
	}
}
