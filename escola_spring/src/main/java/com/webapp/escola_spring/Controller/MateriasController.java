package com.webapp.escola_spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.webapp.escola_spring.Repository.MateriasRepository;

@Controller
public class MateriasController {
    @Autowired
    private MateriasRepository mr;

    
}
