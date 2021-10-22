package ru.digitalspirit.asaka.bpm.orm.service;

import org.hibernate.Session;
import ru.digitalspirit.asaka.bpm.orm.configuration.HibernateConfiguration;
import ru.digitalspirit.asaka.bpm.orm.configuration.Model;
import ru.digitalspirit.asaka.bpm.orm.util.EntityServiceUtil;
import ru.digitalspirit.asaka.bpm.orm.util.ModelUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class EntityService {
	
	private HibernateConfiguration hc = new HibernateConfiguration();

	public enum State { SERVER, LOCAL }

	private State state = State.SERVER;

	public void setState(State state) {
		this.state = state;
	}

	public Session getSession(Model model) {
		return state.equals(State.SERVER) ? hc.getSf(model).openSession() : hc.getLocalSf(model).openSession();
	}

	public void closeSession(Session session) {
		if (session != null) {
            session.close();
        }
	}

	//Save whole object (with destroying unspecified associations)
	public Serializable save(Object entity, Session session) {
		if (ModelUtil.getId(entity) == null) {
            session.persist(entity);
        }
		session.merge(entity);
		return ModelUtil.getId(entity);
	}

	//Save object with destroying only preset associations
	public Serializable save(Object entity, Session session, List<String> nullsPath) {
		entity = EntityServiceUtil.prepareEntityForSave(entity, session, nullsPath, "");
		return save(entity, session);
	}


	public <T> T getById(Class<T> entityClass, Serializable id, Session session) {
		return session.get(entityClass, id);
	}

	public <T> T getById(Class<T> entityClass, Serializable id, List<String> fields, Session session) {
		return (T) EntityServiceUtil.getObjectWithPresetFields(session.get(entityClass, id), fields, "");
	}

	public <T> Collection<T> getAll(Class<T> entityClass, Session session) {
		return session.createCriteria(entityClass).list();
	}
	
	public void delete(Object entity, Session session) {
		session.delete(entity);
	}

	public <T> void delete(Class<T> entityClass, Serializable id, Session session) {
		session.delete(getById(entityClass, id, session));
	}
	
	public void update(Object entity, Session session) {
		session.update(entity);
	}

}
