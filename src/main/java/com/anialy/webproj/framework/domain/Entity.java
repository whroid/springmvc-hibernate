package com.anialy.webproj.framework.domain;

import java.io.Serializable;

public interface Entity<ID extends Serializable> extends Serializable, Cloneable {

	ID getId();

}