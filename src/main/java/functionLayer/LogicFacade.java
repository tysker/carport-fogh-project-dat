/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionLayer;

import dbAccess.CarportMapper;
import dbAccess.CustomerMapper;
import functionLayer.calculation.CarportFlatProductListe;
import dbAccess.ProductMapper;
import dbAccess.UserMapper;
import exceptions.FogException;
import functionLayer.calculation.CarportPointedRoofListe;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oerte
 */
public class LogicFacade {

    // ------------ LOGICFACADE METHODS FOR DATABASE -----------------
    public static User login(String email, String password) throws FogException {
        UserMapper um = new UserMapper();
        return um.getUser(email, password);
    }

    public static User createUser(String email, String password, int customerID) {
        User user = new User(email, password, "customer", customerID);
        UserMapper um = new UserMapper();
        um.createUser(user);
        return user;
    }

    public static Customer createCustomer(Customer customer) {
        CustomerMapper cm = new CustomerMapper();
        cm.addCustomer(customer);
        return customer;
    }

    public static Carport addCarport(Carport carport, Shed shed) {
        CarportMapper cm = new CarportMapper();
        cm.addCarport(carport, shed);
        return carport;
    }

    public static Shed addShed(Shed shed) {
        CarportMapper cm = new CarportMapper();
        cm.addShed(shed);
        return shed;
    }

    // ------- CARPORT CALCULATIONS LOGICFACADE ---------
    public static List<Product> CarportCalculaterFlatRoof(double length, double width, String roofMaterial) {
        CarportFlatProductListe cfp = new CarportFlatProductListe();
        List list = cfp.carportCalculaterFlatRoof(length, width, roofMaterial);
        return list;
    }

    public static List<Product> CarportCalculaterFlatRoofIncludingShed(double length, double width, double shedLength, double shedWidth, String roofMaterial) {
        CarportFlatProductListe cfp = new CarportFlatProductListe();
        List list = cfp.carportCalculaterFlatRoofIncludingShed(length, width, shedLength, shedWidth, roofMaterial);
        return list;
    }

    public static List<Product> CarportCalculatorPointedRoof(double length, double width, double degree, String roofMaterial) {
        CarportPointedRoofListe cfp = new CarportPointedRoofListe();
        List list = cfp.carportCalculaterPointedRoof(length, width, degree, roofMaterial);
        return list;
    }

    public static List<Product> CarportCalculatorPointedRoofIncludingShed(double length, double width, double degree, String roofMaterial) {
        CarportPointedRoofListe cfp = new CarportPointedRoofListe();
        List list = cfp.carportCalculaterPointedRoofIncludingShed(length, width, degree, length, width, roofMaterial);
        return list;
    }

    public static int[] StringArrayToIntArray(String[] s) {
        int[] arrayOfInteger = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            arrayOfInteger[i] = Integer.parseInt(s[i]);
        }
        return arrayOfInteger;
    }

    public static List<Integer> StringListToIntList(List<String> s) {
        List<Integer> arrayOfInteger = new ArrayList<>();
        for (String string : s) {
            arrayOfInteger.add(Integer.valueOf(string));
        }
        return arrayOfInteger;
    }

    public static double roundDoubleToTwoDecimalPoints(double value) {
        DecimalFormat df2 = new DecimalFormat(",##");
        return Double.parseDouble(df2.format(value));

    }

    public static List<Product> getAllProductsFromDatabase() {
        ProductMapper pm = new ProductMapper();
        List<Product> produktList = pm.allProducts();
        return produktList;
    }

//    public static double totalPriceOfCarport(List<Product> stykliste) {
//        double totalPriceOfCarport = 0;
//        for (Product produkt : stykliste) {
//            totalPriceOfCarport += produkt.getTotalPriceOfOrder();
//        }
//        return roundDoubleToTwoDecimalPoints(totalPriceOfCarport);
//    }

}
