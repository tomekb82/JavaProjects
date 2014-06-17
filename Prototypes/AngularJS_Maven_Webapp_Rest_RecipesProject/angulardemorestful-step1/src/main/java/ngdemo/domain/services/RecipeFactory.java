package ngdemo.domain.services;

import ngdemo.domain.dao.RecipeDao;
import ngdemo.domain.dao.impl.RecipeDaoImpl;

/**
 * Created with IntelliJ IDEA.
 * User: tomek
 * Date: 11/24/13
 * Time: 12:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecipeFactory {

    public static RecipeDao getRecipeDAO(String sourceType){
        switch (sourceType){
            case "DB":
                return new RecipeDaoImpl();
        }
        return null;
    }
}
