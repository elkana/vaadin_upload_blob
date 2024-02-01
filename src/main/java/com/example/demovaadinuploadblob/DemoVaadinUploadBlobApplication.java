package com.example.demovaadinuploadblob;

import java.io.IOException;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
public class DemoVaadinUploadBlobApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(DemoVaadinUploadBlobApplication.class, args);
	}


	@EventListener({ApplicationReadyEvent.class})
	void applicationReadyEvent() {
		browse("http://localhost:8080");
	}

	public static void browse(String url) {
		if (!SystemUtils.IS_OS_WINDOWS)
			return;
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
