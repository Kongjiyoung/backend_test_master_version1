package kr.co.polycube.backendtest.backendtestmaster.config;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class BatchIntegrationTest {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job lottoJob;

    @Test
    public void testBatchJob() throws Exception {
        jobLauncher.run(lottoJob, new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters());
    }
}