package ngdemo.domain.bo.impl;

import ngdemo.domain.bo.RecipeBo;
import ngdemo.domain.dao.RecipeDao;
import ngdemo.domain.model.Recipe;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 12/2/13
 * Time: 9:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecipeBoImpl implements RecipeBo {

    RecipeDao recipeDao;

    public void setRecipeDao(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    public void save(Recipe recipe){
        System.out.println("recipe: save()");
        recipeDao.save(recipe);
    }

    public void update(Recipe recipe){
        recipeDao.update(recipe);
    }

    public void delete(Recipe recipe){
        recipeDao.delete(recipe);
    }

    public Recipe findByRecipeTitle(String title){
        return recipeDao.findByRecipeTitle(title);
    }
}
