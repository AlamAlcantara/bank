package com.alam.bank.controller;

import com.alam.bank.entity.Movimiento;
import com.alam.bank.models.MovimientoDto;
import com.alam.bank.models.MovimientoRequestDTO;
import com.alam.bank.models.MovimientoResponseDTO;
import com.alam.bank.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController extends BaseController<MovimientoDto, MovimientoService> {
    @Autowired
    public MovimientoController(MovimientoService service){
        super(service);
    }
    @PostMapping("/debito")
    public ResponseEntity<MovimientoDto> debito(@RequestBody MovimientoRequestDTO movimiento) {
        return new ResponseEntity<>(this.service.debito(movimiento), HttpStatus.OK);
    }
    @PostMapping("/credito")
    public ResponseEntity<MovimientoDto> credito(@RequestBody MovimientoRequestDTO movimiento) {
        return new ResponseEntity<>(this.service.credito(movimiento), HttpStatus.OK);
    }

}
