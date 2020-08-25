/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.singletons;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author RickyTB
 */
public class Constants {
    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("NominaPU");
}
