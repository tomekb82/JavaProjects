package com.tbelina.spring.notifications;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Manages all notifications displayed to user of langup webapp.
 * 
 */
@Service
public class NotificationsService {

	private final String _attributeName = "notifications";

	/**
	 * 09.10.2013 - Known session problem: if there is no request -> exception
	 * {@link IllegalStateException}.
	 * 
	 * @return
	 */
	private HttpSession getSession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	/**
	 * Adds message only with content as parameter (without attribute and with
	 * default type of message - {@link MessageType#INFO}).
	 * 
	 * @param content
	 */
	public void addMessage(String content) {
		addMessage(content, MessageType.INFO);
	}

	/**
	 * Adds message with content and type as parameters (without attribute).
	 * 
	 * @param content
	 */
	public void addMessage(String content, MessageType type) {
		addMessage(content, type, new String());
	}

	/**
	 * Adds message with content, type and attributes as parameters.
	 * 
	 * @param content
	 */
	@SuppressWarnings("unchecked")
	public void addMessage(String content, MessageType type,
			String... attributes) {

		String attributesString = Arrays.toString(attributes);
		attributesString = attributesString.substring(1,
				attributesString.length() - 1);

		List<Notification> lista;
		HttpSession session = getSession();
		Object list = session.getAttribute(_attributeName);
		if (list != null && list instanceof List) {
			lista = (List<Notification>) list;
		} else {
			lista = new ArrayList<Notification>();
		}
		lista.add(new Notification(type, content, attributesString));
		session.setAttribute(_attributeName, lista);
	}

	public void clearNotifications() {
		HttpSession session = getSession();
		session.setAttribute(_attributeName, null);

	}

	/**
	 * Combines notifications from session, userDetails object and persistence
	 * from database.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Notification> getNotifications() {
		HttpSession session = getSession();
		Object list = session.getAttribute(_attributeName);
		if (list != null && list instanceof List) {
			return (List<Notification>) list;
		}
		return null;
	}

	/**
	 * Return {@link Notification} object which can be use outside
	 * {@link NotificationsService}.
	 * 
	 * @param type
	 * @param content
	 * @param attributes
	 *            may be null
	 * @return
	 */
	public Notification createNotificationObject(MessageType type,
			String content, List<String> attributes) {
		if (null == attributes || attributes.isEmpty()) {
			return new Notification(type, content);
		}
		String preparedAttributes = Arrays.toString(attributes.toArray(new String[attributes.size()]));
		preparedAttributes = preparedAttributes.substring(1, preparedAttributes.length());
		return new Notification(type, content, preparedAttributes);
	}
}
