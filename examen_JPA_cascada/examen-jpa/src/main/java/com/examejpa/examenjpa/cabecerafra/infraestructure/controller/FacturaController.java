package com.examejpa.examenjpa.cabecerafra.infraestructure.controller;

import com.examejpa.examenjpa.cabecerafra.application.FacturaService;
import com.examejpa.examenjpa.cabecerafra.domain.CabeceraFra;
import com.examejpa.examenjpa.lineafra.application.LineasFacturaService;
import com.examejpa.examenjpa.cabecerafra.infraestructure.dto.FacturaOutPutDto;
import com.examejpa.examenjpa.lineafra.infraestructure.dto.LineaInputDto;
import com.examejpa.examenjpa.lineafra.infraestructure.dto.LineaOutPutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {
    @Autowired
    FacturaService facturaService;

    @Autowired
    LineasFacturaService lineasFacturaService;

    @GetMapping("/lineasCabecera")
    public List<LineaOutPutDto> mostrarLineasFactura(){

        return lineasFacturaService.mostrarLineasCabecera();

    }
    @GetMapping
    public List<FacturaOutPutDto> mostrarFactura(){
       return facturaService.listaFacturas();
    }



    @DeleteMapping("/delete/{id}")
    public String deleteItemFactura(@PathVariable int id){
        facturaService.deleteFactura(id);
        return"item eliminado";
    }

    @PutMapping("/linea/{idFra}")
    public CabeceraFra addLineaFra(@RequestBody LineaInputDto lineaInputDTO,
                                   @PathVariable int idFra){
        lineaInputDTO.setIdFra(idFra);
        return facturaService.addLineFra(lineaInputDTO);
    }


   /* @Autowired
    LineasFraService lineasFraService;

    @GetMapping("/lineasCabecera")
    public List<LineaOutputDto> mostrarLineasCabecera(){
        return  lineasFraService.mostrarLineasCabecera();

    }

    @GetMapping
    public List<FacturaOutputDto> mostrarFacturas(){
        return lineasFraService.mostrarFacturas();
    }

*/

}
