/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.auth;

import generalisation.GenericDAO.GenericDAO;
import java.util.List;
import model.base.Utilisateur;

/**
 *
 * @author To Mamiarilaza
 */
public class AuthentificationService {
    // Classe responsable de l'authentification des utilisateurs
    public static Utilisateur checkLogin(String username, String password) throws Exception {
        String query = "SELECT * FROM utilisateur WHERE username = '%s' and password = '%s'";
        query = String.format(query, username, password);
        
        List<Utilisateur> utilisateurs  = (List<Utilisateur>) GenericDAO.directQuery(Utilisateur.class, query, null);
        if (utilisateurs.size() == 0) {
            throw new Exception("Verifier votre nom d'utilisateur ou votre mot de passe !");
        } else {
            return utilisateurs.get(0);
        }
    }
}
