package com.objectpartners.plummer.spockctx.controllers;

import com.objectpartners.plummer.spockctx.services.DataByIdService;
import com.objectpartners.plummer.spockctx.services.DataListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    private DataListService dataListService;
    private DataByIdService dataByIdService;

    @Autowired
    public DataController(DataByIdService dataByIdService,
                          DataListService dataListService) {
        this.dataByIdService = dataByIdService;
        this.dataListService = dataListService;
    }

    @GetMapping
    public List<String> get() {
        return dataListService.get();
    }

    @GetMapping("{id}")
    public String getById(@PathVariable("id") String id) {
        return dataByIdService.get(id);
    }
}
