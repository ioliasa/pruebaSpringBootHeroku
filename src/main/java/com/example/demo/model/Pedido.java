package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pedido {

	public static int generador_cod = 0;
	private long id;
	private List<Producto> productos;
	private String direccion;

	public Pedido(String direccion) {
		this.id = generador_cod++;
		this.productos = new ArrayList<>();
		this.direccion = direccion;
	}

	public Pedido() {
		this.id = generador_cod++;
		this.productos = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", productos=" + productos + ", direccion=" + direccion + "]";
	}

	public void addProducto(Producto producto) {
		this.productos.add(producto);
	}
	
}
