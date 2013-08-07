package com.example.snmp4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.snmp4j.smi.VariableBinding;
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

	public List<String> get(List<OID> oids) throws IOException {
		List<String> ret = new ArrayList<>();
		PDU pdu = new PDU();
		pdu.setType(PDU.GET);
		for (OID oid : oids) {
			pdu.add(new VariableBinding(oid));
		}
		ResponseEvent responseEvent = snmp.get(pdu, getTarget());
		for (VariableBinding vb : responseEvent.getResponse()
				.getVariableBindings()) {
			ret.add(vb.getVariable().toString());
		}
		return ret;
	}

	Target getTarget() {
		Address address = GenericAddress.parse("udp:" + ip + "/" + port);
		CommunityTarget target = new CommunityTarget(address, new OctetString(
				"public"));
		target.setRetries(3);
		target.setTimeout(1500);
		target.setVersion(SnmpConstants.version1);
		return target;
	}

}
