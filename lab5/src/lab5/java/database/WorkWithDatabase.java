package lab5.java.database;

import java.sql.Connection;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

public class WorkWithDatabase {

    private static Commands comand = new Commands();

    public void add(String nameProduct, int costProduct) {
        comand.add(nameProduct, costProduct);
    }

    public void delete(String nameProduct) {
        comand.delete(nameProduct);
    }

    public ArrayList<Products> showAll() {
        return comand.showAll();
    }

    public int price(String nameProduct) {
        return comand.price(nameProduct);
    }

    public void changePrice(String nameProduct, int newCost) {
        comand.changePrice(nameProduct, newCost);
    }

    public ArrayList<Products> filterByPrice(int minCost, int maxCost) {
        return comand.filterByPrice(minCost, maxCost);
    }
}
