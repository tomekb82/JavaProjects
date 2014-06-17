package ngdemo.domain.dao;

import ngdemo.domain.model.Recipe;

/**
 * Created with IntelliJ IDEA.
 * User: tomek
 * Date: 11/24/13
 * Time: 12:14 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RecipeDao {

    void save(Recipe recipe);

    void update(Recipe recipe);

    void delete(Recipe recipe);

    Recipe findByRecipeTitle(String title);

}
