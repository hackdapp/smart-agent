package com.cloudwise.smartagent.component;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SigarProxy;
import org.hyperic.sigar.SigarProxyCache;

import com.cloudwise.smartagent.component.discover.model.FileSystemData;


public class HostTest {

	public static void main(String[] args) throws SigarException {
		HostProperties test = HostProperties.TYPE_CPU_USE_RATE;
		testHost();
	}

	public static void testHost() throws SigarException {
		SigarProxy sigar = SigarProxyCache.newInstance();
		FileSystem[] fileSystemList =sigar.getFileSystemList();
		for (FileSystem fs : fileSystemList) {
			FileSystemData fsData = FileSystemData.gather(sigar, fs);
			if (fsData.getStat() != null && fs.getType() != 1) {
				System.out.print(fs.getDevName());
			}
		}
	}
	
	 

}
