/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entc.addressing.digital.address.generator;

/**
 *
 * @author Gatta
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeoLocation p = new GeoLocation(9.565308, 79.856841);
        DigitalAddressGenerator generator = new DigitalAddressGenerator(p);
        DigitalAddress DA = generator.generateDigitalAddress();
        System.out.println(DA.getDigitalAddress());
        
    }
    }
    
