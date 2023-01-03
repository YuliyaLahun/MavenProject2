package classwork_day21;

import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.GsonBuilder;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MyParser {

    private  static final String JSON = "src/test/resources/classwork_day21/recipe.json";

    public void parseGSON() throws FileNotFoundException {
        Gson gson = new Gson();
        Recipe recipe = gson.fromJson(new JsonReader(new FileReader(JSON)), Recipe.class);

        System.out.println(recipe.getRecipename());
        System.out.println(recipe.getIngredlist());
        System.out.println(recipe.getPrepTime());
    }

    public String fromJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Search search = new Search("a", true);
        return gson.toJson(search);
    }

    public void toJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(new Ingredient("kartocha",3));
        Recipe recipe = new Recipe("draniki",ingredientList,120);
        System.out.println(gson.toJson(recipe));

    }
}
