package ngdemo.domain.dao;

import ngdemo.domain.dto.RecipeDTO;

/**
 * Created with IntelliJ IDEA.
 * User: tomek
 * Date: 11/24/13
 * Time: 12:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RecipeDAO {

    public void insertRecipe(RecipeDTO recipe);
    public RecipeDTO findRecipe(int id);
    public void deleteRecipe(int id);

}
