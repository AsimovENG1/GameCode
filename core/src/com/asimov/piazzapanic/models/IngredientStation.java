package com.asimov.piazzapanic.models;

public class IngredientStation<T extends Ingredient> {
    private IngredientSource<T> ingredient;

    public IngredientStation(IngredientSource<T> ingredient) {
        this.ingredient = ingredient;
    }

    public void grab(Chef chef) {
        chef.stack.grab(ingredient.create());
    }
//    public void TomatoStation(){
//        IngredientStation<Tomato> TStation= new IngredientStation<>(()->new Tomato());
//    }
//    public void LettuceStation(){
//        IngredientStation<Lettuce> LStation= new IngredientStation<>(()->new Lettuce());
//    }
//    public void OnionStation(){
//        IngredientStation<Onion> OStation= new IngredientStation<>(()->new Onion());
//    }
//    public void BunStation(){
//        IngredientStation<Bun> BStation= new IngredientStation<>(()->new Bun());
//    }
//    public void MeatStation(){
//        IngredientStation<Meat> MStation= new IngredientStation<>(()->new Meat());
//    }
}
