package br.com.cotiinformatica.components;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.collections.LogMensageria;
import br.com.cotiinformatica.dtos.MensagemUsuarioResponse;
import br.com.cotiinformatica.repositories.LogMensageriaRepository;

@Component
public class RabbitMQProducerComponent {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private Queue queue;
	@Autowired
	private ObjectMapper objectMapper;
	/*
	 * acesso ao banco mongodb
	 */
	@Autowired
	private LogMensageriaRepository logMensageriaRepository;

	public void send(MensagemUsuarioResponse mensagem) throws Exception {
		LogMensageria logMensageria = new LogMensageria();
		logMensageria.setId(UUID.randomUUID());
		logMensageria.setDataHora(new Date());
		logMensageria.setOperacao("ENVIO DE MENSAGEM PARA FILA " + queue.getName());
		try {
			String json = objectMapper.writeValueAsString(mensagem);

			rabbitTemplate.convertAndSend(queue.getName(), json);
			
			logMensageria.setStatus("SUCESSO");
			logMensageria.setDescricao(json);
		} catch (Exception e) {
			logMensageria.setStatus("ERRO");
			logMensageria.setDescricao(e.getMessage());
		} finally {
			logMensageriaRepository.save(logMensageria);
		}
	}
}
