package fr.insy2s.commerce.shoponlineback;

import fr.insy2s.commerce.shoponlineback.mappers.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MapperConfig.class)
public class ShopOnlineBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopOnlineBackApplication.class, args);
	}



}
