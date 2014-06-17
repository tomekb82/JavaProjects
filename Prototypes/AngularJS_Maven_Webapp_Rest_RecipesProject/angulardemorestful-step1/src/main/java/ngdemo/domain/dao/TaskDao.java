package ngdemo.domain.dao;

import ngdemo.domain.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/3/13
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TaskDao {

    void save(Task task);

    void update(Task task);

    void delete(Task task);

    Task findByTaskName(String name);
}
