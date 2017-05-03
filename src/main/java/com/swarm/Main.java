package com.swarm;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.config.logging.Level;
import org.wildfly.swarm.logging.LoggingFraction;
import org.wildfly.swarm.spi.api.StageConfig;
import org.wildfly.swarm.undertow.WARArchive;

public class Main {

	public static void main(String... args) throws Exception {

		ClassLoader cl = Main.class.getClassLoader();
//		URL stageConfig = cl.getResource("project-stages.yml");

		Swarm swarm = new Swarm();
//		swarm.withConfig(stageConfig);
//		swarm.withStageConfig(stageConfig);
		
		StageConfig config = swarm.stageConfig();
		String stage = config.resolve("project.stage").getValue();
		System.out.println(stage);
//		ConfigView config = swarm.configView();
		final String level = config.resolve("logger.level").getValue();
		Level logLevel = Level.valueOf(level);
		
		swarm.fraction(LoggingFraction.createDefaultLoggingFraction(logLevel));

		swarm.start();

		final WARArchive deployment = ShrinkWrap.create(WARArchive.class, "swarm.war");
		addResources(deployment, logLevel);

		swarm.deploy(deployment);

	}

	private static void addResources(WARArchive deployment, Level level) throws Exception {

		// Class files
		deployment.addPackages(true, "com/swarm");
		deployment.addAllDependencies();
		
		if (level == Level.DEBUG) {
			// This prints contents to logs... useful for debugging
			deployment.writeTo(System.out, Formatters.VERBOSE);
		}

	}

}
