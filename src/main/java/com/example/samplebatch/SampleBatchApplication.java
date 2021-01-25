package com.example.samplebatch;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import com.example.samplebatch.batch.base.BatchBase;
import com.example.samplebatch.enums.BatchResult;
import com.example.samplebatch.util.StopWatch;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SampleBatchApplication {

	private static final Logger logger = LoggerFactory.getLogger(SampleBatchApplication.class);

	private static final StopWatch stopWatch = new StopWatch();

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		// SpringApplication.run(SampleBatchApplication.class, args);
		BatchResult batchResult = BatchResult.FAILURE;
		stopWatch.start(logger, "SampleBatchApplication");
		try (ConfigurableApplicationContext cac = SpringApplication.run(SampleBatchApplication.class)) {
			SampleBatchApplication app = cac.getBean(SampleBatchApplication.class);
			logger.info("args: ", (Object[]) args);
			batchResult = app.run(args);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stopWatch.end(logger, "SampleBatchApplication");
			logger.info("Result Code: " + batchResult.getCode());
			System.exit(batchResult.getCode());
		}
	}

	public Map<String, Object> parseCommandLine(String... args) throws ParseException {

		Options options = new Options();
		options.addOption("n", true, "Batch name.");

		CommandLineParser parser = new DefaultParser();
		CommandLine cl = parser.parse(options, args);
		if (!cl.hasOption("n")) {
			throw new InvalidParameterException("Batch Name is required.");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("n", cl.getOptionValue("n"));
		return map;
	}

	public BatchResult run(String... args) throws Exception {

		BatchResult batchResult = BatchResult.FAILURE;
		String batchName = "";
		try {
			Map<String, Object> params = parseCommandLine(args);
			batchName = ((String) params.get("n")).trim();
			stopWatch.start(logger, batchName);
			batchResult = ((BatchBase) context.getBean(batchName)).execute(params);
		} finally {
			stopWatch.end(logger, batchName);
			logger.info(String.format("%s Result：%s", batchName, batchResult));
		}
		return batchResult;
	}
}
