package com.hardlove.cl.login.mvp.model.entity;

/**
 * Created by Chenlu on 2018/12/11
 * Email:chenlu@globalroam.com
 */
public class LoginEntity {
    private Data data;
    private int errorCode;
    private String errorMsg;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static class Data {
        /**
         * chapterTops : []
         * collectIds : []
         * email :
         * icon :
         * id : 14494
         * password :
         * token :
         * type : 0
         * username : 17736868357
         */

        private int id;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
