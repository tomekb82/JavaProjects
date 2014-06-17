package ngdemo.domain.bo;

import ngdemo.domain.model.Recipe;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/2/13
 * Time: 9:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RecipeBo {

    void save(Recipe recipe);

    void update(Recipe recipe);

    void delete(Recipe recipe);

    Recipe findByRecipeTitle(String title);
}
