package ngdemo.domain.bo;

import ngdemo.domain.model.Task;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/3/13
 * Time: 11:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface TaskBo {

    void save(Task task);

    void update(Task task);

    void delete(Task task);

    Task findByTaskName(String name);
}
