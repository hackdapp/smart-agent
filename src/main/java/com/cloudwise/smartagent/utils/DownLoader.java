/*package com.cloudwise.smartagent.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


*//**
 * ＜p＞Description: Download some specified url resources to local specified location. BTW, proxy is supported.＜/p＞
 * 
 * <br/><br/>
 * @author will
 *
 * @2014-10-15
 *//*
public class DownLoader {
    private final static Log logger = LogFactory.getLog(DownLoader.class);
    
    private static int BUFFER_SIZE = 8096;
    private Vector downloadFiles = new Vector();
    private Vector saveToLocalFiles = new Vector();

    public void resetList() {
        downloadFiles.clear();
        saveToLocalFiles.clear();
    }

    *//**
     * Add a url to download list, and saving file name to file list.
     *//*
    public void addItem(String url, String filename) {
        downloadFiles.add(url);
        saveToLocalFiles.add(filename);
    }

    *//**
     * Download the resources in the downloadList.
     *//*
    public void downLoadByList() {
        String url = null;
        String filename = null;
        for (int i = 0; i < downloadFiles.size(); i++) {
            url = (String) downloadFiles.get(i);
            filename = (String) saveToLocalFiles.get(i);
            try {
                saveToFile(url, filename);
            } catch (IOException err) {
                logger.warn("资源[" + url + "]下载失败!!!");
                if (Consts.DEBUG) {
                    System.out.println("资源[" + url + "]下载失败!!!");
                }
            }
        }
        if (Consts.DEBUG) {
            System.out.println("下载完成!!!");
        }
    }

    *//**
     * Save resource to local file.
     * 
     * @param destUrl
     *            String
     * @param fileName
     *            String
     * @throws Exception
     *//*
    public void saveToFile(String destUrl, String fileName) throws IOException {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        try {
            URL url = null;
            byte[] buf = new byte[BUFFER_SIZE];
            int size = 0;
            // create connection
            url = new URL(destUrl);
            httpUrl = (HttpURLConnection) url.openConnection();
            // connect to the remote resources.
            httpUrl.connect();
            // get the inputStream
            bis = new BufferedInputStream(httpUrl.getInputStream());
            // create a local file OutputStream.
            fos = new FileOutputStream(fileName);
            if (Consts.DEBUG)
                System.out.println("正在获取链接[" + destUrl + "]的内容.../n将其保存为文件["
                        + fileName + "]");
            // save file
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
        } finally {
            if (fos != null) {
                fos.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (httpUrl != null) {
                httpUrl.disconnect();
            }
        }
    }

    public static void main(String argv[]) {
        testDownload();
    }

    private static void testDownload() {
        DownLoader save = new DownLoader();
        try {
            *//**
             * add to download list
             *//*
             save.addItem("http://fdfs.xmcdn.com/group5/M01/0B/6F/wKgDtlN0IBawqt-gAANUfn6i5YQ573_common_medium.jpg","./111111111.jpg");//
             save.addItem("https://jersey.java.net/images/jersey_logo.png","./2222222222.png");
             save.addItem("http://img4.duitang.com/uploads/item/201207/19/20120719132725_UkzCN.thumb.600_0.jpeg","./luffy.jpeg");
            *//**
             * begin to download
             *//*
             save.downLoadByList();
//            save.saveToFile(
//                    "http://fdfs.xmcdn.com/group5/M01/0B/6F/wKgDtlN0IBawqt-gAANUfn6i5YQ573_common_medium.jpg",
//                    "./dsdfdsdf.jpg");
        } catch (Exception err) {
        	err.printStackTrace();
        }
    }
    
    *//**
     * Make sure that we already have xxx-plugin.jar file in the correct folder.
     *
     * <br/>
     * @param serviceType
     * @throws IOException
     *//*
    public void doJarExist (Integer serviceType) throws IOException {
        String[] files = FileConsts.JAR_FILE_MAP.get(serviceType);
        File localFile = null;
        for (String file : files) {
            if(file.endsWith(".jar")) {
                localFile = new File(AgentFileHelper.getCoreLibDir() + file);
            } else {
                localFile = new File(AgentFileHelper.getCoreLibDir() + file);
            }
            if (!localFile.exists()) {
                saveToFile("http://img4.duitang.com/uploads/item/201207/19/20120719132725_UkzCN.thumb.600_0.jpeg", localFile.getAbsolutePath());
            }
        }
    }
}
*/