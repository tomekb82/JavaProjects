package ngdemo.domain.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 11/23/13
 * Time: 10:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long recipeId;
    private String recipeTitle;
    private String recipeDescription;
    private String recipeInstructions;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public String getRecipeInstructions() {
        return recipeInstructions;
    }

    public void setRecipeInstructions(String recipeInstructions) {
        this.recipeInstructions = recipeInstructions;
    }

    //  private List<Ingredient> recipeIngredientList;

    /*
    public Recipe(){

    }

    public Recipe(String title, String description, String instructions){
     //   this.recipeIngredientList = new ArrayList<Ingredient>();
        this.recipeTitle = title;
        this.recipeDescription = description;
        this.recipeInstructions = instructions;
    }  */



    public String toString(){
        return "recipe= [" + recipeId.toString() + "," + recipeTitle + "," + recipeDescription + "," + recipeInstructions + "," ;//+ recipeIngredientList.toString() + "]";
    }

}
