/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.singletons;

import java.math.BigDecimal;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pojos.IRValue;

/**
 *
 * @author RickyTB
 */
public class Constants {

    public static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("NominaPU");

    public static final String[] CONCEPTOS = {
        "Impuesto a la renta",
        "Préstamo",
        "Otro"
    };

    public static IRValue[] IR_TABLE = {
        new IRValue(BigDecimal.ZERO, BigDecimal.valueOf(11315.0), BigDecimal.ZERO, BigDecimal.ZERO),
        new IRValue(BigDecimal.valueOf(11315.01), BigDecimal.valueOf(14416.0), BigDecimal.ZERO, BigDecimal.valueOf(0.05)),
        new IRValue(BigDecimal.valueOf(14416.01), BigDecimal.valueOf(18018.0), BigDecimal.valueOf(155), BigDecimal.valueOf(0.1)),
        new IRValue(BigDecimal.valueOf(18018.01), BigDecimal.valueOf(21639.0), BigDecimal.valueOf(515), BigDecimal.valueOf(0.12)),
        new IRValue(BigDecimal.valueOf(21639.01), BigDecimal.valueOf(43268.0), BigDecimal.valueOf(950), BigDecimal.valueOf(0.15)),
        new IRValue(BigDecimal.valueOf(43268.01), BigDecimal.valueOf(64887.0), BigDecimal.valueOf(4194), BigDecimal.valueOf(0.2)),
        new IRValue(BigDecimal.valueOf(64887.01), BigDecimal.valueOf(86516.0), BigDecimal.valueOf(8518), BigDecimal.valueOf(0.25)),
        new IRValue(BigDecimal.valueOf(86516.01), BigDecimal.valueOf(115338.0), BigDecimal.valueOf(13925), BigDecimal.valueOf(0.3)),
        new IRValue(BigDecimal.valueOf(115338.01), BigDecimal.ZERO, BigDecimal.valueOf(22572), BigDecimal.valueOf(0.35))
    };
}
