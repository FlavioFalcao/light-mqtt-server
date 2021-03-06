package com.github.shimonxin.lms.spi.subscriptions;

import java.io.Serializable;

import com.github.shimonxin.lms.proto.QoS;

/**
 * Maintain the information about which Topic a certain ClientID is subscribed and at which QoS
 * 
 * 
 * @author andrea
 */
public class Subscription implements Serializable, Comparable<Subscription> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4316162484349885113L;
	private QoS requestedQos;
	private String clientId;
	private String topic;
	boolean cleanSession;
	boolean active = true;

	public Subscription() {

	}

	public Subscription(String clientId, String topic, QoS requestedQos, boolean cleanSession) {
		this.requestedQos = requestedQos;
		this.clientId = clientId;
		this.topic = topic;
		this.cleanSession = cleanSession;
	}

	public String getClientId() {
		return clientId;
	}

	public QoS getRequestedQos() {
		return requestedQos;
	}

	public String getTopic() {
		return topic;
	}

	public boolean isCleanSession() {
		return this.cleanSession;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Subscription other = (Subscription) obj;
		if (this.requestedQos != other.requestedQos) {
			return false;
		}
		if ((this.clientId == null) ? (other.clientId != null) : !this.clientId.equals(other.clientId)) {
			return false;
		}
		if ((this.topic == null) ? (other.topic != null) : !this.topic.equals(other.topic)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + (this.requestedQos != null ? this.requestedQos.hashCode() : 0);
		hash = 37 * hash + (this.clientId != null ? this.clientId.hashCode() : 0);
		hash = 37 * hash + (this.topic != null ? this.topic.hashCode() : 0);
		return hash;
	}

	/**
	 * Trivial match method
	 */
	boolean match(String topic) {
		return this.topic.equals(topic);
	}

	@Override
	public String toString() {
		return String.format("[t:%s, cliID: %s, qos: %s]", this.topic, this.clientId, this.requestedQos);
	}

	public void setRequestedQos(QoS requestedQos) {
		this.requestedQos = requestedQos;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setCleanSession(boolean cleanSession) {
		this.cleanSession = cleanSession;
	}

	@Override
	public int compareTo(Subscription other) {
		if (clientId == null || topic == null) {
			return -1;
		}
		if (!clientId.equals(other.clientId)) {
			return clientId.compareTo(other.clientId);
		}
		return topic.compareTo(other.topic);
	}
}
