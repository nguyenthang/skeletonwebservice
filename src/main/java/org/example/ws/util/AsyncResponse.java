package org.example.ws.util;

import java.util.concurrent.*;


/**
 * Created by thangnguyen-imac on 5/29/16.
 */
public class AsyncResponse<V> implements Future<V> {

    private V value;

    private boolean isCancel = false;
    private boolean isDone = false;
    private boolean isCompletedExceptional = false;
    private long checkCompletedInterval = 100;

    private Exception executionException;

    public AsyncResponse() {
    }

    public AsyncResponse(V value) {
        this.value = value;
        this.isDone = true;
    }

    public AsyncResponse(Throwable throwable) {
        this.executionException = new ExecutionException(throwable);
        this.isCompletedExceptional = true;
        this.isDone = true;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        this.isDone = true;
        this.isCancel = true;
        return false;
    }

    public boolean complete(V value){
        this.value = value;
        this.isDone = true;
        return  true;
    }
    @Override
    public boolean isCancelled() {
        return this.isCancel;
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {

        block(0);
        if(isCancelled()){
            throw  new CancellationException();
        }

        if(isCompletedExceptional()){
            throw  new ExecutionException(this.executionException);
        }

        if(isDone){
            return this.value;
        }

        throw new InterruptedException();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {

        long timeOutInWillis = unit.toMillis(timeout);

        block(timeOutInWillis);
        if(isCancelled()){
            throw  new CancellationException();
        }

        if(isCompletedExceptional()){
            throw  new ExecutionException(this.executionException);
        }

        if(isDone){
            return this.value;
        }

        throw new InterruptedException();
    }

    public boolean isCompletedExceptional() {
        return isCompletedExceptional;
    }

    public void setCheckCompletedInterval(long mini) {
        this.checkCompletedInterval = mini;
    }

    private void block(long timeout) throws InterruptedException{
        long start = System.currentTimeMillis();
        while(!isDone() && !isCancelled()){
            if(timeout > 0){
                long now = System.currentTimeMillis();
                if(now > start + timeout){
                    break;
                }
            }

            Thread.sleep(checkCompletedInterval);
        }
    }

    public boolean completeExceptionally(Throwable throwable){
        this.value = null;
        this.isCompletedExceptional = true;
        this.executionException = new ExecutionException(throwable);
        this.isDone = true;

        return  true;
    }
}
