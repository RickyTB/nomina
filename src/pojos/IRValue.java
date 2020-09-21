/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.math.BigDecimal;

/**
 *
 * @author RickyTB
 */
public class IRValue {

    private final BigDecimal min;
    private final BigDecimal max;
    private final BigDecimal basica;
    private final BigDecimal excedente;

    public IRValue(BigDecimal min, BigDecimal max, BigDecimal basica, BigDecimal excedente) {
        this.min = min;
        this.max = max;
        this.basica = basica;
        this.excedente = excedente;
    }

    public BigDecimal getMin() {
        return min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public BigDecimal getBasica() {
        return basica;
    }

    public BigDecimal getExcedente() {
        return excedente;
    }

}
