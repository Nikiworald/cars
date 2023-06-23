package com.haemimont.cars.core.main;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.tools.InitDB;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import java.io.File;


public class Main {
    public static void main(String[] args) throws FileSystemException {

        // CustomLogger customLogger = new CustomLogger();
        InitDB.initializeDb();
        FileSystemManager manager = VFS.getManager();
        String serverAddress = Config.getPropertyByName("serverAddress").trim();
        String userId = Config.getPropertyByName("userId").trim();
        String password = Config.getPropertyByName("password").trim();
        String downloadPath = "C:\\Users\\user\\Desktop\\files_download\\";

        //Setup our SFTP configuration
        FileSystemOptions opts = new FileSystemOptions();
        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
                opts, "no");

        //Create the SFTP URI using the host name, userid, password,  remote path and file name
        String sftpUri = "sftp://" + userId + ":" + password + "@" + serverAddress + ":2222/";
//        Files remoteFiles = manager.getSchemes();

        FileObject sftpFiles = manager.resolveFile(sftpUri);
        FileObject[] files = sftpFiles.getChildren();
        for (FileObject remoteFile : files
        ) {
            String fileName = remoteFile.getName().getBaseName();

            File file = new File(downloadPath);
            FileObject localFile = manager.resolveFile(file.getAbsolutePath());
            FileObject localDownload = localFile.resolveFile(fileName);

            localDownload.copyFrom(remoteFile, Selectors.SELECT_SELF);
            System.out.println("File downloaded successful");
        }


    }
}
