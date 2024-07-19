package kr.co.polycube.backendtest.backendtestmaster._core.config;

import kr.co.polycube.backendtest.backendtestmaster.lottos.Lottos;
import kr.co.polycube.backendtest.backendtestmaster.lottos.LottosRepository;
import kr.co.polycube.backendtest.backendtestmaster.lottos.LottosService;
import kr.co.polycube.backendtest.backendtestmaster.winner.Winner;
import kr.co.polycube.backendtest.backendtestmaster.winner.WinnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SimpleJobConfig {

    @Autowired
    private LottosService lottosService;

    @Autowired
    private WinnerRepository winnerRepository;

    @Autowired
    private LottosRepository lottosRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public Job simpleJob1(JobRepository jobRepository, Step simpleStep1) {
        return new JobBuilder("simpleJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(simpleStep1)
                .build();
    }

    @Bean
    public Step simpleStep1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        log.info(">>>>> This is simpleStep1");
        return new StepBuilder("simpleStep1", jobRepository)
                .tasklet(lottoTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet lottoTasklet() {
        return (contribution, chunkContext) -> {
            List<Lottos> lottos = lottosRepository.findAll();
            List<Integer> winningNumbers = lottosService.generateLottoNumbers();
            System.out.println(winningNumbers);
            for (Lottos lotto : lottos) {
                int rank = lottosService.calculateRank(lotto, winningNumbers);
                if (rank > 0) {
                    Winner winner = new Winner();
                    winner.setRank(rank);
                    winner.setLottos(lottosRepository.findById(lotto.getId()));
                    winnerRepository.save(winner);
                }
            }
            return RepeatStatus.FINISHED;
        };
    }

    @Scheduled(cron = "0 28 20 * * *")
    public void runBatchJob() {
        try {
            JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(simpleJob1(applicationContext.getBean(JobRepository.class), simpleStep1(applicationContext.getBean(JobRepository.class), applicationContext.getBean(PlatformTransactionManager.class))), params);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobExecutionListenerSupport() {
            @Override
            public void afterJob(JobExecution jobExecution) {
                if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                    log.info("Job completed successfully");
                }
            }
        };
    }
}
