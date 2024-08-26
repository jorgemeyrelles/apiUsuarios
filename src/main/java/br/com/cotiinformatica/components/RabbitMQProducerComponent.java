package br.com.cotiinformatica.components;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.dtos.MensagemUsuarioResponse;

@Component
public class RabbitMQProducerComponent {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private Queue queue;
	@Autowired
	private ObjectMapper objectMapper;

	public void send(MensagemUsuarioResponse mensagem) throws Exception {
		String json = objectMapper.writeValueAsString(mensagem);

		rabbitTemplate.convertAndSend(queue.getName(), json);
	}
}
