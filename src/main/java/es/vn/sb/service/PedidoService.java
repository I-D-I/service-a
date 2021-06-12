package es.vn.sb.service;

import es.vn.sb.model.Pedido;

public interface PedidoService {

	public String createPedido(Pedido pedido) throws Exception;
	public String createTopic(Pedido pedido);

}