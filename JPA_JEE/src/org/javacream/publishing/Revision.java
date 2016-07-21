package org.javacream.publishing;

import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class Revision {
	private String committer;
	private Date dateOfCommit;
	private String version;
	@Override
	public String toString() {
		return "Revision [committer=" + committer + ", dateOfCommit="
				+ dateOfCommit + ", version=" + version + "]";
	}
	protected Revision() {
		super();
	}
	public Revision(String committer, Date dateOfCommit, String version) {
		super();
		this.committer = committer;
		this.dateOfCommit = dateOfCommit;
		this.version = version;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((committer == null) ? 0 : committer.hashCode());
		result = prime * result
				+ ((dateOfCommit == null) ? 0 : dateOfCommit.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Revision other = (Revision) obj;
		if (committer == null) {
			if (other.committer != null)
				return false;
		} else if (!committer.equals(other.committer))
			return false;
		if (dateOfCommit == null) {
			if (other.dateOfCommit != null)
				return false;
		} else if (!dateOfCommit.equals(other.dateOfCommit))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}
	public String getCommitter() {
		return committer;
	}
	public Date getDateOfCommit() {
		return dateOfCommit;
	}
	public String getVersion() {
		return version;
	}
}
