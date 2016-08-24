package com.capgemini.chess.entity.listeners;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.capgemini.chess.generated.entities.BasicEntity;

public class BasicEntityListener {
	
	@PrePersist
	protected void onCreate(BasicEntity basicObject){
		basicObject.setCreatedAt(new Date());
		basicObject.setModifiedAt(new Date());
	}

	@PreUpdate
	protected void onUpdate(BasicEntity basicClassObject){
		basicClassObject.setModifiedAt(new Date());
	}
}
