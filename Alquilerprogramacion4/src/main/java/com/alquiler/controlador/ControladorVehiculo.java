package com.alquiler.controlador;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alquiler.entidades.vehiculo;
import com.alquiler.proyecto.interfaces.IVehiculo;
import com.sun.el.parser.ParseException;

@Controller
public class ControladorVehiculo {
	@Autowired
	IVehiculo repoV;
    @GetMapping("/vehiculo")
    public String listar(Model modelo) {
        modelo.addAttribute("vehiculos", repoV.findAll());
    	return "AdminP";
       }
    
    @GetMapping("/Rvehiculo")
    public String re(Model modelo) {
    	return "RegistrarAuto";
       }
    
  //guardar foto
	   @PostMapping("/CrearVehiculos")
	   public String crear(
			   @RequestParam(value = "marca")String marca,
			   @RequestParam(value = "modelo")String modelo,
			   @RequestParam(value = "matricula")String matricula,
			   @RequestParam(value = "imagen")MultipartFile imagen,
			   @RequestParam(value = "costoDiario")String costoDiario,
			   @RequestParam(value = "anno")String anno)throws ParseException  {
		   vehiculo h = new vehiculo();
		   if (!imagen.isEmpty() ) {
			   String ruta = "C:\\imagenes";
					  try {
						byte[] bytes = imagen.getBytes();
						Path rutaabsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
						Files.write(rutaabsoluta, bytes);
					    h.setImagen(imagen.getOriginalFilename());
					    h.setEstado(1);
					    h.setMarca(marca);
					   h.setModelo(modelo);
					   h.setMatricula(matricula);
					   h.setCostoDiario(Double.parseDouble(costoDiario));
					   h.setAno(anno);
					   repoV.save(h);
				
					} catch (Exception e) {
						System.out.print("error" + e);
					}
		}
		  
		   return"redirect:/vehiculo";
		}
	   
	   @GetMapping("/Admin")
	    public String llevar(Model modelo) {
	    	return"redirect:/vehiculo";
	       }
	   
	 //trae un objeto
	 	@RequestMapping(value = "/llevar/{id}")
		public ResponseEntity<vehiculo> show(@PathVariable(value = "id")int id) {
			vehiculo v = repoV.findById(id).get();
			return ResponseEntity.ok(v);
		}
		
		//editar
@RequestMapping(value = "/actualizar", method = RequestMethod.POST)
public String crear(@RequestParam(value = "id")int id,
@RequestParam(value = "estado")int estado,
@RequestParam(value = "marca")String marca,
@RequestParam(value = "modelo")String modelo,@RequestParam(value = "matricula")String matricula,
@RequestParam(value = "imagen")MultipartFile imagen,@RequestParam(value = "costoDiario")Double costoDiario,
@RequestParam(value = "anno")String anno)throws ParseException  {
			   vehiculo h = new vehiculo();
			   String ruta = "C:\\imagenes";
			   try {
				   byte[] bytes = imagen.getBytes();
					Path rutaabsoluta = Paths.get(ruta + "//" + imagen.getOriginalFilename());
					Files.write(rutaabsoluta, bytes);
					h.setId_vehiculo(id);
					h.setEstado(estado);
					   h.setMarca(marca);
					   h.setModelo(modelo);
					   h.setMatricula(matricula);
					   h.setCostoDiario(costoDiario);
					   h.setAno(anno);
					   h.setImagen(imagen.getOriginalFilename());
					   repoV.save(h);
			} catch (Exception e) {
				System.out.print("error" + e);
			}
						   
			   return "redirect:/vehiculo";
			}


//ELIMINAR DATOS
@GetMapping("/BorraVehiculo/{id}")
public String borrar(@PathVariable("id") int id ) {
	repoV.deleteById(id);
	   return "redirect:/vehiculo";
}
}
