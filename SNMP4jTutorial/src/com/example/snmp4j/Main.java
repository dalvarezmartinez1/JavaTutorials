package com.example.snmp4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.snmp4j.smi.OID;

public class Main {

	// http://www.jayway.com/2010/05/21/introduction-to-snmp4j/
	public static void main(String[] args) throws IOException {
		SNMPManager manager = new SNMPManager("127.0.0.1", 161);
		manager.start();

		List<OID> oids = new ArrayList<OID>();

		// Our OIDs are: 1.3.6.1.2.1.1.1,1.3.6.1.2.1.1.2, 1.3.6.1.2.1.1.3
		// You actually have to add a "0" as the instance i think to the OID.
		// System description
		oids.add(new OID("1.3.6.1.2.1.1.1.0"));
		// System ObjID
		oids.add(new OID("1.3.6.1.2.1.1.2.0"));
		// System uptime
		oids.add(new OID("1.3.6.1.2.1.1.3.0"));

		List<String> ret = manager.get(oids);

		System.out.println(ret);
	}

}
