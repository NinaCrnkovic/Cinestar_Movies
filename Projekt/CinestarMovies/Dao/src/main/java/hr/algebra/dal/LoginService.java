/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.dal;

import hr.algebra.model.User;

/**
 *
 * @author Nina
 */
public interface LoginService {
    void userLoginIn(User user);
    void userLoggedOut();
    void createAdminUserIfNotExist();
}
