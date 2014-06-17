package ngdemo.domain.model;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 11/23/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */

import java.lang.*;

public class Ingredient {

    Integer amount;
    String amountUnits;
    String ingredientName;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getAmountUnits() {
        return amountUnits;
    }

    public void setAmountUnits(String amountUnits) {
        this.amountUnits = amountUnits;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
