package com.cisco.cat.reports.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class FTPConnect {

	public static void uploadToFTP(String strServer, String strUser, String strPassword, String strRemoteDir, String strLocalDir) {
		String server = strServer;
		int port = 21;
		String user = strUser;
		String pass = strPassword;

		FTPClient ftpClient = new FTPClient();

		try {
			// connect and login to the server
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);

			// use local passive mode to pass firewall
			ftpClient.enterLocalPassiveMode();

			System.out.println("Connected");
			
			String remoteDirPath = strRemoteDir;
			String localDirPath = strLocalDir;
			uploadDirectory(ftpClient, remoteDirPath, localDirPath, "");
			
			// log out and disconnect from the server
			ftpClient.logout();
			ftpClient.disconnect();

			System.out.println("Disconnected");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}




	public static void uploadDirectory(FTPClient ftpClient,
			String remoteDirPath, String localParentDir, String remoteParentDir)
					throws IOException {

		System.out.println("LISTING directory: " + localParentDir);

		File localDir = new File(localParentDir);
		File[] subFiles = localDir.listFiles();
		if (subFiles != null && subFiles.length > 0) {
			for (File item : subFiles) {
				String remoteFilePath = remoteDirPath + "/" + remoteParentDir
						+ "/" + item.getName();
				if (remoteParentDir.equals("")) {
					remoteFilePath = remoteDirPath + "/" + item.getName();
				}


				if (item.isFile()) {
					// upload the file
					String localFilePath = item.getAbsolutePath();
					System.out.println("About to upload the file: " + localFilePath);
					boolean uploaded = uploadSingleFile(ftpClient,
							localFilePath, remoteFilePath);
					if (uploaded) {
						System.out.println("UPLOADED a file to: "
								+ remoteFilePath);
					} else {
						System.out.println("COULD NOT upload the file: "
								+ localFilePath);
					}
				} else {
					try{
						// create directory on the server
						boolean created = ftpClient.makeDirectory(remoteFilePath);
						if (created) {
							System.out.println("CREATED the directory: "
									+ remoteFilePath);
						} else {
							System.out.println("COULD NOT create the directory: "
									+ remoteFilePath);
						}

						// upload the sub directory
						String parent = remoteParentDir + "/" + item.getName();
						if (remoteParentDir.equals("")) {
							parent = item.getName();
						}

						localParentDir = item.getAbsolutePath();
						uploadDirectory(ftpClient, remoteDirPath, localParentDir,
								parent);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}

				}
			}
		}
	}


	/**
	 * Upload a single file to the FTP server.
	 *
	 * @param ftpClient
	 *            an instance of org.apache.commons.net.ftp.FTPClient class.
	 * @param localFilePath
	 *            Path of the file on local computer
	 * @param remoteFilePath
	 *            Path of the file on remote the server
	 * @return true if the file was uploaded successfully, false otherwise
	 * @throws IOException
	 *             if any network or IO error occurred.
	 */
	public static boolean uploadSingleFile(FTPClient ftpClient,
			String localFilePath, String remoteFilePath) throws IOException {
		File localFile = new File(localFilePath);

		InputStream inputStream = new FileInputStream(localFile);
		try {
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			return ftpClient.storeFile(remoteFilePath, inputStream);
		} finally {
			inputStream.close();
		}
	}

}