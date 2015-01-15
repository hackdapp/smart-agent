package com.cloudwise.smartagent.component.discover;

import java.util.List;
import java.util.Map;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

import com.cloudwise.smartagent.component.HostProperties;
import com.cloudwise.smartagent.component.IDiscover;
import com.cloudwise.smartagent.component.discover.model.FileSystemData;
import com.cloudwise.smartagent.component.discover.model.IPAddressInfo;
import com.cloudwise.smartagent.component.discover.model.ServiceInfo;
import com.cloudwise.smartagent.utils.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class HostDisCover implements IDiscover {
	private static SigarProxy sigarProxy;

	public Map<String, Map> doDiscover() {
		Map<String, Integer> host = Maps.newHashMap();
		/*
		 * host.put("cpu_usage", HostProperties.TYPE_CPU_USE_RATE.);
		 * host.put("cpu_burden", Consts.TYPE_BURDEN); host.put("disk_io",
		 * Consts.TYPE_DISK_IO); host.put("disk_usage",
		 * Consts.TYPE_DISK_USE_RATE); host.put("mem", Consts.TYPE_MEM);
		 * host.put("proc", Consts.TYPE_SYSTEM_PROCESS); host.put("base",
		 * Consts.TYPE_HOST); host.put("lan", Consts.TYPE_LAN_TRAFFIC);
		 * host.put("tcp", Consts.TYPE_TCP);
		 */
		for (HostProperties properties : HostProperties.values()) {
			ServiceInfo baseInfo = new ServiceInfo();
			baseInfo.setService_name(properties.key());
			baseInfo.setService_type(properties.value());
			baseInfo.setService_qualifier(StringUtil.getformattedName(String
					.valueOf(properties.value())));

			if (properties == HostProperties.TYPE_DISK_IO
					|| properties == HostProperties.TYPE_DISK_USE_RATE) {
				baseInfo.setAttaches(getDiskIoInfo());
			} else if (properties.value() == Consts.TYPE_LAN_TRAFFIC) {
				attaches = new ArrayList<String>();
				String[] netInterfaceList = getSigar().getNetInterfaceList();
				for (String ni : netInterfaceList) {
					((List<String>) attaches).add(ni);
				}
			} else if (properties.value() == Consts.TYPE_HOST) {
				BaseModel attches = new BaseModel();
				// get hostName
				InetAddress addr = InetAddress.getLocalHost();
				attches.setHost_name(addr.getHostName().toString());

				// ip and mac address.
				ipAndMac(attches);

				attches.setTotalMem(getSigar().getMem().getTotal());

				CpuInfo[] cpuInfoList = getSigar().getCpuInfoList();
				for (CpuInfo ci : cpuInfoList) {
					attches.setCpuVendor(ci.getVendor());
					attches.setCpuModel(ci.getModel());
					break;
				}
				attches.setCpuNums(cpuInfoList.length);

				// ip address... China, ShangHai
				IPAddressInfo addressInfo = null;
				// IPAddressInfo addressInfo = getMapper().readValue(
				// getFromURI(Consts.ipInfoInterface +
				// serviceReport.getHost_ip()),
				// IPAddressInfo.class);
				// XXX
				if (addressInfo == null || addressInfo.getCode() == 0) {
					attches.setAddressInfo(addressInfo);
				}

				// OS properties.
				if (Consts.OS_IS_WIN) {
					attches.setHost_type(ServiceReportInfo.WINDOWS);
				} else {
					attches.setHost_type(ServiceReportInfo.LINUX);
				}
				tmpReportInfo.setMac(attches.getMac());
				attaches = attches;
				baseInfo.setVersion(OperatingSystem.getInstance()
						.getDescription()
						+ " "
						+ OperatingSystem.getInstance().getArch());
			}

		}
		return null;
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

	private SigarProxy getSigar() {
		if (sigarProxy == null) {
			sigarProxy = SigarProxyCache.newInstance();
		}
		return sigarProxy;
	}
}
