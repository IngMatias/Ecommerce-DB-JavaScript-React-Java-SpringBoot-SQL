/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOExceptions;

/**
 *
 * @author giova
 */

public class StickerNotOwnedByUserException extends Exception{
    public StickerNotOwnedByUserException(){
        super("StickerNotOwnedByUserException");
    }
}
