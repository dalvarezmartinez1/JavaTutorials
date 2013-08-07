package com.example.snmp4j;

import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPManager {
	String ip;
	int port;
	Snmp snmp;
	
	public SNMPManager(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start() throws IOException {
		TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
		Snmp snmp = new Snmp(transport);
		this.snmp = snmp;
		snmp.listen();
	}
	
	public String get(OID oid) throws IOException {
		PDU pdu = new PDU();
		pdu.setType(PDU.GET);
		ResponseEvent responseEvent = snmp.get(pdu, getTarget());
		return responseEvent.getResponse().get(0).getVariable().toString();
	}
	
	Target getTarget() {
		Address address = GenericAddress.parse("udp:"+ip+"/"+port);
		CommunityTarget target = new CommunityTarget(address, new OctetString("public"));
		target.setRetries(3);
		target.setTimeout(1500);
		target.setVersion(SnmpConstants.version1);
		return target;
	}
	
	

}
