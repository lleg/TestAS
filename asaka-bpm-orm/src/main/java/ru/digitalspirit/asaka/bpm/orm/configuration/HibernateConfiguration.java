package ru.digitalspirit.asaka.bpm.orm.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.EnumMap;
import java.util.Map;


public class HibernateConfiguration {

	private Map<Model, SessionFactory> sfMap = new EnumMap<>(Model.class);

	private static final Map<Model, String> CONFIGURATION_FILE;

	private static final Map<Model, String> LOCAL_CONFIGURATION_FILE;

	static {
		CONFIGURATION_FILE = new EnumMap<>(Model.class);
		CONFIGURATION_FILE.put(Model.ASAKA_MICROZIME, "ormmapping/microzime/BpmOrmModel.cfg.xml");


		LOCAL_CONFIGURATION_FILE = new EnumMap<>(Model.class);
		LOCAL_CONFIGURATION_FILE.put(Model.ASAKA_MICROZIME, "ormmapping/microzime/BpmOrmModelLocal.cfg.xml");
	}
	
	@SuppressWarnings("deprecation")
	private SessionFactory getSessionFactory(Model model) {
		return new Configuration().configure
				((String)CONFIGURATION_FILE.get
						(model))
				.buildSessionFactory();
	}

	@SuppressWarnings("deprecation")
	private SessionFactory getLocalSessionFactory(Model model) {
		return new Configuration()
				.configure(LOCAL_CONFIGURATION_FILE.get(model))
				.buildSessionFactory();
	}
	

	public SessionFactory getSf(Model model) {
		if (sfMap.get(model) == null) {
			sfMap.put(model, getSessionFactory(model));
		}
		return sfMap.get(model);
	}

	public SessionFactory getLocalSf(Model model) {
		if (sfMap.get(model) == null) {
			sfMap.put(model, getLocalSessionFactory(model));
		}
		return sfMap.get(model);
	}

}
