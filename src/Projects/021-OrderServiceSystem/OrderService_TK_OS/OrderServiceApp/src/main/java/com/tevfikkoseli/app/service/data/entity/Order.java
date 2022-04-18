package com.tevfikkoseli.app.service.data.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Order { //POJO (Plain Old Java Object)
    public long id;
    public LocalDateTime oDateTime= LocalDateTime.now();
    public int clientId;

}
