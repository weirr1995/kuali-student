package org.apache.torque.mojo.morph;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SchemaMorpher extends Morpher {
	private static final Log log = LogFactory.getLog(SchemaMorpher.class);

	// Ant impex has kfs hard coded
	String antSchemaName = "kfs";
	// Token we can look for to help identify this schema XML file as an Impex file generated by the Ant process
	String antSchemaToken = "name=\"" + antSchemaName + "\"";
	// Ant impex is hard coded to database.dtd
	String antDTDString = "\"database.dtd\"";
	// The Kuali database.dtd
	String newDTDString = "\"http://www.kuali.org/dtd/database.dtd\"";
	// Ant impex comment
	String antComment = "<!-- Autogenerated by KualiTorqueJDBCTransformTask! -->";
	// Maven impex comment
	String newComment = "<!-- Autogenerated by the Maven Impex Plugin -->";
	// Ant prologue
	String antPrologue = "<?xml version=\"1.0\"?>";
	// New prologue
	String newPrologue = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	public SchemaMorpher() {
		this(null, null);
	}

	public SchemaMorpher(MorphRequest morphRequest, String schema) {
		super();
		this.schema = schema;
		this.morphRequest = morphRequest;
	}

	/**
	 * Attempt to determine if this content is from an Ant Impex XML export
	 */
	protected boolean isAntImpexSchemaXML(String contents) {
		if (contents == null) {
			return false;
		}
		if (contents.indexOf(antSchemaToken) == -1) {
			return false;
		}
		if (contents.indexOf(antDTDString) == -1) {
			return false;
		}
		if (contents.indexOf(antComment) == -1) {
			return false;
		}
		// All 3 tokens we know about were present in the String
		// Pretty good chance it is content from an Ant Impex export
		return true;
	}

	/**
	 * Return true if we need to morph this file
	 */
	protected boolean isMorphNeeded(String contents) {
		if (!isAntImpexSchemaXML(contents)) {
			log.warn("Unable to determine if this is a schema exported from Ant Impex");
		}

		// Look for the DTD the Maven Impex Plugin uses
		int pos = contents.indexOf(newDTDString);

		if (pos == -1) {
			// It isn't there so we should morph
			return true;
		} else {
			// It is already there, we are good to go
			return false;
		}
	}

	/**
	 * Morph an Ant Impex XML file into a Maven Impex Plugin XML file
	 */
	@Override
	protected String getMorphedContents(String contents) {
		contents = StringUtils.replace(contents, antDTDString, newDTDString);
		contents = StringUtils.replace(contents, antComment, newComment);
		contents = StringUtils.replace(contents, antPrologue, newPrologue);
		return StringUtils.replace(contents, "name=\"" + antSchemaName + "\">", "name=\"" + getSchema() + "\">");
	}

	public String getAntSchemaName() {
		return antSchemaName;
	}

	public void setAntSchemaName(String defaultSchemaName) {
		this.antSchemaName = defaultSchemaName;
	}

	public String getAntSchemaToken() {
		return antSchemaToken;
	}

	public void setAntSchemaToken(String defaultSchemaToken) {
		this.antSchemaToken = defaultSchemaToken;
	}

	public String getAntDTDString() {
		return antDTDString;
	}

	public void setAntDTDString(String defaultDTDString) {
		this.antDTDString = defaultDTDString;
	}

	public String getNewDTDString() {
		return newDTDString;
	}

	public void setNewDTDString(String newDTDString) {
		this.newDTDString = newDTDString;
	}

	public String getAntComment() {
		return antComment;
	}

	public void setAntComment(String defaultComment) {
		this.antComment = defaultComment;
	}

	public String getNewComment() {
		return newComment;
	}

	public void setNewComment(String newComment) {
		this.newComment = newComment;
	}

	public String getAntPrologue() {
		return antPrologue;
	}

	public void setAntPrologue(String antPrologue) {
		this.antPrologue = antPrologue;
	}

	public String getNewPrologue() {
		return newPrologue;
	}

	public void setNewPrologue(String newPrologue) {
		this.newPrologue = newPrologue;
	}

}
