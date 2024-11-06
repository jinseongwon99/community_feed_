package com.jinsungwon99.auth.domain;

public class Password {

    private final String encryptPassword;

    private Password(String encryptPassword){
        this.encryptPassword = encryptPassword;
    }

    public static Password createEncryptPassword(String password){
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("패스워드는 빈값이 올 수 없습니다");
        }
        //패스워드 암호화
        return new Password(SHA256.encrypt(password));
    }

    //비밀번호 확인
    public boolean matchPassword(String password){
        return encryptPassword.matches(SHA256.encrypt(password));
    }

    public String getPasswordText(){
        return encryptPassword;
    }

}
