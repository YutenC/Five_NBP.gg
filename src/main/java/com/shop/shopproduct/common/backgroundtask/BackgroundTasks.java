package com.shop.shopproduct.common.backgroundtask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class BackgroundTasks {

    private Map<String, TaskEntity> tasks = new ConcurrentHashMap<>();
    private ThreadPoolExecutor threadPoolExecutor;


    public BackgroundTasks() {
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void runTask(TaskEntity taskEntity) {
        tasks.put(taskEntity.getName(), taskEntity);
        Future<?> future = threadPoolExecutor.submit(taskEntity.getCallable());
        taskEntity.setResult(future);
    }

//    public void runTask(String sessionName,String taskName){
//
//        TaskEntity taskEntity=concurrentMap.get(sessionName);
//        Future<?>  future=threadPoolExecutor.submit(taskEntity.getTaskRunnable().get(taskName));
//        taskEntity.getTaskResult().put(taskName,future);
//
//    }

    public void addTask() {

    }

    public TaskEntity getTask(String name) {
        return tasks.get(name);
    }

    public void removeTask(String name){
        if(tasks.containsKey(name)){
            tasks.remove(name);
        }
    }

}
