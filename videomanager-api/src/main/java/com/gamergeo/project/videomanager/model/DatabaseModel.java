package com.gamergeo.project.videomanager.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class DatabaseModel {
	
	private LongProperty id = new SimpleLongProperty();
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabaseModel model = (DatabaseModel) o;
        
        return id.get() == model.idProperty().get();
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return this.idProperty().get();
	}
	
	public LongProperty idProperty() {
		return this.id;
	}
	
	public void setId(final long id) {
		this.idProperty().set(id);
	}
	
	@Override
	public String toString() {
		return "Model " + this.getClass().getSimpleName().toLowerCase() + " (id="+ getId() + ")";
	}
}
