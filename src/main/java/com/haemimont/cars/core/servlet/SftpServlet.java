package com.haemimont.cars.core.servlet;

import com.haemimont.cars.core.config.Config;
import com.haemimont.cars.core.loger.CustomLogger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/sftpServlet")
public class SftpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
        // CustomLogger customLogger = new CustomLogger();
//        Config config = new Config();
        // InitDB.initializeDb();
        FileSystemManager manager = VFS.getManager();
        String serverAddress = Config.getPropertyByName("serverAddress").trim();
        String userId = Config.getPropertyByName("userId").trim();
        String password = Config.getPropertyByName("password").trim();
        String downloadPath = Config.getPropertyByName("downloadPath");
        String sftpPort = Config.getPropertyByName("sftpPort");

        //Setup our SFTP configuration
        FileSystemOptions opts = new FileSystemOptions();
        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
                opts, "no");

        //Create the SFTP URI using the host name, userid, password,  remote path and file name
        String sftpUri = "sftp://" + userId + ":" + password + "@" + serverAddress + ":"+sftpPort+"/";


        FileObject sftpFiles = manager.resolveFile(sftpUri);
        FileObject[] files = sftpFiles.getChildren();
        for (FileObject remotefile:files
        ) {
            String fileName = remotefile.getName().getBaseName();

            File file = new File(downloadPath);
            FileObject localFile = manager.resolveFile(file.getAbsolutePath());
            FileObject localDownload = localFile.resolveFile(fileName);

            localDownload.copyFrom(remotefile,Selectors.SELECT_SELF);
            CustomLogger.logInfo("File downloaded "+fileName);
        }
        sendResponse(resp,"files downloaded successfully");}catch (Exception e ){sendResponse(resp,"something went wrong:"+e);}
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    private void sendResponse(HttpServletResponse response, String payload) {
        try {
            OutputStream out = response.getOutputStream();
            out.write(payload.getBytes());
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(Integer.toString(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
        }
    }
}
