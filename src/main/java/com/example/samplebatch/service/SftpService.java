package com.example.samplebatch.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SftpService {

    private final Logger logger = LoggerFactory.getLogger(SftpService.class);

    private Session connectSession(final String host, final int port, final String username, final String password)
            throws JSchException {

        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, port);
        session.setConfig("StrictHostKeyChecking", "no");
        session.setPassword(password);
        session.connect();
        return session;
    }

    private ChannelSftp connectSftpChannel(Session session) throws JSchException {

        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
        channel.connect();
        return channel;
    }

    private void disconnect(Session session, ChannelSftp channel) {

        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    public void put(String host, int port, String username, String password, String src, String dst)
            throws JSchException, SftpException {

        long sTime = System.currentTimeMillis();
        logger.info("sftp.put() start.");
        logger.info("src: " + src);
        logger.info("dst: " + dst);

        Session session = null;
        ChannelSftp channel = null;
        try {
            session = connectSession(host, port, username, password);
            channel = connectSftpChannel(session);
            channel.put(src, dst);
        } finally {
            disconnect(session, channel);
        }
        logger.info(String.format("sftp.put() end. - %d ms", System.currentTimeMillis() - sTime));
    }
}
