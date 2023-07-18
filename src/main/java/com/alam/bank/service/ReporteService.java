package com.alam.bank.service;

import com.alam.bank.converter.ReporteDtoConverter;
import com.alam.bank.models.ReporteDto;
import com.alam.bank.repository.interfaces.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReporteService {

    private ReporteDtoConverter dtoConverter;

    private MovimientoRepository movimientoRepository;

    @Autowired
    public ReporteService(ReporteDtoConverter dtoConverter, MovimientoRepository movimientoRepository){
        this.dtoConverter = dtoConverter;
        this.movimientoRepository = movimientoRepository;
    }

    public List<ReporteDto> movimientosPorCliente(int idCliente, Date desde, Date hasta) {
        return this.dtoConverter.fromMovimientoEntityList(this.movimientoRepository
                .findByClienteAndFecha(String.valueOf(idCliente), desde, hasta));
    }

}
