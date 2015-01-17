package com.cloudwise.smartagent.component.discover;

import java.util.List;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

import com.cloudwise.smartagent.component.HostMetric;
import com.cloudwise.smartagent.component.IDiscover;
import com.cloudwise.smartagent.component.discover.model.BaseModel;
import com.cloudwise.smartagent.component.discover.model.FileSystemData;
import com.cloudwise.smartagent.component.discover.model.ServiceInfo;
import com.cloudwise.smartagent.utils.StringUtil;
import com.cloudwise.smartagent.utils.SystemTool;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

public class HostDiscover implements IDiscover {
	private static SigarProxy sigarProxy;

	public List<ServiceInfo> doDiscover() {
		List<ServiceInfo> serviceList = Lists.newArrayList();
	
		for (HostMetric properties : HostMetric.values()) {
			ServiceInfo baseInfo = new ServiceInfo();
			baseInfo.setService_name(properties.key());
			baseInfo.setService_type(properties.value());
			baseInfo.setService_qualifier(StringUtil.getformattedName(String
					.valueOf(properties.value())));

			try {
				Object collectdata = null;
				if (properties == HostMetric.TYPE_DISK_IO
						|| properties == HostMetric.TYPE_DISK_USE_RATE) {
					collectdata = getDiskIoInfo();
				} else if (properties == HostMetric.TYPE_LAN_TRAFFIC) {
					collectdata = getLanTraffic();
				} else if (properties == HostMetric.TYPE_HOST) {
					collectdata = getHost();
					baseInfo.setVersion(OperatingSystem.getInstance()
							.getDescription()
							+ " "
							+ OperatingSystem.getInstance().getArch());
				}
				if(collectdata!=null){
					baseInfo.setAttaches(new ObjectMapper().writeValueAsString(collectdata));
				}
				serviceList.add(baseInfo);
			} catch (SigarException | JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return serviceList;
	}

	private List<String> getDiskIoInfo() throws SigarException {
		List<String> rtnList = Lists.newArrayList();
		FileSystem[] fileSystemList = getSigar().getFileSystemList();
		for (FileSystem fs : fileSystemList) {
			FileSystemData fsData = FileSystemData.gather(getSigar(), fs);
			if (fsData.getStat() != null && fs.getType() != 1) {
				rtnList.add(fs.getDevName());
			}
		}
		return rtnList;
	}

	private List<String> getLanTraffic() throws SigarException {
		return Lists.newArrayList(getSigar().getNetInterfaceList());
	}
	
	private BaseModel getHost() throws SigarException{
		BaseModel attches = new BaseModel();
		attches.setHost_name(SystemTool.getHostName());
		attches.setHost_ip(SystemTool.getIPAddress());
		attches.setMac(SystemTool.getMACAddress());
		attches.setTotalMem(getSigar().getMem().getTotal());
		CpuInfo[] cpuInfoList = getSigar().getCpuInfoList();
		for (CpuInfo ci : cpuInfoList) {
			attches.setCpuVendor(ci.getVendor());
			attches.setCpuModel(ci.getModel());
			break;
		}
		attches.setCpuNums(cpuInfoList.length);
		attches.setAddressInfo(null); // ip address... China, ShangHai
		attches.setHost_type(SystemTool.getOSType().value());
		return attches;
	}

	private SigarProxy getSigar() {
		if (sigarProxy == null) {
			sigarProxy = SigarProxyCache.newInstance();
		}
		return sigarProxy;
	}
}
