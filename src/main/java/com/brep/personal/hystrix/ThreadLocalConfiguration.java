package com.brep.personal.hystrix;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/31
 * 3:14 오후
 */
//@Configuration
//public class ThreadLocalConfiguration {
//
//    @Autowired(required = false)
//    private HystrixConcurrencyStrategy existingConcurrencyStrategy;
//
//    @PostConstruct
//    public void init() {
//        // Keeps references of existing Hystrix plugins.
//        HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance()
//                .getEventNotifier();
//        HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance()
//                .getMetricsPublisher();
//        HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance()
//                .getPropertiesStrategy();
//        HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins.getInstance()
//                .getCommandExecutionHook();
//
//        HystrixPlugins.reset();
//
//        HystrixPlugins.getInstance().registerConcurrencyStrategy(new ThreadLocalAwareStrategy(existingConcurrencyStrategy));
//        HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
//        HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
//        HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
//        HystrixPlugins.getInstance().registerCommandExecutionHook(commandExecutionHook);
//    }
//}

