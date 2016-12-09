package com.tougher.prattle.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Prattle {
	@Id
	@Column(name = "prattle_id")
	@SequenceGenerator(name = "PRATTLE_SEQ_GEN", sequenceName = "PRATTLE_SEQ", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRATTLE_SEQ_GEN")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "prattler_id")
	@NotNull
	private Prattler prattler;

	@Column(name = "prattle_text")
	private String text;

	@Column(name = "posted_date")
	private Timestamp postedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Prattler getPrattler() {
		return prattler;
	}

	public void setPrattler(Prattler prattler) {
		this.prattler = prattler;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Timestamp postedDate) {
		this.postedDate = postedDate;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
