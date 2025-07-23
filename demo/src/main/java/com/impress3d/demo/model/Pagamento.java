package com.impress3d.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPay;
	private Double valorPago;
	private String metodoPay; // pix, boleto bancário, cartão
	private String statusPay; // Pendente, Aprovado, Rejeitado
	private LocalDateTime dataPay;

	@OneToOne
	@JoinColumn(name = "idPedido")
	private Pedido pedido;

	public Integer getIdPay() {
		return idPay;
	}

	public void setIdPay(Integer idPay) {
		this.idPay = idPay;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	public String getMetodoPay() {
		return metodoPay;
	}

	public void setMetodoPay(String metodoPay) {
		this.metodoPay = metodoPay;
	}

	public String getStatusPay() {
		return statusPay;
	}

	public void setStatusPay(String statusPay) {
		this.statusPay = statusPay;
	}

	public LocalDateTime getDataPay() {
		return dataPay;
	}

	public void setDataPay(LocalDateTime dataPay) {
		this.dataPay = dataPay;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}