import ngdemo.service.RecipeService;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 11/30/13
 * Time: 11:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestRecipeService {


    RecipeService recipeService;
    String id;

    @Before
    public void setUp() {
        recipeService = new RecipeService();
        id = "1";

    }

    @Test
    public void testGET() {
        System.out.println("testService: GET");
        int i=0;
        //for (Recipe recipe: recipeService.getDefaultRecipes()){
        //    assertEquals(Integer.toString(++i), recipe.getId());
        //
        //}
        //assertEquals("3", recipeService.getDefaultRecipe(id).getId());


    }

}
