package com.alam.bank.controller;

import com.alam.bank.models.ReporteDto;
import com.alam.bank.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportesController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteDto>> getReporteMovimientos(@RequestParam(required = true) int idCliente,
                                                                  @RequestParam(required = true) Date desde,
                                                                  @RequestParam(required = true) Date hasta) {
        return new ResponseEntity<>(reporteService.movimientosPorCliente(idCliente, desde, hasta),
                HttpStatus.OK);
    }

}
