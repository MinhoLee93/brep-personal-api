package com.brep.personal.hystrix;

import com.brep.personal.utils.UserContext;
import com.brep.personal.utils.UserContextHolder;

import java.util.concurrent.Callable;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/31
 * 3:13 오후
 */
public final class DelegatingUserContextCallable<V> implements Callable<V> {
    private final Callable<V> delegate;
    private UserContext originalUserContext;

    public DelegatingUserContextCallable(Callable<V> delegate,
                                         UserContext userContext) {
        this.delegate = delegate;
        this.originalUserContext = userContext;
    }

    public V call() throws Exception {
        UserContextHolder.setContext(originalUserContext);

        try {
            return delegate.call();
        } finally {
            this.originalUserContext = null;
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate,
                                         UserContext userContext) {
        return new DelegatingUserContextCallable<V>(delegate, userContext);
    }
}