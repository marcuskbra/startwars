package com.avenuecode.starwars;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
        logStartupCompleted();
    }

    public static void logStartupCompleted() {
	StringBuilder sb = new StringBuilder();
	sb.append("\n");
        sb.append(".___  ___.      ___      ____    ____    .___________. __    __   _______  \n");
        sb.append("|   \\/   |     /   \\     \\   \\  /   /    |           ||  |  |  | |   ____| \n");
        sb.append("|  \\  /  |    /  ^  \\     \\   \\/   /     `---|  |----`|  |__|  | |  |__    \n");
        sb.append("|  |\\/|  |   /  /_\\  \\     \\_    _/          |  |     |   __   | |   __|   \n");
        sb.append("|  |  |  |  /  _____  \\      |  |            |  |     |  |  |  | |  |____  \n");
        sb.append("|__|  |__| /__/     \\__\\     |__|            |__|     |__|  |__| |_______| \n");
        sb.append("\n");
        sb.append("  _______   ______   .______       ______  _______    .______    _______ \n"); 
        sb.append(" |   ____| /  __  \\  |   _  \\     /      ||   ____|   |   _  \\  |   ____| \n"); 
        sb.append(" |  |__   |  |  |  | |  |_)  |   |  ,----'|  |__      |  |_)  | |  |__    \n"); 
        sb.append(" |   __|  |  |  |  | |      /    |  |     |   __|     |   _  <  |   __|   \n"); 
        sb.append(" |  |     |  `--'  | |  |\\  \\---.|  `----.|  |____    |  |_)  | |  |____  \n"); 
        sb.append(" |__|      \\______/  | _| `.____| \\______||_______|   |______/  |_______| \n"); 
        sb.append("\n");
        sb.append(" ____    __    ____  __  .___________. __    __     ____    ____   ______    __    __ \n");
        sb.append(" \\   \\  /  \\  /   / |  | |           ||  |  |  |    \\   \\  /   /  /  __  \\  |  |  |  | \n");
        sb.append("  \\   \\/    \\/   /  |  | `---|  |----`|  |__|  |     \\   \\/   /  |  |  |  | |  |  |  | \n");
        sb.append("   \\            /   |  |     |  |     |   __   |      \\_    _/   |  |  |  | |  |  |  | \n");
        sb.append("    \\    /\\    /    |  |     |  |     |  |  |  |        |  |     |  `--'  | |  `--'  | \n");
        sb.append("     \\__/  \\__/     |__|     |__|     |__|  |__|        |__|      \\______/   \\______/  \n");
        sb.append("\n");
        
        Logger log = LoggerFactory.getLogger(Application.class);
        
        log.info(sb.toString());
    }

}
