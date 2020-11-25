package com.alquiler.controlador;
import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alquiler.entidades.alquiler;
import com.alquiler.entidades.vehiculo;
import com.alquiler.proyecto.config.PaypalPaymentIntent;
import com.alquiler.proyecto.config.PaypalPaymentMethod;
import com.alquiler.proyecto.interfaces.IAlquiler;
import com.alquiler.proyecto.interfaces.IVehiculo;
import com.alquiler.proyecto.interfaces.UsuarioRepo;
import com.alquiler.proyecto.servicios.PaypalService;
import com.alquiler.proyecto.utilidades.URLUtils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.sun.el.parser.ParseException;
@Controller
public class controladorAlquiler {
	@Autowired
	IAlquiler repoA;
	@Autowired
	IVehiculo repoV;
	@Autowired
	UsuarioRepo repoU;
	
	
	public static final String PAYPAL_SUCCESS_URL = "pay/Menu2"; /// vere por que no quiere hagarrar esta url
	public static final String PAYPAL_CANCEL_URL = "pay/Menu";
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;
	
  @GetMapping("/Vista")
  public String vista(Model modelo, Principal principal) {
	modelo.addAttribute("inner", repoA.findAll());
	modelo.addAttribute("user", repoU.findByNick(principal.getName()).getId());
	return "vista";  
  }
  
 @RequestMapping(value = "/CrearAlquiler", method = RequestMethod.POST)
	   public String crearvehiculo(
			   HttpServletRequest request,
			   @RequestParam(value = "marca")String marca,
			   @RequestParam(value = "modelo")String modelo,
			   @RequestParam(value = "matri")String matri,
			   @RequestParam(value = "cost")String cost,
			   @RequestParam(value = "ano")String ano,
			   @RequestParam(value = "img")String img,
			   
			   @RequestParam(value = "color")String color,
			   @RequestParam(value = "fechaE")String fechaE,
			   @RequestParam(value = "fechaC")String fechaC,
			   @RequestParam(value = "galones")int galones,
			   @RequestParam(value = "dias")int dias,
			   @RequestParam(value = "telefono")int telefono,
			   @RequestParam(value = "total")double total,
			   @RequestParam(value = "fkcl")int fkcl,
			   @RequestParam(value = "fkV")int fkV)throws ParseException  {
	alquiler a = new alquiler();
	Date date = new Date();
	a.setColor(color);
	a.setEstado(1); 
	a.setFecha_entrega(fechaE);
	a.setFecha_recibido(fechaC);
	a.setFechaDCompra(date);
	a.setFkCliente(repoU.findById(fkcl).get());
	a.setFkVehiculo(repoV.findById(fkV).get());
	a.setGalonesInicio(galones);
	a.setNumero_dias(dias);
	a.setTelefono(telefono);
	a.setTotal(total);
    repoA.save(a);
		  
		  vehiculo h = new vehiculo();
		  h.setId_vehiculo(fkV);
			h.setEstado(2);
			   h.setMarca(marca);
			   h.setModelo(modelo);
			   h.setMatricula(matri);
			   h.setCostoDiario(Double.parseDouble(cost));
			   h.setAno(ano);
			   h.setImagen(img);
			   repoV.save(h);
			   
			   String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
				String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;
				try {
					String descripcion = "descripción del producto:" +
							" carro: marca" +
							" Modelo: " + modelo + 
							" Costo Diario: " + cost +
							" Color del auto: " + color;
					
					Payment payment = paypalService.createPayment(
							total, 
							"USD", 
							PaypalPaymentMethod.paypal, 
							PaypalPaymentIntent.sale,
							descripcion,
							cancelUrl, 
							successUrl);
					System.out.println(1.0);
					for(Links links : payment.getLinks()){
						if(links.getRel().equals("approval_url")){
							System.out.println(1.1);
							return "redirect:" + links.getHref();
						}
					}
				} catch (PayPalRESTException e) {
					log.error(e.getMessage());
					System.out.println(e);
				}
				System.out.println(1.2);
			   return "redirect:/menu";
		}
	   
 @RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay(){
	 System.out.println(1.6);
	 return "redirect:/menu";
	}

 
 @RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			 System.out.println(1.3);
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				 System.out.println(1.4);
				return "redirect:/menu";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
			 System.out.println(e);
		}
			   System.out.println(1.5);
		return "redirect:/menu";
		
	}
}
