/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modele;

import java.sql.SQLException;

/**
 *
 * @author Vanessa
 */
public interface fightManuel {
    void fightManuel(Equipe e1, Equipe e2, int e1BP, int e2BP) throws SQLException;
}
