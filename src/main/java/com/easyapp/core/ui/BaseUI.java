package com.easyapp.core.ui;

import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class BaseUI {
	private Optional<String> id;
	private Set<String> styleClasses;
	private String customStyle;

	public Optional<String> getId() {
		return id;
	}

	public void setId(final Optional<String> id) {
		this.id = id;
	}

	public void setId(final String id) {
		this.id = Optional.ofNullable(id);
	}

	public Set<String> getStyleClasses() {
		return styleClasses;
	}

	public String getStyleClassesString() {
		return styleClasses.stream().collect(joining(" "));
	}

	public void setStyleClasses(final Set<String> styleClasses) {
		this.styleClasses = styleClasses;
	}

	public void setStyleClassesString(final String styleClassesString) {
		if (styleClassesString != null) {
			if (this.styleClasses == null) {
				this.styleClasses = new HashSet<>();
			}

			this.styleClasses.addAll(Arrays.asList(styleClassesString.split(" ")));
		}
	}

	public void addStyleClass(final String styleClass) {
		if (styleClass != null) {
			if (this.styleClasses == null) {
				this.styleClasses = new HashSet<>();
			}

			this.styleClasses.add(styleClass);
		}
	}

	public void removeStyle(final String styleClass) {
		if (this.styleClasses != null) {
			this.styleClasses.remove(styleClass);
		}
	}

	public void clearStyles() {
		this.styleClasses.clear();
		this.styleClasses = null;
	}

	public String getCustomStyle() {
		return customStyle;
	}

	public void setCustomStyle(String customStyle) {
		this.customStyle = customStyle;
	}
}
