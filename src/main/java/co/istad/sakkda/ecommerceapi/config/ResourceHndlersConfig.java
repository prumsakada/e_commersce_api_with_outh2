package co.istad.sakkda.ecommerceapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHndlersConfig implements WebMvcConfigurer {
    @Value("${file-upload.client-path}")
    private String clientPath;

    @Value("${file-upload.server-path}")
    private String serverPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(clientPath + "/**")
                .addResourceLocations("file:"+serverPath);
    }
}
