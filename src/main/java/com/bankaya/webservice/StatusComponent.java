package com.bankaya.webservice;

import com.bankaya.webservice.gen.Status;
import org.springframework.stereotype.Component;

@Component
public class StatusComponent {

    public Status setDefaultStatus(Object message) {
        Status status = new Status();
        status.setMessage(message.toString());

        return status;
    }
}
