package ngdemo.domain.bo.impl;

import ngdemo.domain.bo.TaskBo;
import ngdemo.domain.dao.TaskDao;
import ngdemo.domain.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/3/13
 * Time: 11:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaskBoImpl implements TaskBo {

    TaskDao taskDao;

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void save(Task task){
        System.out.println("task: save()");
        taskDao.save(task);
    }

    public void update(Task task){
        taskDao.update(task);
    }

    public void delete(Task task){
        taskDao.delete(task);
    }

    public Task findByTaskName(String name){
        return taskDao.findByTaskName(name);
    }
}