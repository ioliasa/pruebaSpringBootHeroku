package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.model.Producto;

@Service
public class ProductoService {

	String foto1 = "pngwing.com.png";
	String foto2 = "../img/pngwing.com(1).png";
	String foto3 = "../img/pngwing.com(2).png";
	String foto4 = "../img/pngwing.com(3).png";
	String foto5 = "../img/pngwing.com(4).png";
	String foto6 = "../img/pngwing.com(5).png";

	private List<Producto> productos = new ArrayList<>(Arrays.asList(
			new Producto("Bombones rellenos de crema de fresa", 1.20, "../img/pngwing.com.png"),
			new Producto("Barrita de crema con caramelo", 1.80, foto2), new Producto("Toffee con caramelo salado", 1.50, foto3),
			new Producto("Bombones rellenos de crema de avellanas", 1.20, foto4), new Producto("Muffin de cacao", 2.00, foto5),
			new Producto("Brownie con pepitas de chocolate", 2.20, foto6)));

	
	public Producto add(Producto e) {
		productos.add(e);
		return e;
	}

	/**
	 * Método creado para poder añadir productos en nuestro objeto wrapper para
	 * poder hacer el binding de la lista de productos que el usuario está
	 * editando.
	 * 
	 * @param p
	 */
	public void addProducto(Producto p) {
		productos.add(p);
	}

	public List<Producto> findAll() {
		return productos;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}
