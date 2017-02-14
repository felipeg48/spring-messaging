package com.apress.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WebSocketsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketsDemoApplication.class, args);
	}
	
	
	
	
	/* Enable this if you need to use a WebSocket client from a Code */
	
	/*
	@Bean
	CommandLineRunner sendAndSubcribe(SimpleWebSocketsProperties props){
		return args -> {
			String empty = "";
			String url = "ws://localhost:8080" + props.getEndpoint();
			
			List<Transport> transports = Arrays.asList( 
				    new WebSocketTransport(new StandardWebSocketClient()), 
				    new RestTemplateXhrTransport(new RestTemplate())); 
			
			
			SockJsClient sockJsClient = new SockJsClient(transports); 
			WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient); 
			StompSessionHandler handler = new StompSessionHandlerAdapter() { 
				 //@Override 
				 //  public void afterConnected(StompSession session, 
				 //    StompHeaders connectedHeaders){
				 //	   System.out.println("#### >>> ");
				 //	 
				 //}
			 };
			
			stompClient.setMessageConverter(new MappingJackson2MessageConverter()); 
			ListenableFuture<StompSession> future = stompClient.connect(url, handler, empty);
			
			StompSession session = future.get();
			session.send(props.getAppDestinationPrefix() + props.getMapping(), new ChatMessage("Hello there..."));
			
			
			//future.addCallback(
			//		new SuccessCallback<StompSession>() {
			//				public void onSuccess(StompSession stompSession) {
			//					System.out.println(">>> on Success!");
			//				}
			//		},
			//		new FailureCallback() {
			//			    public void onFailure(Throwable throwable) {
			//			        System.out.println(">>> on Failure!");
			//			        throwable.printStackTrace();
			//			    }
			//		}
			//);
			
		};
	}
	
	*/
}
