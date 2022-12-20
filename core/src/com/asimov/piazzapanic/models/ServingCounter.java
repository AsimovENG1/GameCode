package com.asimov.piazzapanic.models;

import com.badlogic.gdx.utils.Array;

public class ServingCounter<T extends Dish> {
    public Array<Customer> customers = new Array<>();
    public Array<Dish> dishes = new Array<>();

    public void place(Chef chef) throws Exception {
        if (chef.stack.size() <= 0) {
            throw new Exception("The chef's stack is empty.");
        }

        if (!(chef.stack.peek() instanceof Dish)) {
            throw new Exception("Item not a valid dish.");
        }

        Dish dish = (Dish)chef.stack.place();

        for (Customer customer : this.customers) {
            if (dish.recipe == customer.order) {
                customers.removeValue(customer, true);
                return;
            }
        }

        dishes.add(dish);
    }

    public void addCustomer(Customer customer) {
        for (Dish dish : dishes) {
            if (dish.recipe == customer.order) {
                dishes.removeValue(dish, true);
                return;
            }
        }

        customers.add(customer);
    }
}
