package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

public class CategoryTestCase {
	private static AnnotationConfigApplicationContext context;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	@Test
	public void testCRUDCategory() {
		// add operation
		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is description of Laptop");
		category.setImageURL("CAT_1.png");

		assertEquals("Successfully added a category inside the table", true, categoryDAO.add(category));

		category = new Category();
		category.setName("Television");
		category.setDescription("This is description of Television");
		category.setImageURL("CAT_2.png");

		assertEquals("Successfully added a category inside the table", true, categoryDAO.add(category));

		// fetching and updating the category
		category = categoryDAO.get(2);
		category.setName("TV");
		assertEquals("Successfully undated a single category inside the table", true, categoryDAO.update(category));

		// Delete the category
		assertEquals("Successfully deleted a single category inside the table", true, categoryDAO.delete(category));
		
		//fetching the list
		assertEquals("Successfully fetched a ilst of categories from the table", 1, categoryDAO.list().size());
		

	}

}
