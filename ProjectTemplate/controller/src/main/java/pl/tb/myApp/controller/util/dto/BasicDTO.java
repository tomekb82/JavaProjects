package pl.tb.myApp.controller.util.dto;

import java.util.List;

/**
 * Created by tomek on 24.10.15.
 */
public interface BasicDTO<E,T> {

    public List<E> createDTOs(List<T> models);

    public E createDTO(T model);

    public T createEntity(E model);
}
