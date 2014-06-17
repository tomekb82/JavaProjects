package ngdemo.domain.dto;

import ngdemo.domain.model.Ingredient;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: tomek
 * Date: 11/24/13
 * Time: 12:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class RecipeDTO implements Serializable {

    private String id;
    private String title;
    private String description;
    private List<Ingredient> ingredientList;
    private String instructions;

    public RecipeDTO(){
        this.ingredientList = new ArrayList<Ingredient>();
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredientList;
    }

    public void setIngredient(Ingredient ingredient) {
        ingredientList.add(ingredient);
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String toString(){
        return "recipe DTO= [" + id + "," + title + "," + description + "," + instructions + "," + ingredientList.toString() + "]";
    }
}
