package guru.springframework.bootstrap;

import guru.springframework.model.Category;
import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {



    private CategoryRepository categoryRepository;

    private RecipeRepository recipeRepository;

    private UnitOfMeasureRepository unitOfMeasureRepository;

    public DevBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        // Perfect guacamole recipe
        Recipe perfectGuacamole = new Recipe();

        Set<Category> perfectGuacamoleCateg = new HashSet<>();
        perfectGuacamoleCateg.add(categoryRepository.findByDescription("Mexican").get());
        perfectGuacamole.setCategories(perfectGuacamoleCateg);

        perfectGuacamole.setPrepTime(10);
        perfectGuacamole.setServings(4);
        perfectGuacamole.setDescription("The BEST guacamole! So easy to make with ripe avocados, salt, serrano chiles, cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips.");

        Ingredient guacamoleIngredient1 = new Ingredient();
        guacamoleIngredient1.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        guacamoleIngredient1.setAmount(BigDecimal.valueOf(0.5));
        guacamoleIngredient1.setDescription("Kosher salt");

        Ingredient guacamoleIngredient2 = new Ingredient();
        guacamoleIngredient2.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        guacamoleIngredient2.setAmount(BigDecimal.valueOf(1));
        guacamoleIngredient2.setDescription("Fresh lime juice or lemon juice");

        Set<Ingredient> guacamoleIngredients = new HashSet<>();
        perfectGuacamole.setIngredients(guacamoleIngredients);

        perfectGuacamole.getIngredients().add(guacamoleIngredient1);
        perfectGuacamole.getIngredients().add(guacamoleIngredient2);
        recipeRepository.save(perfectGuacamole);

        // Spicy chicken recipe
        Recipe spicyGrilledChicken = new Recipe();

        Set<Category> spicyChickenCateg = new HashSet<>();
        spicyChickenCateg.add(categoryRepository.findByDescription("Mexican").get());
        spicyGrilledChicken.setCategories(spicyChickenCateg);

        spicyGrilledChicken.setPrepTime(20);
        spicyGrilledChicken.setCookTime(15);
        spicyGrilledChicken.setServings(6);
        spicyGrilledChicken.setDescription("Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.");



        Ingredient spicyChickenIngredient1 = new Ingredient();
        spicyChickenIngredient1.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Tablespoon").get());
        spicyChickenIngredient1.setAmount(BigDecimal.valueOf(2));
        spicyChickenIngredient1.setDescription("Ancho chili powder");

        Ingredient spicyChickenIngredient2 = new Ingredient();
        spicyChickenIngredient2.setUnitOfMeasure(unitOfMeasureRepository.findByDescription("Teaspoon").get());
        spicyChickenIngredient2.setAmount(BigDecimal.valueOf(1));
        spicyChickenIngredient2.setDescription("Dried oregano");

        Set<Ingredient> spicyChickenIngredients = new HashSet<>();
        spicyGrilledChicken.setIngredients(spicyChickenIngredients);

        spicyGrilledChicken.getIngredients().add(spicyChickenIngredient1);
        spicyGrilledChicken.getIngredients().add(spicyChickenIngredient2);
        recipeRepository.save(spicyGrilledChicken);
    }
}
