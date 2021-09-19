/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.stepanoff.trsis.lec2.trsisjspsample;

/**
 *
 * @author wildhost5
 */
public class SampleBean {
    
    String message = "Developer says \"hello\" to you";
    
    public String getMessage(){
        return message;
    };
    
    public void setMessage(String message){
        this.message= message;
    }
}
