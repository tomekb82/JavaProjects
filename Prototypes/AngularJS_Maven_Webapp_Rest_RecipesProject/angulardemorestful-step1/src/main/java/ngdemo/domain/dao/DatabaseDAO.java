package ngdemo.domain.dao;

import ngdemo.domain.dto.RecipeDTO;

/**
 * Created with IntelliJ IDEA.
 * User: tomek
 * Date: 11/24/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseDAO implements RecipeDAO {

    @Override
    public void insertRecipe(RecipeDTO recipeDTO){
        System.out.println("Insert recipe into database");
    }
    @Override
    public RecipeDTO findRecipe(int id){
        System.out.println("Find recipe in database");
        return  null;
    }
    @Override
    public void deleteRecipe(int id){
        System.out.println("Delete recipe from database");
    }

}
