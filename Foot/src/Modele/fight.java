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
public interface fight {
    void fight(Equipe e1, Equipe e2)throws SQLException;
}
