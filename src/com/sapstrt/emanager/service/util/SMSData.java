package com.sapstrt.emanager.service.util;

import java.io.Serializable;

/**
 * Created by cambas on 10/5/13.
 */
public class SMSData implements Serializable{

    private String messageAddress;
    private String messageBody;
    private Long timseStamp;

    public String getMessageAddress() {
        return messageAddress;
    }

    public void setMessageAddress(String messageAddress) {
        this.messageAddress = messageAddress;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Long getTimseStamp() {
        return timseStamp;
    }

    public void setTimseStamp(Long timseStamp) {
        this.timseStamp = timseStamp;
    }
}
