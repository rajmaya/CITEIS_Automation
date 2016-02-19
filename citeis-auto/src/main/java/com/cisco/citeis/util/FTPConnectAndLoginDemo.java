package com.cisco.citeis.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
public class FTPConnectAndLoginDemo {
	private static void showServerReply(FTPClient ftpClient) {
		String[] replies = ftpClient.getReplyStrings();
		if (replies != null && replies.length > 0) {
			for (String aReply : replies) {
				System.out.println("SERVER: " + aReply);
			}
		}
	}
	/*public static void main(String[] args) {
        String server = "10.197.64.122";
        int port = 21;
        String user = "username";
        String pass = "password";

        FTPClient ftpClient = new FTPClient();

        try {

            ftpClient.connect(server, port);
            showServerReply(ftpClient);

            int replyCode = ftpClient.getReplyCode();


            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Connect failed");
                return;
            }

           // boolean success = ftpClient.login(user, pass);
          //  showServerReply(ftpClient);

            if (!success) {
                System.out.println("Could not login to the server");
                return;
            }

            // Lists files and directories
            FTPFile[] files1 = ftpClient.listFiles("/Gopal");
            System.out.println(files1);
           // printFileDetails(files1);

            // uses simpler methods
            String[] files2 = ftpClient.listNames();
            printNames(files2);




        } catch (IOException ex) {
            System.out.println("Oops! Something wrong happened");
            ex.printStackTrace();
        } finally {
            // logs out and disconnects from server
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }*/

	public static void main(String[] args) {
		String server = "10.197.64.122";
		int port = 21;
		String user = "anonymous";
		String pass = "anonymous";

		FTPClient ftpClient = new FTPClient();

		try {
			// connect and login to the server
			ftpClient.connect(server, port);
			ftpClient.login(user, pass);

			// use local passive mode to pass firewall
			ftpClient.enterLocalPassiveMode();

			System.out.println("Connected");
			boolean success = false;
			String remoteDirPath = "/Gopal/results";
			String localDirPath = "C:/CITEIS_Automation/citeis-auto/results";
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













	//Print Details    
	private static void printFileDetails(FTPFile[] files) {
		DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (FTPFile file : files) {
			String details = file.getName();
			if (file.isDirectory()) {
				details = "[" + details + "]";
			}
			details += "\t\t" + file.getSize();
			details += "\t\t" + dateFormater.format(file.getTimestamp().getTime());

			System.out.println(details);
		}
	}

	private static void printNames(String files[]) {
		if (files != null && files.length > 0) {
			for (String aFile: files) {
				System.out.println(aFile);
			}
		}
	}

}