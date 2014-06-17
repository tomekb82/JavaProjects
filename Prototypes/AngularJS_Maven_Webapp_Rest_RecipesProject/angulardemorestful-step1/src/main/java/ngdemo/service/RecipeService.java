package ngdemo.service;

import ngdemo.domain.model.Ingredient;
import ngdemo.domain.model.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 11/23/13
 * Time: 10:48 AM
 * To change this template use File | Settings | File Templates.
 */
public class RecipeService {

    public List<Recipe> getDefaultRecipes(){

        Recipe recipe = new Recipe();

        recipe.setTitle("Zupa pomidorowa 1");
        recipe.setDescription("Przepis na zupę");
        recipe.setInstructions("Ugotuj i zjedz;)");

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("pomidory");
        ingredient.setAmount(5);
        ingredient.setAmountUnits("paczaa");
        recipe.setIngredient(ingredient);
        recipe.setIngredient(ingredient);
        recipe.setIngredient(ingredient);
        recipe.setIngredient(ingredient);
        recipe.setIngredient(ingredient);
        recipe.setIngredient(ingredient);

        //Recipe.setIdGlobal();
        //recipe.setId(Recipe.getIdGlobal());

        Recipe recipe2 = new Recipe();

        recipe2.setTitle("Zupa pomidorowa 2");
        recipe2.setDescription("Przepis na zupę");
        recipe2.setInstructions("Ugotuj i zjedz;)");
        recipe2.setIngredient(ingredient);

        //Recipe.setIdGlobal();
        //recipe2.setId(Recipe.getIdGlobal());

        List<Recipe> recipeList = new ArrayList<Recipe>();
        recipeList.add(recipe);
        recipeList.add(recipe2);

        return recipeList;
    }

    public Recipe getDefaultRecipe(String id){

        System.out.println("getDefaultRecipe=" + id);
        Recipe recipe = new Recipe();

        recipe.setTitle("ROSÓŁ");
        recipe.setDescription("ssssss");
        recipe.setInstructions("dddddddddddddd");

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("pomidory");
        ingredient.setAmount(5);
        ingredient.setAmountUnits("paczaa");
        recipe.setIngredient(ingredient);
        recipe.setIngredient(ingredient);
        recipe.setIngredient(ingredient);
        recipe.setIngredient(ingredient);
        recipe.setIngredient(ingredient);


        //Recipe.setIdGlobal();
        //recipe.setId(Recipe.getIdGlobal());

        return recipe;
    }

}
