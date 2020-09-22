/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.interfaces;

import nomina.entities.Concepto;

/**
 *
 * @author RickyTB
 */
public interface ChooseConceptListener {
    public void onConceptChosen(String conceptName);
    public void onAddConcept(Concepto concepto);
}
