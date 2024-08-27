package br.com.cotiinformatica.components;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cotiinformatica.collections.LogMensageria;
import br.com.cotiinformatica.dtos.MensagemUsuarioResponse;
import br.com.cotiinformatica.repositories.LogMensageriaRepository;

@Component
public class RabbitMQConsumerComponent {
	@Autowired
	private EmailComponent emailComponent;
	@Autowired
	private LogMensageriaRepository logMensageriaRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@RabbitListener(queues = { "mensagens-usuarios" })
	public void proccess(@Payload String message) throws Exception {
		LogMensageria logMensageria = new LogMensageria();
		logMensageria.setId(UUID.randomUUID());
		logMensageria.setDataHora(new Date());
		logMensageria.setOperacao("PROCESSAMENTO DE MENSAGEM DA FILA");
		try {
			MensagemUsuarioResponse response = objectMapper.readValue(message, MensagemUsuarioResponse.class);

			emailComponent.sendMail(response.getEmailDestinatario(), response.getAssunto(), response.getTexto());
			
			logMensageria.setStatus("SUCESSO");
			logMensageria.setDescricao(message);
			
		} catch (Exception e) {
			logMensageria.setStatus("ERRO");
			logMensageria.setDescricao(e.getMessage());
		} finally {
			logMensageriaRepository.save(logMensageria);
		}
	}
}
