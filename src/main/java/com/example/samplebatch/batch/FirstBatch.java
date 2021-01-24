package com.example.samplebatch.batch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.example.samplebatch.batch.base.*;
import com.example.samplebatch.enums.BatchResult;
import com.example.samplebatch.service.SftpService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("FirstBatch")
public class FirstBatch implements BatchBase {

    private final Logger logger = LoggerFactory.getLogger(FirstBatch.class);

    @Value("${output.dir}")
    private String outputDir;

    @Value("${marketing.cloud.sftp.host}")
    private String sftpHost;

    @Value("${marketing.cloud.sftp.port}")
    private Integer sftpPort;

    @Value("${marketing.cloud.sftp.username}")
    private String sftpUsername;

    @Value("${marketing.cloud.sftp.password}")
    private String sftpPassword;

    @Value("${marketing.cloud.sftp.dst.dir}")
    private String sftpDstdir;

    @Autowired
    private SftpService ftpService;

    public BatchResult execute(Map<String, Object> params) throws Exception {

        // TODO データ抽出

        // TODO CSV作成
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String fname = "data_" + today + ".csv";
        String srcFilePath = outputDir + "/" + fname;

        // SFTPサーバへの転送
        String dstFilePath = sftpDstdir + "/" + fname;
        ftpService.put(sftpHost, sftpPort, sftpUsername, sftpPassword, srcFilePath, dstFilePath);

        logger.info(FirstBatch.class.getSimpleName() + " executed.");
        return BatchResult.SUCCESS;
    }
}
