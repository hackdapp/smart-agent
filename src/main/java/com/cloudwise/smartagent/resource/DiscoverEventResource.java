package com.cloudwise.smartagent.resource;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

import com.cloudwise.smartagent.component.ComponentFactory;
import com.cloudwise.smartagent.component.IDiscover;
import com.cloudwise.smartagent.component.discover.model.BaseModel;
import com.cloudwise.smartagent.model.ServiceReportInfo;
import com.cloudwise.smartagent.schedule.IScheduleEvent;
import com.cloudwise.smartagent.utils.SystemTool;

public class DiscoverEventResource implements IScheduleEvent {
	private static final String ENDPOINT = "http://portal.toushibao.com/proxy_discover/postServices/{hostkey}";

	public String getName() {
		return "DiscoverEvent";
	}

	public void execute(Map map) {
		String localMacAddress = SystemTool.getMACAddress();
		ServiceReportInfo reportInfo = new ServiceReportInfo(localMacAddress);
		// 1.discover the host's services.
		List<IDiscover> discoverList = ComponentFactory.getInstance().getDiscoverList();
		for(IDiscover discover:discoverList){
			discover.doDiscover();
		}
	}

	private void ipAndMac(BaseModel tmpReportInfo)  {
		String content = Encryptor.decodeValue(FileUtils
				.readFileToString(new File(AgentFileHelper
						.getCoreIdentifierFile())));
		Map map = CacheCenter.getObjectMapper().readValue(content, Map.class);
		String[] netInterfaceList = getSigar().getNetInterfaceList();
		NetInterfaceConfig niConfig;

		File ipFile = new File(AgentFileHelper.getCoreConfIPFile());
		boolean is_certain_ip = false;
		String ip = null;

		AgentUtils.debugMsg("found ip config file : " + ipFile);

		if (ipFile.exists()) {
			ip = FileUtils.readFileToString(ipFile).trim();
			if (StringUtils.isBlank(ip)) {
				AgentUtils.debugMsg("No ip found");
			} else {
				is_certain_ip = true;
				AgentUtils.debugMsg("found ip : " + ip);
			}
		}

		for (String ni : netInterfaceList) {
			niConfig = CacheCenter.getSigar().getNetInterfaceConfig(ni);
			AgentUtils.debugMsg("type is : " + niConfig.getType()
					+ ", ip is : " + niConfig.getAddress() + ", mac is : "
					+ niConfig.getHwaddr());
			if (!niConfig.getType().contains("Loopback")
					&& !niConfig.getAddress().equals("0.0.0.0")) {
				if (is_certain_ip) {
					if (niConfig.getAddress().equalsIgnoreCase(ip)) {
						tmpReportInfo.setHost_ip(niConfig.getAddress());
						AgentUtils.debugMsg("WE GOT IP IS -> :"
								+ niConfig.getAddress());
						tmpReportInfo.setMac(niConfig.getHwaddr());
						AgentUtils.debugMsg(niConfig.getHwaddr()
								+ " >>>>>>>>>>>>>>>> " + niConfig.getAddress());
					} else {
						AgentUtils.debugMsg(niConfig.getHwaddr()
								+ " found----------------> but ip is : "
								+ niConfig.getAddress() + ", not " + ip);
					}
				} else {
					tmpReportInfo.setHost_ip(niConfig.getAddress());
					AgentUtils.debugMsg("WE GOT IP IS -> :"
							+ niConfig.getAddress());
					tmpReportInfo.setMac(niConfig.getHwaddr());
					AgentUtils.debugMsg(niConfig.getHwaddr()
							+ " >>>>>>>>>>>>>>>> " + niConfig.getAddress());
				}
			}
		}
		CacheCenter.setMac(tmpReportInfo.getMac());
		CacheCenter.setIp(tmpReportInfo.getHost_ip());
		if (null != map.get(Consts.FIELD_MAC)) {
			if (!tmpReportInfo.getMac().equals(map.get(Consts.FIELD_MAC))) {
				logger.error("This smart agent doesn't belong to current host");
				System.exit(0);
			}
		} else {
			map.put(Consts.FIELD_MAC, tmpReportInfo.getMac());
			FileUtils.writeStringToFile(
					new File(AgentFileHelper.getCoreIdentifierFile()),
					Encryptor.encode(getMapper().writeValueAsString(map)),
					Consts.CHARSET);
		}
	}

}
