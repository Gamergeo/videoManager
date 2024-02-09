package com.gamergeo.project.videomanager;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.view.SceneView;

import de.saxsys.mvvmfx.spring.MvvmfxSpringApplication;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan("com.gamergeo.project.videomanager.model")
@EnableJpaRepositories("com.gamergeo.project.videomanager.repository")
@SpringBootApplication
public class VideoManagerApplication extends MvvmfxSpringApplication {

	public static void main(String[] args) {
		launch(args);
	}
    
//    @Override
//    public void init() {
//    	log.info("Application VideoManager JavaFX is starting...");
//		SpringApplicationBuilder builder = new SpringApplicationBuilder(VideoManagerApplication.class);
//		applicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));
//		
////		applicationContext.getBeanFactory().registerSingleton(VideoManagerApplication.class.getCanonicalName(), this);
//		
//		MvvmFX.setCustomDependencyInjector(applicationContext::getBean);
//		applicationContext.getBeanFactory().autowireBean(this);
//
//		initMvvmfx();
//		
//        log.info("Application VideoManager JavaFX started");
//    }
	
	@Override
	public void startMvvmfx(Stage stage) throws Exception {
    	Parent page =  FXUtils.load(SceneView.class).getView();
        Scene scene = new Scene(page);
        scene.getStylesheets().add(FXUtils.classPackageToResourcePath(this.getClass()));
        stage.setTitle("Video manager");
        stage.setMaximized(true);
        stage.setScene(scene);		
        stage.show();
	}
}
