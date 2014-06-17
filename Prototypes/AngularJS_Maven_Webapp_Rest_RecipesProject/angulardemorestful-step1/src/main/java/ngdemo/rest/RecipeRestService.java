package ngdemo.rest;

import ngdemo.domain.model.Recipe;
import ngdemo.service.RecipeService;
import javax.ws.rs.*;
import java.util.*;

@Path("/recipes")
public class RecipeRestService {

    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public List<Recipe> getDefaultRecipesInJSON() {
        RecipeService recipeService = new RecipeService();
        return recipeService.getDefaultRecipes();
    }


    @GET
    @Path("/{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    public Recipe getDefaultRecipeInJSON(@PathParam(value = "id") String id) {
        RecipeService recipeService = new RecipeService();
        return recipeService.getDefaultRecipe(id);
    }

    @POST
    @Path("/{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    public void save(@PathParam(value = "id") String id) {
        System.out.println("SAVE!!!!!!!!!");
        //return 0;
    }

    @DELETE
    @Path("/{id}")
    //@Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam(value = "id") String id) {
        System.out.println("DELETE!!!!!!!!!");
        //return 0;
    }
}