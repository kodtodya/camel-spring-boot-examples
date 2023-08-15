package com.mycompany.camel.activemq.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileWriterService {

	public void writeMsg(String message){
	try {
		FileWriter fileWriter = new FileWriter(new File("/home/alele/_tech/testData/input/"+message.substring(0, 5)+".txt"));
		fileWriter.write(message);
		fileWriter.flush();
		fileWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
}
