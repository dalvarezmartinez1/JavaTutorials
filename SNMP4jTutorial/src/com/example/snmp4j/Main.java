package com.example.snmp4j;

import java.io.IOException;

import org.snmp4j.smi.OID;

public class Main {

	
	
	// shivasoft.in/blog/java/snmp/create-snmp-client-in-java-using-snmp4j
	public static void main(String[] args) throws IOException {
		SNMPManager manager = new SNMPManager("127.0.0.1", 161);
		manager.start();
		
		String ret = manager.get(new OID(".1.3.6.1.2.1.1.5.0"));
		
		System.out.println(ret);
	}

}
