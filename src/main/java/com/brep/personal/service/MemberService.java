package com.brep.personal.service;

import com.brep.personal.client.MetaFeignClient;
import com.brep.personal.config.ServiceConfig;
import com.brep.personal.dto.MemberListResponse;
import com.brep.personal.repository.member.MemberRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/26
 * 8:45 오후
 */
@Service
@Slf4j
@RequiredArgsConstructor
@SuppressWarnings("SpellCheckingInspection")
//@DefaultProperties
public class MemberService {

    private final MetaFeignClient metaFeignClient;

    private final ServiceConfig serviceConfig;

    private final MemberRepository memberRepository;

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void randomlyRunLong() {
        Random rand = new Random();
        int randomNum = rand.nextInt((3 - 1) + 1) + 1;
        if (randomNum == 3) sleep();
    }

    /* --------------- Netflix Hystrix (p187)
    @HystrixCommand(
            commandKey = "getAllMembers",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5"),
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE") // ??
            },
            fallbackMethod = "hystrixFallback",
            threadPoolKey = "getAllMembersThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(
                            name = "coreSize",
                            value = "30"
                    ),
                    @HystrixProperty(
                            name = "maxQueueSize",
                            value = "10"
                    )
            }
    )*/
    /*@HystrixCommand(fallbackMethod = "hystrixFallback")*/
    public List<MemberListResponse> getAllMembers(int traceId) {

        log.debug("[Hystrix] trace id : {}", traceId);

        /* ThreadLocal UserContext */
        /*log.debug("[Hystrix] Correlation id: {}", UserContextHolder.getContext().getCorrelationId());*/

        randomlyRunLong();
        return memberRepository.findAll().stream().map(MemberListResponse::from).collect(Collectors.toList());

    }

    private List<MemberListResponse> hystrixFallback(int traceId) {

        log.debug("[Hystrix][Fallback] trace id : {}", traceId);

        return Collections.singletonList(
                MemberListResponse.builder()
                        .id(123L)
                        .name("hystrix fallback")
                        .build()
        );
    }

    /* --------------- Resilence4j / CircuitBreaker ---------------- */

    @CircuitBreaker(name = "test", fallbackMethod = "circuitBreakerFallback")
    public List<MemberListResponse> getAllMembersByResilence4jCircuitBreaker(int traceId) {
//        throw new HttpServerErrorException(HttpStatus.MULTI_STATUS);
        try {
            throw new IOException("[Resilience4j][CircuitBreaker] ignore count exception");
        } catch (IOException ioException) {
            ioException.printStackTrace();
//            return circuitBreakerFallback(traceId, ioException);
        }
        return null;
    }

    private List<MemberListResponse> circuitBreakerFallback(int traceId, Throwable e) {

        log.debug("[Resilience4j][CircuitBreaker][Fallback] trace id : " + traceId, e);

        return Collections.singletonList(
                MemberListResponse.builder()
                        .id(123L)
                        .name("circuit breaker fallback")
                        .build()
        );
    }

    /* --------------- Resilence4j / RateLimiter ---------------- */

    @RateLimiter(name = "test", fallbackMethod = "rateLimiterFallback")
    public List<MemberListResponse> getAllMembersByResilence4jRateLimiter(int traceId) {

        log.debug("[Resilience4j][RateLimiter] trace id : " + traceId);

        return null;
    }

    private List<MemberListResponse> rateLimiterFallback(int traceId, Throwable e) {

        log.debug("[Resilience4j][RateLimiter][Fallback] trace id : " + traceId, e);

        return Collections.singletonList(
                MemberListResponse.builder()
                        .id(123L)
                        .name("rate limit fallback")
                        .build()
        );
    }

    @Retry(name = "test", fallbackMethod = "retryFallback")
    public List<MemberListResponse> getAllMembersByResilence4jRetry(int traceId) {

        log.debug("[Resilience4j][Retry] start time : " + System.currentTimeMillis());
        log.debug("[Resilience4j][Retry] trace id : " + traceId);

        return Collections.singletonList(
                MemberListResponse.builder()
                        .id(123L)
                        .name(metaFeignClient.getTracks())
                        .build()
        );
    }

    private List<MemberListResponse> retryFallback(int traceId, Throwable e) {

        log.debug("[Resilience4j][Retry] end time : " + System.currentTimeMillis());
        log.debug("[Resilience4j][Retry][Fallback] trace id : " + traceId, e);

        return Collections.singletonList(
                MemberListResponse.builder()
                        .id(123L)
                        .name("retry fallback")
                        .build()
        );
    }

    @Bulkhead(name = "test", fallbackMethod = "bulkheadFallback")
    public List<MemberListResponse> getAllMembersByResilence4jBulkhead(int traceId) {

        log.debug("[Resilience4j][Bulkhead] trace id : " + traceId);

        return null;
    }

    private List<MemberListResponse> bulkheadFallback(int traceId, Throwable e) {

        log.debug("[Resilience4j][Bulkhead][Fallback] trace id : " + traceId, e);

        return Collections.singletonList(
                MemberListResponse.builder()
                        .id(123L)
                        .name("bulkhead fallback")
                        .build()
        );
    }

    public String getExampleProperty() {
        return serviceConfig.getExampleProperty();
    }
}
