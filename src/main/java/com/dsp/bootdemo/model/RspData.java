package com.dsp.bootdemo.model;

public class RspData extends Response {

    private Object o;
    public RspData(int eroorCode, String msg, Object o) {
        super(eroorCode, msg);
        this.o = o;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
}
