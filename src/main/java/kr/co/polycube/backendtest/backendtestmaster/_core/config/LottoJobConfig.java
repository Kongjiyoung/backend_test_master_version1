package kr.co.polycube.backendtest.backendtestmaster._core.config;

import kr.co.polycube.backendtest.backendtestmaster.lottos.Lotto;
import kr.co.polycube.backendtest.backendtestmaster.lottos.LottoRepository;
import kr.co.polycube.backendtest.backendtestmaster.lottos.LottoService;
import kr.co.polycube.backendtest.backendtestmaster.winner.Winner;
import kr.co.polycube.backendtest.backendtestmaster.winner.WinnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;

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


@Slf4j
@RequiredArgsConstructor
@Configuration
public class LottoJobConfig {

    @Autowired
    private LottoService lottoService;

    @Autowired
    private WinnerRepository winnerRepository;

    @Autowired
    private LottoRepository lottoRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public Job lottoJob1(JobRepository jobRepository, Step simpleStep1) {
        return new JobBuilder("simpleJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(simpleStep1)
                .build();
    }

    @Bean
    public Step lottoStep1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        log.info(">>>>> This is lottoStep1");
        return new StepBuilder("lottoStep1", jobRepository)
                .tasklet(lottoTasklet(), platformTransactionManager)
                .build();
    }

    @Bean
    public Tasklet lottoTasklet() {
        return (contribution, chunkContext) -> {
            List<Lotto> lottos = lottoRepository.findAll();
            List<Integer> winningNumbers = lottoService.winningLottoNumbers();
            System.out.println(winningNumbers);
            for (Lotto lotto : lottos) {
                int rank = lottoService.winningRank(lotto, winningNumbers);
                if (rank > 0) {
                    Winner winner = new Winner();
                    winner.setRank(rank);
                    winner.setLotto(lottoRepository.findById(lotto.getId()));
                    winnerRepository.save(winner);
                }
            }
            return RepeatStatus.FINISHED;
        };
    }

    @Scheduled(cron = "0 37 1 * * SUN")
    public void runBatchJob() {
        try {
            JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();
            jobLauncher.run(lottoJob1(applicationContext.getBean(JobRepository.class), lottoStep1(applicationContext.getBean(JobRepository.class), applicationContext.getBean(PlatformTransactionManager.class))), params);
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
