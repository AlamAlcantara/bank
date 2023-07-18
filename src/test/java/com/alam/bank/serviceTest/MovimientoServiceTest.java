package com.alam.bank.serviceTest;

import com.alam.bank.converter.MovimientoDtoConverter;
import com.alam.bank.models.CuentaDto;
import com.alam.bank.models.MovimientoRequestDTO;
import com.alam.bank.repository.interfaces.MovimientoRepository;
import com.alam.bank.repository.interfaces.TipoMovimientoRepository;
import com.alam.bank.service.CuentaService;
import com.alam.bank.service.MovimientoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovimientoServiceTest {

    private MovimientoService movimientoService;
    private MovimientoDtoConverter movimientoDtoConverter;
    private MovimientoRepository movimientoRepository;
    private TipoMovimientoRepository tipoMovimientoRepository;
    private CuentaService cuentaService;

    @Before
    public void setUp(){
        this.movimientoRepository = mock(MovimientoRepository.class);
        this.tipoMovimientoRepository = mock(TipoMovimientoRepository.class);
        this.cuentaService = mock(CuentaService.class);
        this.movimientoDtoConverter = new MovimientoDtoConverter();

        this.movimientoService = new MovimientoService(movimientoRepository,
                movimientoDtoConverter,tipoMovimientoRepository,cuentaService);
    }


    @Test()
    public void shouldReturnNotFound(){
        //Setup
        when(movimientoRepository.findById(anyInt())).thenReturn(Optional.empty());

        //Execute
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> movimientoService.getById(1));

        //Assert
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Registro no encontrado", exception.getReason());
    }

    @Test
    public void shouldReturnBadRequest(){
        //Setup
        MovimientoRequestDTO movimientoRequestDTO = MovimientoRequestDTO.builder()
                .monto(0d)
                .idCuenta(0)
                .build();

        //Execute
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> movimientoService.debito(movimientoRequestDTO));

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    public void shouldReturnBadRequestWhenThereIsNoBalance(){
        //Setup
        CuentaDto cuenta = CuentaDto.builder()
                .numeroCuenta(1)
                .saldo(0d)
                .build();

        when(cuentaService.getById(anyInt())).thenReturn(cuenta);

        MovimientoRequestDTO movimientoRequestDTO = MovimientoRequestDTO.builder()
                .monto(500d)
                .idCuenta(1)
                .build();

        //Execute
        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> movimientoService.debito(movimientoRequestDTO));

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
        assertEquals("Saldo no disponible", exception.getReason());

    }


}
