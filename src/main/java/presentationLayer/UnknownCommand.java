/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentationLayer;
import exceptions.FogException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author oerte
 */
public class UnknownCommand extends Command {

    @Override
    String execute( HttpServletRequest request, HttpServletResponse response )  {
        String msg = "Unknown command. Contact IT";
        return msg;
     
    }
}
