/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas01;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordStore {
    public String name, username;
    private String password, hashkey;
    private double score;
    private int category;
    public static final int UNCATEGORIZED = 0;
    public static final int CAT_WEBAPP = 1;
    public static final int CAT_MOBILEAPP = 2;
    public static final int CAT_OTHER = 3;
    public PasswordStore(String name, String username, String plainPass)throws Exception{
        this.hashkey = Encryptor.generateKey();
        this.name = name;
        this.username = username;
        setPassword(plainPass);

    }
    public PasswordStore(String name, String username, String plainPass, int category)throws Exception{
        this.hashkey = Encryptor.generateKey();
        this.name = name;
        this.username = username;
        this.category = category;
        this.setPassword(plainPass);

    }
    public void setPassword(String plainPass)throws Exception{
        this.password = Encryptor.encrypt(plainPass, hashkey);
        this.calculateScore(plainPass);
    }
    public String getPassword()throws Exception{
        return Encryptor.decrypt(this.password, this.hashkey);
    }
    public void setCategory(int category){
        this.category = category;
    }
    public String getCategory(){
        String categoryName;

        switch (this.category) {
            case UNCATEGORIZED:
                categoryName = "BELUM BERKATEGORI";
                break;
            case CAT_WEBAPP:
                categoryName = "Aplikasi Web";
                break;
            case CAT_MOBILEAPP:
                categoryName = "Apliaksi Mobile";
                break;
            case CAT_OTHER:
                categoryName = "Akun Lainnya";
            default:
                categoryName = "Tidak Terdefinisi";
                break;
        }
        return categoryName;
    }
    private void calculateScore(String plainPass){
        if(plainPass.length() > 15){
            this.score = 10;

        }else{
            this.score = (plainPass.length() / 15.0) * 10 ;
        }
    }
    @Override
    public String toString() {
    return "Username: " + this.username + "\n" + 
            "Password: " + this.password + "\n" + 
            "HashKey: " + this.hashkey + "\n" +
            "Kategori: " + this.getCategory() + "\n" +
            "Score: " + this.score + "\n";
    }
    }