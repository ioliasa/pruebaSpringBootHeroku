package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;

@Service
public class PedidoService {

	private List<Pedido> pedidos = new ArrayList<>();

	@Autowired
	private UsuarioService serviceUser;

	/**
	 * utilizo esta propiedad para que pueda reconocer el pedido nuevo que se ha
	 * reliado y poder obtenerlo más fácilmente.
	 */
	private long pedidoId = 0;

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public long getPedidoId() {
		return pedidoId;
	}

	/**
	 * Cuando se cree un pedido llamaremos a este método y se seteará
	 * automáticamente.
	 */
	public void setPedidoId(long pedidoRealizadoId) {
		pedidoId = pedidoRealizadoId;
	}

	public Pedido add(Pedido e) {
		pedidos.add(e);
		return e;
	}

	public List<Pedido> findAll() {
		return pedidos;
	}

	/**
	 * Genero unos datos estáticos para poder probar la aplicación. Realmente lo
	 * estoy añadiendo en el constructor de la clase Usuario, en su lista de
	 * pedidos.
	 */
	@PostConstruct
	public void init() {
		pedidos.addAll(Arrays.asList(new Pedido("Calle Luna, 45, Fantasyland, La Luna"),
				(new Pedido("Av. Constitucón, 73, Rojo, Redland"))));
	}

	/**
	 * Método para poder encontrar un pedido por su id. Consultaremos los del
	 * usuario logueado. Los pedidos generados arriba son para hacer pruebas.
	 * 
	 * @param id
	 * @return pedido concreto o null en caso de no encontrarlo.
	 */
	public Pedido findById(long id) {
		Usuario usuario = serviceUser.findById(serviceUser.getUserId());
		boolean encontrado = false;
		Pedido pedidoEncontrado = null;
		int i = 0;
		while (!encontrado && i < pedidos.size()) {
			if (usuario.getPedidos().get(i).getId() == id) {
				pedidoEncontrado = usuario.getPedidos().get(i);
			} else {
				i++;
			}
		}
		return pedidoEncontrado;
	}

	/**
	 * No comprobamos si el usuario existe puesto que ya se está controlando la
	 * sesión y, además, si ha llegado hasta aquí, debe estar registrado y tener una
	 * lista de pedidos, aunque esté vacía. Le añado los pedidos al usuario (ya que
	 * son datos estáticos, para que tenga algunos de base y comprobar el
	 * resultado).
	 * 
	 * @param usuario
	 * @return lista de pedidos del usuario
	 */
	public List<Pedido> encuentraPedidosDeUsuario() {
		Usuario usuario = serviceUser.findById(serviceUser.getUserId());
		return usuario.getPedidos();
	}

	/**
	 * Uso el iterador para poder encontrar rápidamente un pedido concreto del
	 * usuario logueado.
	 * 
	 * @param id
	 * @return pedido buscado. null si no lo encuentra
	 */
	public Pedido encuentraPedidoDeUsuario(long id) {
		Pedido buscado = null;
		Usuario usuario = serviceUser.findById(serviceUser.getUserId());
		Iterator<Pedido> it = usuario.getPedidos().iterator();
		boolean result = false;
		while (it.hasNext() && !result && !usuario.getPedidos().isEmpty()) {
			Pedido ped = it.next();
			if (ped.getId() == id) {
				buscado = ped;
				result = true;
			}
		}
		return buscado;
	}

	public void borraPedidoDeUsuario(long id) {
		Usuario usuario = serviceUser.findById(serviceUser.getUserId());
		Iterator<Pedido> it = usuario.getPedidos().iterator();
		boolean result = false;
		int i = 0;
		while (it.hasNext() && !result) {
			Pedido ped = it.next();
			if (ped.getId() == id) {
				result = true;
				usuario.getPedidos().remove(i);
			}
			i++;
		}
	}

	/**
	 * Método para crear un pedido para el usuario logueado. Añade un pedido a la
	 * lista de pedidos del usuario. De esta manera, se almacena ordenado por fecha.
	 */
	public void creaPedido(Pedido pedido) {
		Usuario usuario = serviceUser.findById(serviceUser.getUserId());
		usuario.getPedidos().add(0, pedido);
	}


}
