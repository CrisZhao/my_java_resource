package multithread;

import java.util.Collection;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程计算引擎
 * Created by zhaoqc on 2015/05/19.
 */
public class MultithreadCalculator<T> {
    private static final int THREAD_NUM = 10;

    private Stack<T> stack = new Stack<T>();

    private IHandler<T> handler;

    public MultithreadCalculator(Collection<T> datas, IHandler<T> handler) {
        this.stack.addAll(datas);
        this.handler = handler;
    }

    private synchronized T getData() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.pop();
    }

    public void startCalculate() {
        ExecutorService exec = Executors.newFixedThreadPool(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    T data;
                    while ((data = getData()) != null) {
                        handler.handle(data);
                    }
                }
            });
        }
    }

}
