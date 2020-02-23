/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginScene;

import DTO.SimpleUser;

/**
 *
 * @author Mashael
 */
public class UserData {
    private static SimpleUser simpleUser ;

    public static SimpleUser getSimpleUser() {
        return simpleUser;
    }

    public static void setSimpleUser(SimpleUser simpleUser) {
        UserData.simpleUser = simpleUser;
    }
    
    
}
