package lottery.web.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistration;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig
  extends WebMvcConfigurerAdapter
  implements WebSocketConfigurer
{
  @Autowired
  private SystemWebSocketHandler socketHandler;
  
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry)
  {
    registry.addHandler(this.socketHandler, new String[] { "/websocket" }).addInterceptors(new org.springframework.web.socket.server.HandshakeInterceptor[] { new HandshakeInterceptor() });
    
    registry.addHandler(this.socketHandler, new String[] { "/websocket/sockjs" }).addInterceptors(new org.springframework.web.socket.server.HandshakeInterceptor[] { new HandshakeInterceptor() }).withSockJS();
  }
}
